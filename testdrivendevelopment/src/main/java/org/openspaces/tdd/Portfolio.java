package org.openspaces.tdd;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to allow us to build a portfolio of Trades and perform simple functions on that portfolio.
 * <p/>
 * User: suggitpe
 * Date: 08/09/11
 * Time: 19:18
 */

public final class Portfolio {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Portfolio.class );

    PriceService priceService;
    Map<Trade, Integer> trades = new HashMap<Trade, Integer>();

    public void addPriceService( PriceService aPriceService ) {
        priceService = aPriceService;
    }

    public void addNumberOfTrades( Trade aTrade, int aQuantityOfTrades ) {
        if ( trades.containsKey( aTrade ) ) {
            trades.put( aTrade, trades.get( aTrade ) + aQuantityOfTrades );
        }
        else {
            trades.put( aTrade, aQuantityOfTrades );
        }
    }

    public int calculateValue() {
        int total = 0;
        for ( Trade trade : trades.keySet() ) {
            total += trades.get( trade ) * trade.calculateTradePrice( priceService );
        }
        return total;
    }
}
