package org.openspaces.googleguice;

import java.util.ArrayList;
import java.util.List;

/**
 * User: suggitpe
 * Date: 25/07/12
 * Time: 13:38
 */
public class DaoStub implements DAO {

    private List<ThingaMeBob> listOfThings = new ArrayList<ThingaMeBob>();

    @Override
    public void persist(ThingaMeBob aThingaMeBob) {
        listOfThings.add(aThingaMeBob);
    }

    public int count() {
        return listOfThings.size();
    }
}
