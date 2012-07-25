/*
 * StateTest.java created on 28 Aug 2009 18:15:18 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.*;
import org.suggs.libs.statemachine.impl.StateImpl;
import org.suggs.libs.statemachine.impl.StateTransitionManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite for the state implementation.
 *
 * @author suggitpe
 * @version 1.0 28 Aug 2009
 */
public class StateTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( StateTest.class );

    private StateMachineContext mockContext;
    private StateTransition mockTransitionOne;
    private StateTransition mockTransitionTwo;
    private Action mockAction;

    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "===================" + StateTest.class.getSimpleName() );
    }

    @Before
    public void doBefore() {
        StateTransitionManager.instance().clearTransitionsFromTransitionManager();
        mockContext = mock( StateMachineContext.class );
        mockTransitionOne = mock( StateTransition.class );
        mockTransitionTwo = mock( StateTransition.class );
        mockAction = mock( Action.class );
    }

    @Test
    public void stateInitiatesStateCorrectly() {
        final String STATE_NAME = "TestStateForTest";
        State state = new StateImpl( STATE_NAME );

        assertThat( state.getStateName(), equalTo( STATE_NAME ) );
    }

    @Test
    public void stepWithValidTransitionsToReturnNewState() throws StateMachineException {
        State state = new StateImpl( "TestState" );
        State endState = new StateImpl( "TestEndState" );

        when( mockTransitionOne.getStartingState() ).thenReturn( state );
        when( mockTransitionOne.getTransitionName() ).thenReturn( "invalidTransition" );
        when( mockTransitionTwo.getStartingState() ).thenReturn( state );
        when( mockTransitionTwo.getTransitionName() ).thenReturn( "badTransition" );
        when( mockTransitionTwo.getEndingState() ).thenReturn( endState );

        when( mockTransitionOne.evaluateTransitionValidity( mockContext ) ).thenReturn( false );
        when( mockTransitionTwo.evaluateTransitionValidity( mockContext ) ).thenReturn( true );

        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo );

        State newState = state.step( mockContext );

        assertThat( state, not( equalTo( newState ) ) );
        assertThat( endState, equalTo( newState ) );
    }

    @Test(expected = StateMachineException.class)
    public void stepWithTwoValidTransitionsCausesException() throws StateMachineException {
        State state = new StateImpl( "TestState" );
        State endState = new StateImpl( "TestEndState" );

        when( mockTransitionOne.getStartingState() ).thenReturn( state );
        when( mockTransitionOne.getTransitionName() ).thenReturn( "invalidTransition" );
        when( mockTransitionTwo.getStartingState() ).thenReturn( state );
        when( mockTransitionTwo.getTransitionName() ).thenReturn( "badTransition" );
        when( mockTransitionTwo.getEndingState() ).thenReturn( endState );

        when( mockTransitionOne.evaluateTransitionValidity( mockContext ) ).thenReturn( true );
        when( mockTransitionTwo.evaluateTransitionValidity( mockContext ) ).thenReturn( true );

        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo );

        State newState = state.step( mockContext );
    }

    @Test
    public void stepWithNonValidTransitionsToReturnSelf() throws StateMachineException {
        State state = new StateImpl( "TestState" );

        when( mockTransitionOne.getStartingState() ).thenReturn( state );
        when( mockTransitionOne.getTransitionName() ).thenReturn( "invalidTransition" );
        when( mockTransitionTwo.getStartingState() ).thenReturn( state );
        when( mockTransitionTwo.getTransitionName() ).thenReturn( "badTransition" );

        when( mockTransitionOne.evaluateTransitionValidity( mockContext ) ).thenReturn( false );
        when( mockTransitionTwo.evaluateTransitionValidity( mockContext ) ).thenReturn( false );

        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo );

        State newState = state.step( mockContext );

        assertThat( state, equalTo( newState ) );
    }

    @Test
    public void stepWithNoTransitionsSetUpReturnsSelf() throws StateMachineException {
        State state = new StateImpl( "TestState" );
        State newState = state.step( mockContext );

        assertThat( state, sameInstance( newState ) );
    }
}
