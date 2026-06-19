package org.asif.workflow;

/**
 * The <em>context</em>. A document moves through a publishing workflow, but
 * rather than tracking its stage with a status field and branching on it
 * everywhere, it simply delegates {@link #publish()} to its current
 * {@link DocumentState}. Each state knows what to do and which state comes next,
 * so the document's behaviour changes as its state changes.
 */
public final class Document {
    private final String title;
    private DocumentState state = new DraftState();
    private Role currentRole;

    public Document(final String title, final Role actingRole) {
        this.title = title;
        this.currentRole = actingRole;
    }

    /** Requests publication; what actually happens depends on the current state and role. */
    public void publish() {
        state.publish(this);
    }

    /**
     * Switches the user currently acting on the document, e.g. handing it from
     * an author to an admin reviewer.
     *
     * @param role the role now acting
     */
    public void actAs(final Role role) {
        this.currentRole = role;
    }

    /**
     * Used by the state objects to move the document to its next stage.
     *
     * @param next the new state
     */
    void changeState(final DocumentState next) {
        this.state = next;
    }

    /**
     * @return the name of the current workflow stage
     */
    public String currentState() {
        return state.name();
    }

    /**
     * @return the role currently acting on the document
     */
    public Role currentRole() {
        return currentRole;
    }

    /**
     * @return the document's title
     */
    public String title() {
        return title;
    }
}
