package org.asif.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class EmailNotificationListener implements EventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotificationListener.class);

    private final String email;

    public EmailNotificationListener(final String email) {
        this.email = email;
    }

    @Override
    public void update(String eventType, File file) {
        LOGGER.info("Email to {}: Someone has performed {} operation with the following file: {}",
                email, eventType, file.getName());
    }
}
