package org.openspaces.googleguice;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class ThingaMeBobGuiceIntegrationTest {

    // TODO: annotate
    protected TheApplication worker;

    // TODO: annotate
    protected ReaderStub reader;

    // TODO: annotate
    protected DaoStub dao;

    @Test
    public void tranferThingaMeBobsFromSourceToRepository(){
        int count = reader.getNumberToReturn();
        worker.transferThingaMeBobs();
        assertThat(dao.count(), equalTo(count));
    }

    // TODO: create a private inner class extending AbstractModule for the bindings

}
