package org.openspaces.googleguice;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class ThingaMeBobGuiceIntegrationTest {

    protected TheApplication worker;
    protected Reader reader;
    protected DAO dao;

    // TODO: populate the members

    @Test
    public void tranferThingaMeBobsFromSourceToRepository(){
        int count = ((ReaderStub)reader).getNumberToReturn();
        worker.transferThingaMeBobs();
        assertThat(((DaoStub)dao).count(), equalTo(count));
    }

    // TODO: create a private inner class extending AbstractModule for the bindings

}
