package org.asif;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(String[] args) {
        // Two different parts of the app ask for the configuration...
        ConfigurationManager first = ConfigurationManager.getInstance();
        ConfigurationManager second = ConfigurationManager.getInstance();

        // ...and they both receive the exact same object (note the single "loaded once" log line above).
        LOGGER.info("Is the same instance shared everywhere? {}", first == second);

        LOGGER.info("App name    : {}", first.get("app.name"));
        LOGGER.info("App version : {}", first.get("app.version"));
        LOGGER.info("Environment : {}", second.get("app.environment"));
    }
}
