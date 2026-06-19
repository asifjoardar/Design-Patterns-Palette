package org.asif.commands;

/**
 * Turns an edit into a stand-alone object. Every command knows how to both
 * {@link #execute()} itself and {@link #undo()} the change it made, which is
 * what lets the editor offer unlimited undo and redo.
 */
public interface Command {

    /** Performs the edit on the receiver. */
    void execute();

    /** Reverses exactly what {@link #execute()} did. */
    void undo();
}
