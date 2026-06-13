package org.asif.strategies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PayByPayPalTest {

    private static final int AMOUNT = 100;

    @Test
    void payFailsWhenNotSignedIn() {
        PayStrategy strategy = new PayByPayPal();

        assertFalse(strategy.pay(AMOUNT),
                "Payment must fail until the user has signed in");
    }
}
