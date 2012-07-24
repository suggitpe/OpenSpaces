/*
 * StateImpl.java created on 28 Aug 2009 18:21:13 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.Action;
import org.suggs.libs.statemachine.State;
import org.suggs.libs.statemachine.StateMachineContext;
import org.suggs.libs.statemachine.StateTransition;
import org.suggs.libs.statemachine.StateMachineException;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This main purpose of this class is to act as the source of state information of the state machine. It is
 * also an associative class in that it provides the link between the step execution at the state machine
 * level down into the association and evaluation of the transitions.
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2009
 */
public class StateImpl implements State {

    private static final Logger LOG = LoggerFactory.getLogger( StateImpl.class );

    private final String stateName;
    private Action entryAction;
    private Action exitAction;
    private Collection<StateTransition> transitions = new ArrayList<StateTransition>();

    public StateImpl( String aStateName ) {
        stateName = aStateName;
    }

    public StateImpl( State aState ) {
        stateName = aState.getStateName();
    }

    @Override
    public String getStateName() {
        return stateName;
    }

    public void setEntryAction( Action aAction ) {
        entryAction = aAction;
    }

    public void setExitAction( Action aAction ) {
        exitAction = aAction;
    }

    @Override
    public State step( StateMachineContext aContext ) throws StateMachineException {
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Step called from state=[" + stateName + "]" );
        }
        loadTransitionsIntoState();
        return evaluateTransitionsToNewState( aContext );
    }

    private void loadTransitionsIntoState() {
        if ( transitions == null || transitions.size() == 0 ) {
            transitions = StateTransitionManager.instance().getListOfTransitionsForState( this );
        }
    }

    private State evaluateTransitionsToNewState( StateMachineContext aContext )
                    throws StateMachineException {
        StateTransition successfulTransition = null;
        for ( StateTransition transInLoop : transitions ) {
            if ( transInLoop.evaluateTransitionValidity( aContext ) ) {
                if ( LOG.isInfoEnabled() ) {
                    LOG.info( "State Transition [" + transInLoop + "] is valid" );
                }
                if ( successfulTransition != null ) {
                    throw new StateMachineException( "More than one transition appears to be valid (only one should be valid for any given starting state)" );
                }
                successfulTransition = transInLoop;
            }
            else {
                if ( LOG.isInfoEnabled() ) {
                    LOG.info( "State Transition [" + transInLoop + "] is not valid" );
                }
            }
        }

        return successfulTransition == null ? this : successfulTransition.getEndingState();
    }

    @Override
    public void executeEntryAction( StateMachineContext aContext ) throws StateMachineException {
        if ( entryAction != null ) {
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "Executing entry action:" + entryAction );
            }
            entryAction.execute( aContext );
        }
    }

    @Override
    public void executeExitAction( StateMachineContext aContext ) throws StateMachineException {
        if ( exitAction != null ) {
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "Executing exit action:" + exitAction );
            }
            exitAction.execute( aContext );
        }
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }

        StateImpl other = (StateImpl) obj;
        if ( stateName == null ) {
            if ( other.stateName != null ) {
                return false;
            }
        }
        else if ( !stateName.equals( other.stateName ) ) {
            return false;
        }

        if ( entryAction == null && other.entryAction != null ) {
            return false;
        }

        if ( exitAction == null && other.exitAction != null ) {
            return false;
        }

        if ( transitions == null && other.transitions != null ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( stateName == null ) ? 0 : stateName.hashCode() );
        return result;
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder( "StateImpl:" );
        buff.append( " stateName=[" ).append( stateName ).append( "]" );
        return buff.toString();
    }

}
