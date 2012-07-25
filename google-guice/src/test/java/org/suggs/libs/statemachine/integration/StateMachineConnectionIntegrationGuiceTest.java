package org.suggs.libs.statemachine.integration;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.suggs.libs.statemachine.*;
import org.suggs.libs.statemachine.impl.StateTransitionEventImpl;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class StateMachineConnectionIntegrationGuiceTest {

    private StateMachine stateMachine;

    protected State disconnectedState;
    protected State connectedState;
    protected State initialState;

    @Before
    public void setup() {
        Injector injector = Guice.createInjector(new StateMachineModule());
        stateMachine = injector.getInstance(StateMachine.class);
    }

    private class StateMachineModule extends AbstractModule {

        @Override
        protected void configure() {
        }
    }

    @Test
    public void initialisationOfStateMachine() throws StateMachineException {
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
