package org.asif.buttons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All products families have the same varieties (macOS/Windows/Linux).
 * <p>
 * This is a macOS variant of a button.
 */
public class MacOSButton implements Button {
    private static final Logger LOGGER = LoggerFactory.getLogger(MacOSButton.class);

    @Override
    public void paint() {
        LOGGER.info("You have created MacOSButton.");
    }
}
