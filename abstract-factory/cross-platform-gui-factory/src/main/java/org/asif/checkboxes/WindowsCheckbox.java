package org.asif.checkboxes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All products families have the same varieties (macOS/Windows).
 * <p>
 * This is another variant of a checkbox.
 */
public class WindowsCheckbox implements Checkbox {
    private static final Logger LOGGER = LoggerFactory.getLogger(WindowsCheckbox.class);

    @Override
    public void paint() {
        LOGGER.info("You have created WindowsCheckbox.");
    }
}
