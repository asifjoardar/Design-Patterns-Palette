package org.asif.workflow;

/**
 * The <em>state</em> interface. Each workflow stage (draft, moderation,
 * published) is its own class that decides what {@code publish()} means right
 * now. The document delegates to its current state instead of running a big
 * {@code switch} over a status field.
 */
public interface DocumentState {

    /**
     * Handles a publish request for the document while it is in this state,
     * moving it to the next state when the request is allowed.
     *
     * @param document the document being acted on
     */
    void publish(Document document);

    /**
     * @return the human-readable name of this state
     */
    String name();
}
