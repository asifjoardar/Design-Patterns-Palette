package org.asif.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class LogOpenListener implements EventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogOpenListener.class);

    private final File log;

    public LogOpenListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(String eventType, File file) {
        LOGGER.info("Save to log {}: Someone has performed {} operation with the following file: {}",
                log, eventType, file.getName());
    }
}
