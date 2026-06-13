package org.asif.buttons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All products families have the same varieties (macOS/Windows).
 * <p>
 * This is another variant of a button.
 */
public class WindowsButton implements Button {
    private static final Logger LOGGER = LoggerFactory.getLogger(WindowsButton.class);

    @Override
    public void paint() {
        LOGGER.info("You have created WindowsButton.");
    }
}
