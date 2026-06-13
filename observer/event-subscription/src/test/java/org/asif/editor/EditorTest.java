package org.asif.editor;

import org.asif.listeners.RecordingEventListener;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EditorTest {

    @Test
    void editorExposesItsEventManager() {
        assertNotNull(new Editor().getEvents());
    }

    @Test
    void openingAFilePublishesOpenEvent() {
        Editor editor = new Editor();
        RecordingEventListener listener = new RecordingEventListener();
        editor.getEvents().subscribe("open", listener);

        editor.openFile("test.txt");

        assertEquals(List.of("open:test.txt"), listener.getReceived());
    }

    @Test
    void savingAFilePublishesSaveEvent() throws Exception {
        Editor editor = new Editor();
        RecordingEventListener listener = new RecordingEventListener();
        editor.getEvents().subscribe("save", listener);

        editor.openFile("test.txt");
        editor.saveFile();

        assertEquals(List.of("save:test.txt"), listener.getReceived());
    }

    @Test
    void savingBeforeOpeningThrows() {
        Editor editor = new Editor();

        assertThrows(Exception.class, editor::saveFile);
    }
}
