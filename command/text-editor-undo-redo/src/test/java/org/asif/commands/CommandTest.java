package org.asif.commands;

import org.asif.document.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {

    private static final int COMMA_WORLD_LENGTH = 7;

    @Test
    void insertCommandExecuteThenUndoLeavesDocumentUnchanged() {
        Document document = new Document();
        document.append("Hello");
        Command insert = new InsertTextCommand(document, ", World");

        insert.execute();
        assertEquals("Hello, World", document.getContent());

        insert.undo();
        assertEquals("Hello", document.getContent());
    }

    @Test
    void deleteCommandRestoresExactlyWhatItRemovedOnUndo() {
        Document document = new Document();
        document.append("Hello, World");
        Command delete = new DeleteLastCommand(document, COMMA_WORLD_LENGTH);

        delete.execute();
        assertEquals("Hello", document.getContent());

        delete.undo();
        assertEquals("Hello, World", document.getContent());
    }
}
