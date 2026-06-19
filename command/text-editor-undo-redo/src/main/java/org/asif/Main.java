package org.asif;

import org.asif.commands.DeleteLastCommand;
import org.asif.commands.InsertTextCommand;
import org.asif.document.Document;
import org.asif.editor.Editor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Small demo that types a few words into an editor, deletes some, and then
 * walks the history backwards and forwards with undo/redo.
 */
public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final int CHARS_TO_DELETE = 6;

    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(final String[] args) {
        final Document document = new Document();
        final Editor editor = new Editor(document);

        editor.execute(new InsertTextCommand(document, "Hello"));
        editor.execute(new InsertTextCommand(document, ", World"));
        editor.execute(new DeleteLastCommand(document, CHARS_TO_DELETE));  // "Hello, World" -> "Hello,"

        LOGGER.info("--- now stepping back through history ---");
        editor.undo();   // undo the delete -> " World" comes back: "Hello, World"
        editor.undo();   // undo the ", World" insert: "Hello"
        editor.redo();   // redo that insert: "Hello, World"

        LOGGER.info("Final document: \"{}\"", editor.getText());
    }
}
