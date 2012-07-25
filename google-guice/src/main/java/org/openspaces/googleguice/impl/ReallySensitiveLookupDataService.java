package org.openspaces.googleguice.impl;

import org.openspaces.googleguice.LookupService;
import org.openspaces.googleguice.ThingaMeBob;

/**
 * User: suggitpe
 * Date: 25/07/12
 * Time: 13:07
 */
public class ReallySensitiveLookupDataService implements LookupService {

    @Override
    public boolean reallyImportantLookup(ThingaMeBob aThingaMeBob) {
        if ((aThingaMeBob.getStuff().length() % 2 == 0)) {
            return true;
        }
        return false;
    }
}
