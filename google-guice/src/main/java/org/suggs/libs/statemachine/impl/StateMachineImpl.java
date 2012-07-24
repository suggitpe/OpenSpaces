/*
 * StateMachineImpl.java created on 24 Aug 2009 06:53:39 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.State;
import org.suggs.libs.statemachine.StateMachine;
import org.suggs.libs.statemachine.StateMachineContext;
import org.suggs.libs.statemachine.StateMachineException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the StateMachine interface. This implementation will delegate all transition evaluation
 * to the underlying current state. Thus the core responsibility of this class is to manage the overall state
 * of the state machine.
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2009
 */
public class StateMachineImpl implements StateMachine {

    private static final Logger LOG = LoggerFactory.getLogger( StateMachineImpl.class );

    private State initialState;
    private State currentState;

    public StateMachineImpl( State aInitialState ) {
        initialise( aInitialState );
    }

    private void initialise( State aInitialState ) {
        initialState = aInitialState;
        currentState = aInitialState;
    }

    @Override
    public void step( StateMachineContext aContext ) throws StateMachineException {
        State newState = currentState.step( aContext );
        if ( newState == null || currentState.equals( newState ) ) {
            if ( LOG.isInfoEnabled() ) {
                LOG.info( "No valid transitions found from state=[" + currentState
                          + "], state remain unchanged." );
            }
        }
        else {
            if ( LOG.isInfoEnabled() ) {
                LOG.info( "Transitioning state machine to new state=[" + newState + "]" );
            }
            currentState.executeExitAction( aContext );
            currentState = newState;
            currentState.executeEntryAction( aContext );
            // this may look odd: we need to call step again when we
            // reach a new state to allow for transitory states within
            // the overall state machine.
            this.step( aContext );
        }
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState( State aState ) {
        currentState = aState;
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder( "StateMachineImpl:" );
        buff.append( " currentState=[" ).append( currentState ).append( "]" );
        return buff.toString();
    }

    @Override
    public void reset() {
        LOG.debug( "Resetting state machine" );
        currentState = initialState;
    }
}
