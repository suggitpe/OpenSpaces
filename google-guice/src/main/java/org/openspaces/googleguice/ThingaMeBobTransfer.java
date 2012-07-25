package org.openspaces.googleguice;

/**
 * User: suggitpe
 * Date: 25/07/12
 * Time: 12:55
 */
public class ThingaMeBobTransfer {

    private Reader reader;
    private Repository repository;

    public ThingaMeBobTransfer(Reader aReader, Repository aRepository){
        reader = aReader;
        repository = aRepository;
    }

    public void transferThingaMeBobs(){
        ThingaMeBob thingaMeBob;
        while((thingaMeBob = reader.readThingaMeBob())!= null){
            repository.writeThingaMeBob(thingaMeBob);
        }
    }
}
