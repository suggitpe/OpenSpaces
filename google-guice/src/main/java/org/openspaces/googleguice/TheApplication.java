package org.openspaces.googleguice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * User: suggitpe
 * Date: 25/07/12
 * Time: 12:55
 */
public class TheApplication {

    private static final Logger LOG = LoggerFactory.getLogger(TheApplication.class);
    private Reader reader;
    private Repository repository;

    @Inject
    public TheApplication(Reader aReader, Repository aRepository){
        reader = aReader;
        repository = aRepository;
    }

    public void transferThingaMeBobs(){
        ThingaMeBob thingaMeBob;
        while((thingaMeBob = reader.readThingaMeBob())!= null){
            LOG.info("Transfering a thingy");
            repository.writeThingaMeBob(thingaMeBob);
        }
    }
}
