package com.ubs.openspaces.tdd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Trade class to abstract the higher level concepts of a Trade.
 * <p/>
 * User: suggitpe
 * Date: 08/09/11
 * Time: 19:40
 */

public abstract class Trade {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Trade.class );
    private String symbol;

    public Trade( String aSymbol ) {
        symbol = aSymbol;
    }

    public int calculateTradePrice( PriceService aPriceService ) {
        return doCalculateTradePrice( aPriceService.getPriceFor( symbol ) );
    }

    protected abstract int doCalculateTradePrice( int aSymbolPrice );

}
