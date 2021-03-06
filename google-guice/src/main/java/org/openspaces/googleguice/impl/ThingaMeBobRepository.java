package org.openspaces.googleguice.impl;

import org.omg.DynamicAny.DynAnyOperations;
import org.openspaces.googleguice.DAO;
import org.openspaces.googleguice.LookupService;
import org.openspaces.googleguice.Repository;
import org.openspaces.googleguice.ThingaMeBob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * User: suggitpe
 * Date: 25/07/12
 * Time: 13:02
 */
public class ThingaMeBobRepository implements Repository {

    private static final Logger LOG = LoggerFactory.getLogger(ThingaMeBobRepository.class);
    private LookupService lookupService;
    private DAO dao;

    @Inject
    public ThingaMeBobRepository(LookupService aLookupService, DAO aDao){
        lookupService = aLookupService;
        dao = aDao;
    }

    @Override
    public void writeThingaMeBob(ThingaMeBob aThingaMeBob) {
        boolean isOkToWriteTodao = lookupService.reallyImportantLookup(aThingaMeBob);
        if(!isOkToWriteTodao){
            throw new IllegalStateException("Woah, this is some really sensitive stuff here");
        }
        dao.persist(aThingaMeBob);
    }
}
