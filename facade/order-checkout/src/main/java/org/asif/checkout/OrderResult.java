package org.asif.checkout;

/**
 * The simple outcome the caller gets back from the facade — success or failure,
 * plus the references it needs, without any of the subsystem detail.
 *
 * @param success        whether the order went through
 * @param message        a human-readable summary
 * @param transactionId  the payment reference (null when the order failed)
 * @param trackingNumber the shipment reference (null when the order failed)
 */
public record OrderResult(boolean success, String message, String transactionId, String trackingNumber) {

    /**
     * @param transactionId  the payment reference
     * @param trackingNumber the shipment reference
     * @return a successful result
     */
    public static OrderResult placed(final String transactionId, final String trackingNumber) {
        return new OrderResult(true, "Order placed", transactionId, trackingNumber);
    }

    /**
     * @param reason why the order could not be placed
     * @return a failed result
     */
    public static OrderResult rejected(final String reason) {
        return new OrderResult(false, reason, null, null);
    }
}
