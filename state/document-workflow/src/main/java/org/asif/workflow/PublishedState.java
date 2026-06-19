package org.asif.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The final state. A published document is already live, so publishing again
 * does nothing — the state simply absorbs the request.
 */
public final class PublishedState implements DocumentState {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishedState.class);

    @Override
    public void publish(final Document document) {
        LOGGER.info("'{}' is already published — nothing to do", document.title());
    }

    @Override
    public String name() {
        return "Published";
    }
}
