package org.asif.buttons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTML button implementation.
 */
public class HtmlButton implements Button {
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlButton.class);

    public void render() {
        LOGGER.info("<button>Test Button</button>");
        onClick();
    }

    public void onClick() {
        LOGGER.info("Click! Button says - 'Hello World!'");
    }
}
