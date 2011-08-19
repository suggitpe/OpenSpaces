package com.ubs.gfit.openspaces.ddd;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for a trading application.
 * <p/>
 * User: suggitpe
 * Date: 19/08/11
 * Time: 13:18
 */

public final class TradingApplicationTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TradingApplicationTest.class );

    TradingApplication tradingApplication;
    MarketObservationService marketObservationService;


    @Before
    public void onSetup() {
        tradingApplication = new TradingApplication();
        marketObservationService = mock(MarketObservationService.class);
        when( marketObservationService.getCurrentPriceForSymbol("SYMBOL" )  ).thenReturn( 19.99F);
    }

    @Test
    public void shouldLetMeAddNewTradeToApplication() {
        tradingApplication.addNewTrade( new StockTradeImpl( "Foo", 9.99F ) );
        assertThat( tradingApplication.getNumberOfTrades(), equalTo( 1 ) );
    }

    @Test
    public void shouldCalculateAllMarkToMarketValuesForContainedTrades() {
        tradingApplication.addNewTrade( new StockTradeImpl( "SYMBOL", 9.99F ) );
        tradingApplication.calculateMarkToMarketValues( marketObservationService );
    }

    @Test
    public void shouldCalculateMarkToMarketValuesForAllTradeTypes(){
        tradingApplication.addNewTrade( new OptionTradeImpl( "SYMBOL", 1.99F, 1.50F ) );
        tradingApplication.calculateMarkToMarketValues( marketObservationService );
    }
}
