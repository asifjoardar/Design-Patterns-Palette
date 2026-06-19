package org.asif.editor;

import org.asif.commands.Command;
import org.asif.document.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The <em>invoker</em>. It runs commands and keeps the history needed for undo
 * and redo, yet it never knows what any command actually does — it only calls
 * {@code execute()} and {@code undo()}.
 *
 * <p>Two stacks do all the work: every executed command is pushed onto the undo
 * stack; undoing pops it and pushes it onto the redo stack; redoing moves it
 * back. Running a brand-new command clears the redo stack, just like a real
 * editor abandons the redo history once you type something new.</p>
 */
public final class Editor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Editor.class);

    private final Document document;
    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    public Editor(final Document document) {
        this.document = document;
    }

    /**
     * Runs a command and records it so it can be undone later.
     *
     * @param command the edit to perform
     */
    public void execute(final Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
        LOGGER.info("Executed {} -> \"{}\"", command.getClass().getSimpleName(), document.getContent());
    }

    /** Reverses the most recent command, if there is one. */
    public void undo() {
        if (undoStack.isEmpty()) {
            LOGGER.info("Nothing left to undo");
            return;
        }
        final Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);
        LOGGER.info("Undid    {} -> \"{}\"", command.getClass().getSimpleName(), document.getContent());
    }

    /** Re-applies the most recently undone command, if there is one. */
    public void redo() {
        if (redoStack.isEmpty()) {
            LOGGER.info("Nothing left to redo");
            return;
        }
        final Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
        LOGGER.info("Redid    {} -> \"{}\"", command.getClass().getSimpleName(), document.getContent());
    }

    /**
     * @return the current text of the underlying document
     */
    public String getText() {
        return document.getContent();
    }
}
