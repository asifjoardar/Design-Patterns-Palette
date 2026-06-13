package org.asif.checkboxes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All products families have the same varieties (macOS/Windows/Linux).
 * <p>
 * This is a variant of a checkbox.
 */
public class LinuxCheckbox implements Checkbox {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinuxCheckbox.class);

    @Override
    public void paint() {
        LOGGER.info("You have created LinuxCheckbox.");
    }
}
