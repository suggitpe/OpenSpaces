package org.openspaces.googleguice.impl;

import org.openspaces.googleguice.Reader;
import org.openspaces.googleguice.ThingaMeBob;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URL;

/**
 * User: suggitpe
 * Date: 25/07/12
 * Time: 12:55
 */
public class FileReader implements Reader {

    private URL fileLocation;

    public FileReader(String aFileUrl){
        fileLocation = this.getClass().getResource(aFileUrl);
    }

    @Override
    public ThingaMeBob readThingaMeBob() {
        throw new NotImplementedException();
    }
}
