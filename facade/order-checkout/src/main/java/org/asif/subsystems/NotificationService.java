package org.asif.subsystems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Subsystem that emails the customer. It keeps a record of what it sent so the
 * rest of the system (and our tests) can confirm the customer was told.
 */
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    private final List<String> sentMessages = new ArrayList<>();

    /**
     * Sends an email to the customer.
     *
     * @param customer the recipient's email
     * @param message  the message body
     */
    public void emailCustomer(final String customer, final String message) {
        sentMessages.add(customer + ": " + message);
        LOGGER.info("Emailed {} -> {}", customer, message);
    }

    /**
     * @return an immutable view of every message sent so far
     */
    public List<String> getSentMessages() {
        return List.copyOf(sentMessages);
    }
}
