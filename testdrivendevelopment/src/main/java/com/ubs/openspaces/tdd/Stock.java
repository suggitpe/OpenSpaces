package com.ubs.openspaces.tdd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete implementation of a Stock class.
 * <p/>
 * User: suggitpe
 * Date: 08/09/11
 * Time: 19:19
 */

public final class Stock extends Trade {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Stock.class );

    public Stock( String aSymbol ) {
        super( aSymbol );
    }

    @Override
    protected int doCalculateTradePrice( int aSymbolPrice ) {
        return aSymbolPrice;
    }

}
