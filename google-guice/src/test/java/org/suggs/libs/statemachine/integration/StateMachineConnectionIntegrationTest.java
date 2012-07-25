/*
 * StateMachineConnectionIntegrationTest.java created on 3 Sep 2009 20:14:30 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.integration;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.suggs.libs.statemachine.*;
import org.suggs.libs.statemachine.impl.StateTransitionEventImpl;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Integration test that uses a spring injected state machine to replicate how the state machine library can
 * be used to navigate your way through a state machine. In this test we use the example of a simple
 * connection to show how the navigation process works.
 *
 * @author suggitpe
 * @version 1.0 3 Sep 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:xml/it-state-machine-connection-test-statemachine.xml"})
public class StateMachineConnectionIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(StateMachineConnectionIntegrationTest.class);

    @Resource(name = "stateMachine")
    protected StateMachine stateMachine;

    @Resource(name = "disconnectedState")
    protected State disconnectedState;

    @Resource(name = "initialState")
    protected State initialState;

    @Resource(name = "connectedState")
    protected State connectedState;

    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug("=================== Start: "
                + StateMachineConnectionIntegrationTest.class.getSimpleName());
    }

    @Test
    public void initialisationOfStateMachineThroughSpring() throws StateMachineException {
        State initial = stateMachine.getCurrentState();
        assertThat(initial, equalTo(initialState));
    }

    @Test
    public void transitionFromInitialToDisconnected() throws StateMachineException {
        State initial = stateMachine.getCurrentState();
        assertThat(initial, equalTo(initialState));

        stateMachine.step(new StateMachineContext() {

            @Override
            public StateTransitionEvent getStateTransitionEvent() {
                return new StateTransitionEventImpl("DumyEvent for initial test");
            }
        });

        State newState = stateMachine.getCurrentState();
        assertThat(newState, equalTo(disconnectedState));
    }

    @Test
    public void noTransitionOccursFromIrrelevantEvent() throws StateMachineException {
        assertThat(stateMachine.getCurrentState(), equalTo(disconnectedState));
        stateMachine.step(new StateMachineContext() {

            @Override
            public StateTransitionEvent getStateTransitionEvent() {
                return new StateTransitionEventImpl("notRelevantEvent");
            }
        });
        assertThat(stateMachine.getCurrentState(), equalTo(disconnectedState));
    }

    @Test
    public void transitionFromDisconnectedToConnected() throws StateMachineException {
        assertThat(stateMachine.getCurrentState(), equalTo(disconnectedState));
        stateMachine.step(new StateMachineContext() {

            @Override
            public StateTransitionEvent getStateTransitionEvent() {
                return new StateTransitionEventImpl("connect");
            }
        });
        assertThat(stateMachine.getCurrentState(), equalTo(connectedState));
    }

    @Test
    public void transitionFromConnectedToDisconnected() throws StateMachineException {
        assertThat(stateMachine.getCurrentState(), equalTo(connectedState));
        stateMachine.step(new StateMachineContext() {

            @Override
            public StateTransitionEvent getStateTransitionEvent() {
                return new StateTransitionEventImpl("disconnect");
            }
        });
        assertThat(stateMachine.getCurrentState(), equalTo(disconnectedState));
    }
}
