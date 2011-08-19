package com.ubs.gfit.openspaces.ddd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent a StockTradeImpl.
 * <p/>
 * User: suggitpe
 * Date: 19/08/11
 * Time: 13:23
 */

public final class StockTradeImpl implements Trade {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( StockTradeImpl.class );

    private String stockSymbol;
    private float purchasePrice;

    public StockTradeImpl( String aStockSymbol, float aPurchasePrice ) {
        stockSymbol = aStockSymbol;
        purchasePrice = aPurchasePrice;
    }

    @Override
    public float calculateMarkToMarketValue( MarketObservationService aMarketObservationService ) {
        return aMarketObservationService.getCurrentPriceForSymbol( stockSymbol ) - purchasePrice;
    }

    @Override
    public String describeTrade() {
        return "Stock:"+stockSymbol;
    }
}
