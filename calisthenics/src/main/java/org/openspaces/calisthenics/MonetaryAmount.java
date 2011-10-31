package org.openspaces.calisthenics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that will represent an amount of money.
 * <p/>
 * User: suggitpe Date: 14/10/11 Time: 07:25
 */

public class MonetaryAmount {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( MonetaryAmount.class );

    private double amount;

    public MonetaryAmount( double anAmount ) {
        amount = anAmount;
    }

    public MonetaryAmount add( MonetaryAmount aMonetaryAmount ) {
        return new MonetaryAmount( amount + aMonetaryAmount.amount );
    }

    public MonetaryAmount subtract( MonetaryAmount aMonetaryAmount ) {
        return new MonetaryAmount( amount - aMonetaryAmount.amount );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof MonetaryAmount ) ) return false;

        MonetaryAmount that = ( MonetaryAmount ) o;

        if ( Double.compare( that.amount, amount ) != 0 ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = amount != +0.0d ? Double.doubleToLongBits( amount ) : 0L;
        return ( int ) ( temp ^ ( temp >>> 32 ) );
    }
}
