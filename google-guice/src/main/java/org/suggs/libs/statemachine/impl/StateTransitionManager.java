/*
 * StateTransitionManager.java created on 24 Aug 2009 06:59:46 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.State;
import org.suggs.libs.statemachine.StateTransition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used to store all of the state transitions in a single accessible place. Realistically it is
 * a fancy collections class that is accessed statically for all parties. <br/>
 * <br/>
 * <b>TODO: This needs to be made thread safe and also to ensure that it can service more than one state
 * machine at any one time.</b>
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2009
 */
public final class StateTransitionManager {

    private static final Logger LOG = LoggerFactory.getLogger( StateTransitionManager.class );
    private static final StateTransitionManager INSTANCE = new StateTransitionManager();
    private final Map<String, Map<String, StateTransition>> transitionMap = new HashMap<String, Map<String, StateTransition>>();

    public static StateTransitionManager instance() {
        return INSTANCE;
    }

    public Collection<StateTransition> getListOfTransitionsForState( State aState ) {
        if ( aState == null ) {
            throw new IllegalArgumentException( "Cannot use null for State Transition lookup" );
        }

        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Searching for all transitions for state=[" + aState + "]" );
        }

        if ( transitionMap.containsKey( aState.getStateName() ) ) {
            return transitionMap.get( aState.getStateName() ).values();
        }
        return new HashMap<String, StateTransition>().values();
    }

    public Collection<StateTransition> getAllTransitions() {
        List<StateTransition> listOfTransitions = new ArrayList<StateTransition>();
        for ( String stateName : transitionMap.keySet() ) {
            Map<String, StateTransition> innerMapOfTransitions = transitionMap.get( stateName );
            listOfTransitions.addAll( innerMapOfTransitions.values() );
        }
        return listOfTransitions;
    }

    public void addTransitionToManager( StateTransition aStateTransition ) {
        if ( aStateTransition == null ) {
            throw new IllegalArgumentException( "Cannot add a null transition to the transition manager" );
        }

        String startStateName = aStateTransition.getStartingState().getStateName();
        buildInnerTransitionMapIfNeeded( startStateName );

        Map<String, StateTransition> innerMap = transitionMap.get( startStateName );
        if ( innerMap.containsKey( aStateTransition.getTransitionName() ) ) {
            throw new IllegalStateException( "Cannot add more than one State Transition with the same name for the same state: State=["
                                             + aStateTransition.getStartingState()
                                             + "], Transition=["
                                             + aStateTransition + "], " );
        }

        innerMap.put( aStateTransition.getTransitionName(), aStateTransition );

        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Successfully loaded transition: state=[" + aStateTransition.getStartingState()
                       + "], transition=[" + aStateTransition + "]" );
        }
    }

    private void buildInnerTransitionMapIfNeeded( String aStateName ) {
        if ( !transitionMap.containsKey( aStateName ) ) {
            // using a concurrent hashmap to ensure that when the
            // values view is exposed there is some level of
            // protection
            // of the underlying map in a multi-threaded environment
            transitionMap.put( aStateName, new ConcurrentHashMap<String, StateTransition>() );
        }
    }

    public void setTransitions( List<StateTransition> aListOfTransitions ) {
        if ( aListOfTransitions == null ) {
            throw new IllegalArgumentException( "Cannot add a null list of transitions to the Transition Manager" );
        }

        for ( StateTransition transition : aListOfTransitions ) {
            this.addTransitionToManager( transition );
        }
    }

    public void clearTransitionsFromTransitionManager() {
        if ( LOG.isInfoEnabled() ) {
            LOG.info( "Clearing all transitions from the transition manager" );
        }
        transitionMap.clear();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder( "TransitionManager: " );
        ret.append( "{" ).append( Integer.toHexString( super.hashCode() ) ).append( "} " );
        ret.append( "numStates=[" ).append( transitionMap.size() ).append( "]" );
        for ( String startState : transitionMap.keySet() ) {
            ret.append( ": state[" ).append( startState ).append( "]" );
            for ( String transitionName : transitionMap.get( startState ).keySet() ) {
                ret.append( ", transition[" ).append( transitionName ).append( "]" );
            }
        }

        return ret.toString();
    }

}
