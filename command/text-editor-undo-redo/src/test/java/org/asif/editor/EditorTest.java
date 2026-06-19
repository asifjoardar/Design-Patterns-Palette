package org.asif.editor;

import org.asif.commands.DeleteLastCommand;
import org.asif.commands.InsertTextCommand;
import org.asif.document.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditorTest {

    private static final int SPACE_WORLD_LENGTH = 6;

    private Editor newEditor(final Document document) {
        return new Editor(document);
    }

    @Test
    void undoReversesCommandsInLastInFirstOutOrder() {
        Document document = new Document();
        Editor editor = newEditor(document);
        editor.execute(new InsertTextCommand(document, "Hello"));
        editor.execute(new InsertTextCommand(document, ", World"));

        editor.undo();

        assertEquals("Hello", editor.getText());
    }

    @Test
    void redoReappliesTheMostRecentlyUndoneCommand() {
        Document document = new Document();
        Editor editor = newEditor(document);
        editor.execute(new InsertTextCommand(document, "Hello"));
        editor.undo();

        editor.redo();

        assertEquals("Hello", editor.getText());
    }

    @Test
    void executingANewCommandClearsTheRedoHistory() {
        Document document = new Document();
        Editor editor = newEditor(document);
        editor.execute(new InsertTextCommand(document, "Hello"));
        editor.undo();

        editor.execute(new InsertTextCommand(document, "Goodbye"));
        editor.redo();

        assertEquals("Goodbye", editor.getText());
    }

    @Test
    void fullScenarioMixesInsertDeleteUndoAndRedo() {
        Document document = new Document();
        Editor editor = newEditor(document);
        editor.execute(new InsertTextCommand(document, "Hello"));
        editor.execute(new InsertTextCommand(document, ", World"));
        editor.execute(new DeleteLastCommand(document, SPACE_WORLD_LENGTH));

        editor.undo();
        editor.undo();
        editor.redo();

        assertEquals("Hello, World", editor.getText());
    }

    @Test
    void undoAndRedoOnEmptyHistoryAreNoOps() {
        Document document = new Document();
        Editor editor = newEditor(document);

        editor.undo();
        editor.redo();

        assertEquals("", editor.getText());
    }
}
