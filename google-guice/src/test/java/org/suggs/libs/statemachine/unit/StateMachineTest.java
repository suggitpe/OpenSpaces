/*
 * StateMachineTest.java created on 26 Aug 2009 07:09:34 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.State;
import org.suggs.libs.statemachine.StateMachine;
import org.suggs.libs.statemachine.StateMachineContext;
import org.suggs.libs.statemachine.StateMachineException;
import org.suggs.libs.statemachine.impl.StateMachineImpl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite for the state machine implementation.
 *
 * @author suggitpe
 * @version 1.0 26 Aug 2009
 */
public class StateMachineTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( StateMachineTest.class );

    private State mockInitialState;
    private State mockNewState;
    private StateMachineContext mockContext;

    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "===================" + StateMachineTest.class.getSimpleName() );
    }

    @Before
    public void doBefore() {
        mockInitialState = mock( State.class );
        mockNewState = mock( State.class );
        mockContext = mock( StateMachineContext.class );
    }

    @Test
    public void stateMachineInitiatesToInitialState() {
        when( mockInitialState.getStateName() ).thenReturn( "InitialState" );

        StateMachine stateMachine = new StateMachineImpl( mockInitialState );
        State curState = stateMachine.getCurrentState();

        assertThat( mockInitialState, equalTo( curState ) );
    }

    @Test
    public void stepResultsInNewCurrentState() throws StateMachineException {
        when( mockInitialState.step( mockContext ) ).thenReturn( mockNewState );
        when( mockNewState.step( mockContext ) ).thenReturn( mockNewState );

        StateMachine stateMachine = new StateMachineImpl( mockInitialState );
        stateMachine.step( mockContext );

        assertThat( stateMachine.getCurrentState(), equalTo( mockNewState ) );
    }

    @Test
    public void noStepResultsInSameCurrentState() throws StateMachineException {
        when( mockInitialState.step( mockContext ) ).thenReturn( mockInitialState );

        StateMachine stateMachine = new StateMachineImpl( mockInitialState );
        stateMachine.step( mockContext );

        assertThat( stateMachine.getCurrentState(), equalTo( mockInitialState ) );
    }

    @Test
    public void nullStepResultsInSameCurrentState() throws StateMachineException {
        when( mockInitialState.step( mockContext ) ).thenReturn( null );

        StateMachine stateMachine = new StateMachineImpl( mockInitialState );
        stateMachine.step( mockContext );

        assertThat( stateMachine.getCurrentState(), equalTo( mockInitialState ) );
    }
}
