package org.asif.checkout;

import org.asif.subsystems.InventoryService;
import org.asif.subsystems.NotificationService;
import org.asif.subsystems.PaymentService;
import org.asif.subsystems.ShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <em>facade</em>. Placing an order really means coordinating four separate
 * subsystems in the right order — check stock, take payment, book shipping, tell
 * the customer. This class wraps that whole dance behind one method,
 * {@link #placeOrder}, so callers never have to know the steps or wire the
 * subsystems together themselves.
 *
 * <p>Note the facade does not <em>replace</em> the subsystems — they remain fully
 * usable on their own. It just offers a simple, convenient entry point for the
 * common case.</p>
 */
public final class OrderCheckoutFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCheckoutFacade.class);

    private final InventoryService inventory;
    private final PaymentService payment;
    private final ShippingService shipping;
    private final NotificationService notifications;

    public OrderCheckoutFacade(final InventoryService inventory,
                               final PaymentService payment,
                               final ShippingService shipping,
                               final NotificationService notifications) {
        this.inventory = inventory;
        this.payment = payment;
        this.shipping = shipping;
        this.notifications = notifications;
    }

    /**
     * Runs the full checkout flow for a single product order.
     *
     * @param customer the customer's email
     * @param sku      the product to buy
     * @param quantity how many units
     * @param amount   the total to charge
     * @param address  where to ship
     * @return the outcome, with payment and tracking references on success
     */
    public OrderResult placeOrder(final String customer, final String sku, final int quantity,
                                  final double amount, final String address) {
        LOGGER.info("Placing order: {} x {} for {}", quantity, sku, customer);

        if (!inventory.isInStock(sku, quantity)) {
            LOGGER.info("Order rejected: {} is out of stock", sku);
            return OrderResult.rejected("Out of stock: " + sku);
        }

        inventory.reserve(sku, quantity);
        final String transactionId = payment.charge(customer, amount);
        final String trackingNumber = shipping.scheduleDelivery(sku, quantity, address);
        notifications.emailCustomer(customer, "Your order is confirmed. Tracking: " + trackingNumber);

        return OrderResult.placed(transactionId, trackingNumber);
    }
}
