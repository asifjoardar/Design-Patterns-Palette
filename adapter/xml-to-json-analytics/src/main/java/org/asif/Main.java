package org.asif;

import org.asif.adapter.XmlToJsonAnalyticsAdapter;
import org.asif.analytics.AnalyticsService;
import org.asif.thirdparty.XmlTrafficProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo: our dashboard only knows the JSON-based {@link AnalyticsService}, yet
 * the data comes from a legacy XML system. The adapter bridges the gap so the
 * dashboard code below stays blissfully unaware of XML.
 */
public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(final String[] args) {
        // The legacy system we just integrated — it only emits XML.
        final XmlTrafficProvider legacy = new XmlTrafficProvider();

        // Wrap it so it looks like the AnalyticsService our app expects.
        final AnalyticsService analytics = new XmlToJsonAnalyticsAdapter(legacy);

        // The dashboard speaks only JSON and never sees the XML underneath.
        LOGGER.info("Most-visited pages as JSON: {}", analytics.fetchVisitsJson());
    }
}
