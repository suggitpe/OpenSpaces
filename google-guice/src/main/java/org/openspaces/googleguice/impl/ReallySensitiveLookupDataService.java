package org.openspaces.googleguice.impl;

import org.openspaces.googleguice.LookupService;
import org.openspaces.googleguice.ThingaMeBob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: suggitpe
 * Date: 25/07/12
 * Time: 13:07
 */
public class ReallySensitiveLookupDataService implements LookupService {

    private static final Logger LOG = LoggerFactory.getLogger(ReallySensitiveLookupDataService.class);

    @Override
    public boolean reallyImportantLookup(ThingaMeBob aThingaMeBob) {
        if ((aThingaMeBob.getStuff().length() % 2 == 0)) {
            LOG.info("Phew safe to proceed");
            return true;
        }
        LOG.warn("Alert alert, call HR!!!");
        return false;
    }
}
