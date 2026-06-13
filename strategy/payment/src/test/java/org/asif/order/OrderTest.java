package org.asif.order;

import org.asif.strategies.PayStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verifies the Strategy pattern context: {@link Order} delegates to whatever
 * {@link PayStrategy} it is given, without knowing the concrete implementation.
 */
class OrderTest {

    private static final int FIRST_COST = 100;
    private static final int SECOND_COST = 50;

    @Test
    void processOrderDelegatesToStrategy() {
        StubPayStrategy strategy = new StubPayStrategy(true);
        Order order = new Order();

        order.processOrder(strategy);

        assertTrue(strategy.isCollectCalled(),
                "Order should delegate payment-detail collection to the strategy");
    }

    @Test
    void setTotalCostAccumulates() {
        Order order = new Order();

        order.setTotalCost(FIRST_COST);
        order.setTotalCost(SECOND_COST);

        assertEquals(FIRST_COST + SECOND_COST, order.getTotalCost());
    }

    @Test
    void orderIsOpenUntilClosed() {
        Order order = new Order();

        assertTrue(order.isClosed(), "A new order should still be open");

        order.setClosed();

        assertFalse(order.isClosed(), "Order should be closed after setClosed()");
    }

    /**
     * Hand-written test double standing in for any concrete strategy.
     */
    private static final class StubPayStrategy implements PayStrategy {
        private final boolean payResult;
        private boolean collectCalled;

        StubPayStrategy(boolean payResult) {
            this.payResult = payResult;
        }

        @Override
        public boolean pay(int paymentAmount) {
            return payResult;
        }

        @Override
        public void collectPaymentDetails() {
            collectCalled = true;
        }

        boolean isCollectCalled() {
            return collectCalled;
        }
    }
}
