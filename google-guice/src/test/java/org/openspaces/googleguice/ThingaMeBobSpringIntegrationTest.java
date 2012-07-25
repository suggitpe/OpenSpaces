package org.openspaces.googleguice;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * User: suggitpe
 * Date: 25/07/12
 * Time: 13:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/thingamebob-integration-test.xml" })
public class ThingaMeBobSpringIntegrationTest {

    @Resource(name="transferer")
    protected ThingaMeBobTransfer worker;

    @Resource(name="reader")
    protected ReaderStub reader;

    @Resource(name="dao")
    protected DaoStub dao;

    @Test
    public void tranferThingaMeBobsFromSourceToRepository(){
        int count = reader.getNumberToReturn();
        worker.transferThingaMeBobs();
        assertThat(dao.count(), equalTo(count));
    }

}
