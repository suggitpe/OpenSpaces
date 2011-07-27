package com.ubs.gfit.openspaces.openspace;

import java.util.List;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 27/07/11
 * Time: 16:31
 */
public interface OpenSpaceSession {

    public List<LovelyPeople> peopleGiveUpTheirTime();

    public void participateInOpenSpace( List<LovelyPeople> aBunchOfLovelyPeople);

    public List<Boolean> assessValueGainedFromOpenSpace();



}
