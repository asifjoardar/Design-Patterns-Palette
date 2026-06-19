package org.asif.commands;

import org.asif.document.Document;

/**
 * Concrete command that inserts a piece of text at the end of the document.
 * Undo simply removes the same number of characters it added.
 */
public final class InsertTextCommand implements Command {
    private final Document document;
    private final String text;

    public InsertTextCommand(final Document document, final String text) {
        this.document = document;
        this.text = text;
    }

    @Override
    public void execute() {
        document.append(text);
    }

    @Override
    public void undo() {
        document.deleteLast(text.length());
    }
}
