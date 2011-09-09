package com.ubs.openspaces.tdd;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Test class for Portfolio behaviour.
 * <p/>
 * User: suggitpe
 * Date: 08/09/11
 * Time: 19:13
 */

public final class PortfolioTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( PortfolioTest.class );
    private Portfolio portfolio;

    private static final Trade STOCK = new Stock( "FOO.L" );
    private static final Trade OPTION = new StockOption( "BAR.L", 35 );

    @Before
    public void onSetup() {
        portfolio = new Portfolio();
        PriceService priceService = Mockito.mock( PriceService.class );
        when( priceService.getPriceFor( "FOO.L" ) ).thenReturn( 5 );
        when( priceService.getPriceFor( "BAR.L" ) ).thenReturn( 50 );
        portfolio.addPriceService( priceService );
    }

    @Test
    public void shouldCalculateValueWithStocks() {
        portfolio.addNumberOfTrades( STOCK, 10 );
        int valueOfPortfolio = portfolio.calculateValue();
        assertThat( valueOfPortfolio, equalTo( 10 * 5 ) );
    }

    @Test
    public void shouldCalculateValueWithOptions() {
        portfolio.addNumberOfTrades( OPTION, 25 );
        int valueOfPortfolio = portfolio.calculateValue();
        assertThat( valueOfPortfolio, equalTo( 25 * ( 50 - 35 ) ) );
    }

    @Test
    public void shouldCalculateValueWithStockOptionsMix() {
        portfolio.addNumberOfTrades( STOCK, 34 );
        portfolio.addNumberOfTrades( OPTION, 74 );
        int valueOfPortfolio = portfolio.calculateValue();
        assertThat( valueOfPortfolio, equalTo( ( 34 * 5 ) + ( 74 * ( 50 - 35 ) ) ) );
    }
}
