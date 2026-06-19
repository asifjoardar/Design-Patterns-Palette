package org.asif.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The starting state. Publishing a draft doesn't make it live — it submits the
 * document for review, moving it into moderation.
 */
public final class DraftState implements DocumentState {
    private static final Logger LOGGER = LoggerFactory.getLogger(DraftState.class);

    @Override
    public void publish(final Document document) {
        LOGGER.info("'{}' submitted for review", document.title());
        document.changeState(new ModerationState());
    }

    @Override
    public String name() {
        return "Draft";
    }
}
