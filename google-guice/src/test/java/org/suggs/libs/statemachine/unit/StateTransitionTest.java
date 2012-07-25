/*
 * StateTransitionTest.java created on 1 Sep 2009 07:20:51 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.*;
import org.suggs.libs.statemachine.impl.StateImpl;
import org.suggs.libs.statemachine.impl.StateTransitionEventImpl;
import org.suggs.libs.statemachine.impl.StateTransitionImpl;
import org.suggs.libs.statemachine.support.FalseGuardStub;
import org.suggs.libs.statemachine.support.TrueGuardStub;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite for the state transition implementation.
 *
 * @author suggitpe
 * @version 1.0 1 Sep 2009
 */
public class StateTransitionTest {

    private static final Logger LOG = LoggerFactory.getLogger( StateTransitionTest.class );

    private State startState;
    private State endState;
    private StateMachineContext context;

    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "===================" + StateTransitionTest.class.getSimpleName() );
    }

    @Before
    public void doBefore() {
        startState = mock( State.class );
        endState = mock( State.class );
        context = mock( StateMachineContext.class );
    }

    @Test
    public void transitionDataSetIsSameWhenGetLater() {
        final String transName = "TestStateTransition";
        State localStartState = new StateImpl( "TestStartState" );
        State localendState = new StateImpl( "TestEndState" );
        StateTransition transition = new StateTransitionImpl( transName, localStartState, localendState );

        assertThat( localStartState, equalTo( transition.getStartingState() ) );
        assertThat( localendState, equalTo( transition.getEndingState() ) );
        assertThat( transName, equalTo( transition.getTransitionName() ) );
    }

    @Test(expected = StateMachineException.class)
    public void transitionEvaluationForNullContext() throws StateMachineException {
        StateTransition target = new StateTransitionImpl( "testTransition", startState, endState );

        target.evaluateTransitionValidity( null );

        fail( "The test should have thrown an execption so this should not be seen" );
    }

    @Test
    public void transitionEventEvaluationForValidEvent() throws StateMachineException {
        StateTransitionImpl target = new StateTransitionImpl( "testTransition", startState, endState );
        StateTransitionEvent evt = new StateTransitionEventImpl( "testEvent" );
        target.addTransitionEvent( evt );

        when( context.getStateTransitionEvent() ).thenReturn( new StateTransitionEventImpl( "testEvent" ) );

        boolean result = target.evaluateTransitionValidity( context );
        assertThat( result, is( true ) );
    }

    @Test
    public void transitionEventEvaluationForNoEvents() throws StateMachineException {
        StateTransitionImpl target = new StateTransitionImpl( "testTransition", startState, endState );

        StateTransitionEvent evt = new StateTransitionEventImpl( "testEvent" );

        when( context.getStateTransitionEvent() ).thenReturn( evt );

        boolean result = target.evaluateTransitionValidity( context );
        assertThat( result, is( true ) );
    }

    @Test
    public void transitionEventEvaluationForNoValidEvents() throws StateMachineException {
        StateTransitionImpl target = new StateTransitionImpl( "testTransition", startState, endState );
        StateTransitionEvent evt = new StateTransitionEventImpl( "testEvent" );
        target.addTransitionEvent( evt );

        when( context.getStateTransitionEvent() ).thenReturn( new StateTransitionEventImpl( "notMatchingEvent" ) );

        boolean result = target.evaluateTransitionValidity( context );
        assertThat( result, is( false ) );
    }

    @Test
    public void transitionEventEvaluationForNullEvents() throws StateMachineException {
        StateTransitionImpl target = new StateTransitionImpl( "testTransition", startState, endState );
        target.setTransitionEvents( null );

        when( context.getStateTransitionEvent() ).thenReturn( new StateTransitionEventImpl( "notMatchingEvent" ) );

        boolean result = target.evaluateTransitionValidity( context );
        assertThat( result, is( true ) );
    }

    @Test
    public void transitionEventEvaluationForValidGuard() throws StateMachineException {
        StateTransitionImpl target = new StateTransitionImpl( "testTransition", startState, endState );
        target.addTransitionGuard( new TrueGuardStub() );

        boolean result = target.evaluateTransitionValidity( context );
        assertThat( result, is( true ) );
    }

    @Test
    public void transitionEventEvaluationForValidGuardAndInvalidGuard() throws StateMachineException {
        StateTransitionImpl target = new StateTransitionImpl( "testTransition", startState, endState );
        target.addTransitionGuard( new FalseGuardStub() );
        target.addTransitionGuard( new TrueGuardStub() );

        boolean result = target.evaluateTransitionValidity( context );
        assertThat( result, is( false ) );
    }

    @Test
    public void transitionEventEvaluationForNoGuards() throws StateMachineException {

        StateTransitionImpl target = new StateTransitionImpl( "testTransition", startState, endState );

        boolean result = target.evaluateTransitionValidity( context );
        assertThat( result, is( true ) );
    }

    @Test
    public void transitionEventEvaluationForNoValidGuards() throws StateMachineException {

        StateTransitionImpl target = new StateTransitionImpl( "testTransition", startState, endState );
        target.addTransitionGuard( new FalseGuardStub() );

        boolean result = target.evaluateTransitionValidity( context );
        assertThat( result, is( false ) );
    }

    @Test
    public void transitionEventEvaluationForNullGuards() throws StateMachineException {

        StateTransitionImpl target = new StateTransitionImpl( "testTransition", startState, endState );
        target.setTransitionGuards( null );

        boolean result = target.evaluateTransitionValidity( context );
        assertThat( result, is( true ) );
    }

}
