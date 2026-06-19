package org.asif.commands;

import org.asif.document.Document;

/**
 * Concrete command that deletes the last {@code count} characters. To support
 * undo it must remember what it removed, so it can paste the text back exactly.
 */
public final class DeleteLastCommand implements Command {
    private final Document document;
    private final int count;
    private String removed = "";

    public DeleteLastCommand(final Document document, final int count) {
        this.document = document;
        this.count = count;
    }

    @Override
    public void execute() {
        removed = document.deleteLast(count);
    }

    @Override
    public void undo() {
        document.append(removed);
    }
}
