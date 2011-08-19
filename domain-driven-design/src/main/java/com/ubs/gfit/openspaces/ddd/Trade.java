package com.ubs.gfit.openspaces.ddd;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 19/08/11
 * Time: 13:58
 */
public interface Trade {

    float calculateMarkToMarketValue( MarketObservationService aMarketObservationService );

    String describeTrade();
}
