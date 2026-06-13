package org.asif.publisher;

import org.asif.listeners.RecordingEventListener;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventManagerTest {

    private static final String OPEN = "open";
    private static final String SAVE = "save";

    @Test
    void subscribedListenerReceivesNotification() {
        EventManager manager = new EventManager(OPEN, SAVE);
        RecordingEventListener listener = new RecordingEventListener();
        manager.subscribe(OPEN, listener);

        manager.notify(OPEN, new File("a.txt"));

        assertEquals(List.of("open:a.txt"), listener.getReceived());
    }

    @Test
    void allSubscribersOfAnEventAreNotified() {
        EventManager manager = new EventManager(OPEN);
        RecordingEventListener first = new RecordingEventListener();
        RecordingEventListener second = new RecordingEventListener();
        manager.subscribe(OPEN, first);
        manager.subscribe(OPEN, second);

        manager.notify(OPEN, new File("b.txt"));

        assertEquals(List.of("open:b.txt"), first.getReceived());
        assertEquals(List.of("open:b.txt"), second.getReceived());
    }

    @Test
    void unsubscribedListenerStopsReceivingNotifications() {
        EventManager manager = new EventManager(OPEN);
        RecordingEventListener listener = new RecordingEventListener();
        manager.subscribe(OPEN, listener);
        manager.unsubscribe(OPEN, listener);

        manager.notify(OPEN, new File("c.txt"));

        assertTrue(listener.getReceived().isEmpty());
    }

    @Test
    void notifyWithNoSubscribersDoesNotThrow() {
        EventManager manager = new EventManager(SAVE);

        assertDoesNotThrow(() -> manager.notify(SAVE, new File("d.txt")));
    }
}
