package com.ubs.openspaces.tdd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete class to represent a Stock Option.
 * <p/>
 * User: suggitpe
 * Date: 08/09/11
 * Time: 19:38
 */

public final class StockOption extends Trade {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( StockOption.class );
    int strikePrice;

    public StockOption( String aSymbol, int aStrikePrice ) {
        super( aSymbol );
        strikePrice = aStrikePrice;
    }

    @Override
    protected int doCalculateTradePrice( int aSymbolPrice ) {
        return aSymbolPrice - strikePrice;
    }
}
