package org.asif.workflow;

/**
 * The role of the user acting on a document. Who you are decides whether a
 * transition is allowed — only an {@link #ADMIN} can approve a document for
 * publication.
 */
public enum Role {
    AUTHOR,
    ADMIN
}
