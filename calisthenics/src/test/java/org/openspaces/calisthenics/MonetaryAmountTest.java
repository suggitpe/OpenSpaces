package org.openspaces.calisthenics;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class for the Monetary Amount class.
 * <p/>
 * User: suggitpe Date: 14/10/11 Time: 08:24
 */

public class MonetaryAmountTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( MonetaryAmountTest.class );

    MonetaryAmount amount;

    @Before
    public void setup() {
        amount = new MonetaryAmount( 100.0 );
    }

    @Test
    public void shouldEqualSameDistinctMonetaryAmount() {
        assertThat( new MonetaryAmount( 10.0 ), is( equalTo( new MonetaryAmount( 10.0 ) ) ) );
    }

    @Test
    public void shouldNotEqualDifferentMonetaryAmount() {
        assertThat( new MonetaryAmount( 20.0 ), is( not( equalTo( new MonetaryAmount( 10.0 ) ) ) ) );
    }

    @Test
    public void shouldAllowTwoAmountsToBeAdded() {
        amount = amount.add( new MonetaryAmount( 10.0 ) );
        assertThat( amount, is( equalTo( new MonetaryAmount( 110.0 ) ) ) );
    }

    @Test
    public void shouldAllowTwoAmountsToBeSubtracted() {
        amount = amount.subtract( new MonetaryAmount( 10.0 ) );
        assertThat( amount, is( equalTo( new MonetaryAmount( 90.0 ) ) ) );
    }

}
