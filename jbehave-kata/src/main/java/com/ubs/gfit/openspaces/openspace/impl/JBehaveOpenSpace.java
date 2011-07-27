package com.ubs.gfit.openspaces.openspace.impl;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.openspaces.openspace.LovelyPeople;
import com.ubs.gfit.openspaces.openspace.OpenSpaceSession;

/**
 * Class to represent the open space on Jbehave;
 * <p/>
 * User: suggitpe
 * Date: 27/07/11
 * Time: 16:34
 */

public final class JBehaveOpenSpace implements OpenSpaceSession {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( JBehaveOpenSpace.class );

    @Override
    public List<LovelyPeople> peopleGiveUpTheirTime() {
        throw new NotImplementedException();
    }

    @Override
    public void participateInOpenSpace( List<LovelyPeople> aBunchOfLovelyPeople ) {
        throw new NotImplementedException();
    }

    @Override
    public List<Boolean> assessValueGainedFromOpenSpace() {
        throw new NotImplementedException();
    }
}
