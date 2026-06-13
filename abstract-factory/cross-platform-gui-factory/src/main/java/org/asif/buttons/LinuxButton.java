package org.asif.buttons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All products families have the same varieties (macOS/Windows/Linux).
 * <p>
 * This is a Linux variant of a button.
 */
public class LinuxButton implements Button {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinuxButton.class);

    @Override
    public void paint() {
        LOGGER.info("You have created LinuxButton.");
    }
}
