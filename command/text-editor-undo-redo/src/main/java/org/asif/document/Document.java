package org.asif.document;

/**
 * The <em>receiver</em> in the Command pattern: the object that actually knows
 * how to change the text. Commands call these methods to do their work, but the
 * document itself has no idea about undo, redo, or who is asking.
 */
public final class Document {
    private final StringBuilder content = new StringBuilder();

    /**
     * Appends text to the end of the document.
     *
     * @param text the text to add
     */
    public void append(final String text) {
        content.append(text);
    }

    /**
     * Removes the last {@code count} characters and returns what was removed,
     * so a command can remember it and put it back on undo.
     *
     * @param count how many trailing characters to delete
     * @return the deleted text (empty if there was nothing to remove)
     */
    public String deleteLast(final int count) {
        final int length = content.length();
        final int from = Math.max(0, length - count);
        final String removed = content.substring(from, length);
        content.delete(from, length);
        return removed;
    }

    /**
     * @return the current text held by the document
     */
    public String getContent() {
        return content.toString();
    }
}
