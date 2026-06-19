package org.asif.adapter;

import com.google.gson.Gson;
import org.asif.analytics.AnalyticsService;
import org.asif.analytics.VisitRecord;
import org.asif.thirdparty.XmlTrafficProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The <em>adapter</em>. It implements the target {@link AnalyticsService} our
 * app wants, while delegating to the incompatible {@link XmlTrafficProvider}
 * behind the scenes. Every call it translates XML into our JSON format, so the
 * dashboard never has to know the data originally arrived as XML.
 */
public final class XmlToJsonAnalyticsAdapter implements AnalyticsService {
    private final XmlTrafficProvider provider;
    private final Gson gson = new Gson();

    public XmlToJsonAnalyticsAdapter(final XmlTrafficProvider provider) {
        this.provider = provider;
    }

    @Override
    public String fetchVisitsJson() {
        final List<VisitRecord> records = parse(provider.fetchTrafficXml());
        records.sort(Comparator.comparingInt(VisitRecord::visits).reversed());
        return gson.toJson(records);
    }

    private List<VisitRecord> parse(final String xml) {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new InputSource(new StringReader(xml)));

            final NodeList pages = document.getElementsByTagName("page");
            final List<VisitRecord> records = new ArrayList<>();
            for (int i = 0; i < pages.getLength(); i++) {
                final Element page = (Element) pages.item(i);
                final String url = page.getAttribute("url");
                final int visits = Integer.parseInt(page.getAttribute("visits"));
                records.add(new VisitRecord(url, visits));
            }
            return records;
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse traffic XML", e);
        }
    }
}
