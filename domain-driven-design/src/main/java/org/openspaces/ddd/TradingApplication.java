package org.openspaces.ddd;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent a trading application.
 * <p/>
 * User: suggitpe
 * Date: 19/08/11
 * Time: 13:22
 */

public final class TradingApplication {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TradingApplication.class );

    private List<Trade> trades = new ArrayList<Trade>();

    public void addNewTrade( Trade aTrade ) {
        trades.add( aTrade );
    }

    public int getNumberOfTrades() {
        return trades.size();
    }

    public void calculateMarkToMarketValues( MarketObservationService aMarketObservationService ) {
        for ( Trade trade : trades ) {
            LOG.debug( "MTM for trade [" + trade.describeTrade() + "]: " + trade.calculateMarkToMarketValue( aMarketObservationService ) );
        }
    }
}

