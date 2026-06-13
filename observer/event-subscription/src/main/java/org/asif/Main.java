package org.asif;

import org.asif.editor.Editor;
import org.asif.listeners.EmailNotificationListener;
import org.asif.listeners.LogOpenListener;

public final class Main {
    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.getEvents().subscribe("open", new LogOpenListener("/path/to/log/file.txt"));
        editor.getEvents().subscribe("save", new EmailNotificationListener("admin@example.com"));

        try {
            editor.openFile("test.txt");
            editor.saveFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
