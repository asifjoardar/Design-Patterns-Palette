package org.asif.analytics;

/**
 * The <em>target</em> interface — the shape our analytics dashboard expects.
 * The whole application is built around JSON, so it asks every analytics source
 * for its data as a JSON array, most-visited page first.
 */
public interface AnalyticsService {

    /**
     * @return page-traffic metrics serialised as a JSON array, e.g.
     *         {@code [{"url":"/home","visits":1200}, ...]}
     */
    String fetchVisitsJson();
}
