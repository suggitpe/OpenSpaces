/*
 * StateTransitionEventTest.java created on 2 Sep 2009 07:11:50 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.StateTransitionEvent;
import org.suggs.libs.statemachine.impl.StateTransitionEventImpl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Test suite for the state transition event implementation.
 *
 * @author suggitpe
 * @version 1.0 2 Sep 2009
 */
public class StateTransitionEventTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( StateTransitionEventTest.class );

    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "===================" + StateTransitionEventTest.class.getSimpleName() );
    }

    @Test
    public void stateNameExtraction() {
        final String EVENT_NAME = "TestEvent";
        StateTransitionEvent event = new StateTransitionEventImpl( EVENT_NAME );

        assertThat( event.getEventName(), equalTo( EVENT_NAME ) );
    }
}
