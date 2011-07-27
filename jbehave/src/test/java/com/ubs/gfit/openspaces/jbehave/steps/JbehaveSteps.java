package com.ubs.gfit.openspaces.jbehave.steps;

import org.jbehave.core.annotations.BeforeStories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is where we define the steps that bind the story to the test execution.
 * <p/>
 * User: suggitpe
 * Date: 27/07/11
 * Time: 10:24
 */

public final class JbehaveSteps {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( JbehaveSteps.class );

    @BeforeStories
    public void onSetup() {
        LOG.debug( "Setting up JBehave stories" );
    }
}
