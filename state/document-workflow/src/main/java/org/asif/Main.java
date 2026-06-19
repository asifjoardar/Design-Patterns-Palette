package org.asif;

import org.asif.workflow.Document;
import org.asif.workflow.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo: drive a document through its publishing workflow. The very same
 * {@code publish()} call does something different at each stage — and even
 * depends on who is asking once the document is under review.
 */
public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(final String[] args) {
        final Document doc = new Document("State Pattern Explained", Role.AUTHOR);
        LOGGER.info("Start: {}", doc.currentState());          // Draft

        doc.publish();
        LOGGER.info("After author submits: {}", doc.currentState());   // Moderation

        doc.publish();
        LOGGER.info("After author tries to approve: {}", doc.currentState()); // still Moderation

        doc.actAs(Role.ADMIN);
        doc.publish();
        LOGGER.info("After admin approves: {}", doc.currentState());   // Published

        doc.publish();
        LOGGER.info("After publishing again: {}", doc.currentState()); // still Published
    }
}
