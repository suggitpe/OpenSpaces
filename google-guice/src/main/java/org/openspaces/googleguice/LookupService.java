package org.openspaces.googleguice;

import com.google.inject.ImplementedBy;
import org.openspaces.googleguice.impl.ReallySensitiveLookupDataService;

/**
 * User: suggitpe
 * Date: 25/07/12
 * Time: 13:03
 */
public interface LookupService {
    boolean reallyImportantLookup(ThingaMeBob aThingaMeBob);
}
