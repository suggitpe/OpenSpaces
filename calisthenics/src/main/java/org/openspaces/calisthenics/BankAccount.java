package org.openspaces.calisthenics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent an acocunt of money.
 * <p/>
 * User: suggitpe Date: 14/10/11 Time: 07:27
 */

public class BankAccount {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( BankAccount.class );

    MonetaryAmount balance;

    public BankAccount( MonetaryAmount aMonetaryMount ) {
        balance = aMonetaryMount;
    }

    public void deposit( MonetaryAmount aMonetaryAmount ) {
        balance = balance.add( aMonetaryAmount );
    }

    public MonetaryAmount withdraw( MonetaryAmount aMonetaryAmount ) {
        balance = balance.subtract( aMonetaryAmount );
        return aMonetaryAmount;
    }

    public void transferTo( BankAccount aReceiverAccount, MonetaryAmount aMonetaryAmount ) {
        aReceiverAccount.deposit( withdraw( aMonetaryAmount ) );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof BankAccount ) ) return false;

        BankAccount that = ( BankAccount ) o;

        if ( balance != null ? !balance.equals( that.balance ) : that.balance != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return balance != null ? balance.hashCode() : 0;
    }

    public BankAccountStatement createStatement() {
        return new BankAccountStatement();
    }
}
