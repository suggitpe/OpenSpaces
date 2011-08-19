package com.ubs.gfit.openspaces.ddd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 19/08/11
 * Time: 14:00
 */

public final class OptionTradeImpl implements Trade {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( OptionTradeImpl.class );

    private String stockSymbol;
    private float strikePrice;
    private float premiumPrice;

    public OptionTradeImpl( String aStockSymbol, float aStrikePrice, float aPremiumPrice ) {
        stockSymbol = aStockSymbol;
        strikePrice = aStrikePrice;
        premiumPrice = aPremiumPrice;
    }

    public float calculateMarkToMarketValue( MarketObservationService aMarketObservationService ) {
        return aMarketObservationService.getCurrentPriceForSymbol( stockSymbol ) - strikePrice - premiumPrice;
    }

    @Override
    public String describeTrade() {
        return "Option:" + stockSymbol;
    }
}
