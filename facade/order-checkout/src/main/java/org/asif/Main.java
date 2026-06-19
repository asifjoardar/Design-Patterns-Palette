package org.asif;

import org.asif.checkout.OrderCheckoutFacade;
import org.asif.checkout.OrderResult;
import org.asif.subsystems.InventoryService;
import org.asif.subsystems.NotificationService;
import org.asif.subsystems.PaymentService;
import org.asif.subsystems.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo: a client places an order with a single, simple call and never touches
 * the inventory, payment, shipping, or notification subsystems directly.
 */
public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final String SKU = "BOOK-GOF-1994";
    private static final int INITIAL_STOCK = 5;
    private static final int ORDER_QUANTITY = 2;
    private static final double ORDER_AMOUNT = 89.97;

    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(final String[] args) {
        // Wire the subsystems once...
        final InventoryService inventory = new InventoryService();
        final PaymentService payment = new PaymentService();
        final ShippingService shipping = new ShippingService();
        final NotificationService notifications = new NotificationService();
        inventory.addStock(SKU, INITIAL_STOCK);

        // ...then hide them all behind the facade.
        final OrderCheckoutFacade checkout =
                new OrderCheckoutFacade(inventory, payment, shipping, notifications);

        final OrderResult ok = checkout.placeOrder(
                "ada@example.com", SKU, ORDER_QUANTITY, ORDER_AMOUNT, "10 Downing St");
        LOGGER.info("Result: {}", ok);

        // A second, oversized order shows the facade handling the unhappy path too.
        final OrderResult tooMany = checkout.placeOrder(
                "bob@example.com", SKU, INITIAL_STOCK, ORDER_AMOUNT, "221B Baker St");
        LOGGER.info("Result: {}", tooMany);
    }
}
