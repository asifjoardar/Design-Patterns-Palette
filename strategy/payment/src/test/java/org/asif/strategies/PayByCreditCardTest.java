package org.asif.strategies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PayByCreditCardTest {

    private static final int AMOUNT = 100;

    @Test
    void payFailsWhenCardDetailsNotCollected() {
        PayStrategy strategy = new PayByCreditCard();

        assertFalse(strategy.pay(AMOUNT),
                "Payment must fail until valid card details have been collected");
    }
}
