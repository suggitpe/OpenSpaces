package org.openspaces.calisthenics;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class for the Bank Account.
 * <p/>
 * User: suggitpe Date: 14/10/11 Time: 07:23
 */

public class BankAccountTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( BankAccountTest.class );

    BankAccount bankAccountUnderTest;

    @Before
    public void setup() {
        bankAccountUnderTest = new BankAccount( new MonetaryAmount( 100.0 ) );
    }

    @Test
    public void shouldKnowWhenSameAsOtherBankAccounts() {
        assertThat( bankAccountWithAmountOf( 20.0 ), is( equalTo( bankAccountWithAmountOf( 20.0 ) ) ) );
    }

    @Test
    public void shouldKnowWhenDifferentToOtherBankAccounts() {
        assertThat( bankAccountWithAmountOf( 20.0 ), is( not( equalTo( bankAccountWithAmountOf( 10.0 ) ) ) ) );
    }

    @Test
    public void shouldAllowDeposits() {
        bankAccountUnderTest.deposit( new MonetaryAmount( 10.0 ) );
        assertThat( bankAccountUnderTest, is( equalTo( bankAccountWithAmountOf( 110.0 ) ) ) );
    }

    @Test
    public void shouldAllowWithdrawals() {
        bankAccountUnderTest.withdraw( new MonetaryAmount( 10.0 ) );
        assertThat( bankAccountUnderTest, is( equalTo( bankAccountWithAmountOf( 90.0 ) ) ) );
    }

    @Test
    public void shouldAllowToTransferFromOneAccountToAnother() {
        BankAccount receiverAccount = bankAccountWithAmountOf( 20.0 );
        bankAccountUnderTest.transferTo( receiverAccount, new MonetaryAmount( 10.0 ) );
        assertThat( bankAccountUnderTest, is( equalTo( bankAccountWithAmountOf( 90.0 ) ) ) );
        assertThat( receiverAccount, is( equalTo( bankAccountWithAmountOf( 30.0 ) ) ) );
    }

    @Test
    public void shouldAllowCreationOfAccountStatement() {
        BankAccountStatement statement = bankAccountUnderTest.createStatement();
        assertThat( statement, is( not( nullValue() ) ));
    }

    private BankAccount bankAccountWithAmountOf( double anAmount ) {
        return new BankAccount( new MonetaryAmount( anAmount ) );
    }
}
