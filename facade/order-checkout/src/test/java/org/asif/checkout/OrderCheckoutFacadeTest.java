package org.asif.checkout;

import org.asif.subsystems.InventoryService;
import org.asif.subsystems.NotificationService;
import org.asif.subsystems.PaymentService;
import org.asif.subsystems.ShippingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderCheckoutFacadeTest {

    private static final String SKU = "BOOK-GOF-1994";
    private static final int INITIAL_STOCK = 5;
    private static final int ORDER_QUANTITY = 2;
    private static final int REMAINING_STOCK = 3;
    private static final double AMOUNT = 89.97;

    private InventoryService inventory;
    private NotificationService notifications;
    private OrderCheckoutFacade checkout;

    @BeforeEach
    void setUp() {
        inventory = new InventoryService();
        notifications = new NotificationService();
        inventory.addStock(SKU, INITIAL_STOCK);
        checkout = new OrderCheckoutFacade(
                inventory, new PaymentService(), new ShippingService(), notifications);
    }

    @Test
    void successfulOrderDrivesEverySubsystem() {
        OrderResult result = checkout.placeOrder("ada@example.com", SKU, ORDER_QUANTITY, AMOUNT, "10 Downing St");

        assertTrue(result.success());
        assertNotNull(result.transactionId(), "payment subsystem should have run");
        assertNotNull(result.trackingNumber(), "shipping subsystem should have run");
        assertEquals(REMAINING_STOCK, inventory.available(SKU), "inventory should be decremented");
        assertEquals(1, notifications.getSentMessages().size(), "customer should be notified");
    }

    @Test
    void outOfStockOrderIsRejectedAndNoSubsystemsRun() {
        OrderResult result = checkout.placeOrder("bob@example.com", SKU, INITIAL_STOCK + 1, AMOUNT, "221B Baker St");

        assertFalse(result.success());
        assertEquals(INITIAL_STOCK, inventory.available(SKU), "stock must be untouched on rejection");
        assertTrue(notifications.getSentMessages().isEmpty(), "no email on rejection");
    }
}
