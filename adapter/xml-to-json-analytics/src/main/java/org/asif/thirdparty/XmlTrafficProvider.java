package org.asif.thirdparty;

/**
 * The <em>adaptee</em>: a legacy, third-party traffic system we cannot change.
 * It only speaks XML, which is incompatible with our JSON-based
 * {@link org.asif.analytics.AnalyticsService}. We have no access to its source,
 * so we cannot make it implement our interface — exactly the situation Adapter
 * is built for.
 */
public class XmlTrafficProvider {

    /**
     * @return raw page-traffic data as an XML document
     */
    public String fetchTrafficXml() {
        return """
                <traffic>
                    <page url="/home" visits="1200"/>
                    <page url="/pricing" visits="340"/>
                    <page url="/blog" visits="875"/>
                </traffic>
                """;
    }
}
