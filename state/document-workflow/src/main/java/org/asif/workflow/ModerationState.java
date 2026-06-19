package org.asif.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The under-review state. Here the same {@code publish()} action behaves
 * differently depending on <em>who</em> is asking: an admin approves and the
 * document goes live, while an author cannot approve their own work, so it
 * stays in moderation.
 */
public final class ModerationState implements DocumentState {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModerationState.class);

    @Override
    public void publish(final Document document) {
        if (document.currentRole() == Role.ADMIN) {
            LOGGER.info("Admin approved '{}' — now published", document.title());
            document.changeState(new PublishedState());
        } else {
            LOGGER.info("Only an admin can approve; '{}' stays in moderation", document.title());
        }
    }

    @Override
    public String name() {
        return "Moderation";
    }
}
