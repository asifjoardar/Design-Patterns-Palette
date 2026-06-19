package org.asif.adapter;

import com.google.gson.Gson;
import org.asif.analytics.AnalyticsService;
import org.asif.analytics.VisitRecord;
import org.asif.thirdparty.XmlTrafficProvider;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlToJsonAnalyticsAdapterTest {

    /** A test double standing in for the third-party system with controlled XML. */
    private static XmlTrafficProvider providerReturning(final String xml) {
        return new XmlTrafficProvider() {
            @Override
            public String fetchTrafficXml() {
                return xml;
            }
        };
    }

    @Test
    void presentsXmlTrafficAsJsonSortedByVisitsDescending() {
        AnalyticsService analytics = new XmlToJsonAnalyticsAdapter(new XmlTrafficProvider());

        VisitRecord[] records = new Gson().fromJson(analytics.fetchVisitsJson(), VisitRecord[].class);
        List<String> urls = Arrays.stream(records).map(VisitRecord::url).toList();

        // /home=1200, /blog=875, /pricing=340 -> most visited first
        assertEquals(List.of("/home", "/blog", "/pricing"), urls);
    }

    @Test
    void visitCountSurvivesTheXmlToJsonConversion() {
        AnalyticsService analytics = new XmlToJsonAnalyticsAdapter(
                providerReturning("<traffic><page url=\"/x\" visits=\"42\"/></traffic>"));

        assertEquals("[{\"url\":\"/x\",\"visits\":42}]", analytics.fetchVisitsJson());
    }

    @Test
    void emptyTrafficBecomesAnEmptyJsonArray() {
        AnalyticsService analytics = new XmlToJsonAnalyticsAdapter(
                providerReturning("<traffic></traffic>"));

        assertEquals("[]", analytics.fetchVisitsJson());
    }
}
