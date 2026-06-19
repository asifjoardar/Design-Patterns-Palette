package org.asif.subsystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Subsystem that charges the customer and returns a transaction reference.
 */
public class PaymentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    /**
     * Charges the customer the given amount.
     *
     * @param customer the customer's email
     * @param amount   the amount to charge
     * @return a transaction id for the successful charge
     */
    public String charge(final String customer, final double amount) {
        final String transactionId = "TXN-" + UUID.randomUUID();
        LOGGER.info("Charged {} {} -> {}", customer, amount, transactionId);
        return transactionId;
    }
}
