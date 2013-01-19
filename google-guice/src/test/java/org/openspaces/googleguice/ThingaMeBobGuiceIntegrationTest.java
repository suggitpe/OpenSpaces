package org.openspaces.googleguice;

import com.google.inject.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.openspaces.googleguice.impl.ReallySensitiveLookupDataService;
import org.openspaces.googleguice.impl.ThingaMeBobRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

/**
 *
 */
public class ThingaMeBobGuiceIntegrationTest {

    protected TheApplication worker;
    protected DAO dao;

    // TODO: populate the members

    @Before
    public void setup() {
        Injector injector = Guice.createInjector(new ThingyModule());
        worker = injector.getInstance(TheApplication.class);
        dao = injector.getInstance(DAO.class);
    }

    @Test
    public void tranferThingaMeBobsFromSourceToRepository() {
        worker.transferThingaMeBobs();
        verify(dao).persist(Matchers.any(ThingaMeBob.class));
    }


    private class ThingyModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(Repository.class).to(ThingaMeBobRepository.class).in(Scopes.SINGLETON);
            bind(LookupService.class).to(ReallySensitiveLookupDataService.class).in(Scopes.SINGLETON);


        }

        @Provides
        @Singleton
        public Reader getReader() {
            Reader mock = mock(Reader.class);
            stub(mock.readThingaMeBob()).toReturn(new ThingaMeBob("test")).toReturn(null);
            return mock;
        }

        @Provides
        @Singleton
        public DAO getDao(){
            return mock(DAO.class);
        }

    }
}
