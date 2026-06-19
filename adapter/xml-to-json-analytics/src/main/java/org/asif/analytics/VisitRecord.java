package org.asif.analytics;

/**
 * A single page-traffic metric in the shape our application understands:
 * a page URL and how many visits it received.
 *
 * @param url    the page that was visited
 * @param visits the number of visits recorded for that page
 */
public record VisitRecord(String url, int visits) {
}
