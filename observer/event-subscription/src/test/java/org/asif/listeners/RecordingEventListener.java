package org.asif.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Hand-written test double that records the events it receives, so tests can
 * assert exactly what a subject published.
 */
public class RecordingEventListener implements EventListener {
    private final List<String> received = new ArrayList<>();

    @Override
    public void update(String eventType, File file) {
        received.add(eventType + ":" + file.getName());
    }

    public List<String> getReceived() {
        return received;
    }
}
