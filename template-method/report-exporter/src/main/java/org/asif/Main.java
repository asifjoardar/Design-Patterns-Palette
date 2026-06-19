package org.asif;

import org.asif.report.CsvReportExporter;
import org.asif.report.JsonReportExporter;
import org.asif.report.Report;
import org.asif.report.ReportExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo: the same report is exported two ways. Both exporters follow the identical
 * algorithm defined in {@link ReportExporter#export}; only the per-step
 * formatting differs.
 */
public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(final String[] args) {
        final Report report = new Report("region", "units")
                .addRow("West", "120")
                .addRow("East", "95")
                .addRow("North", "60");

        final ReportExporter csv = new CsvReportExporter();
        final ReportExporter json = new JsonReportExporter();

        LOGGER.info("CSV export:\n{}", csv.export(report));
        LOGGER.info("JSON export:\n{}", json.export(report));
    }
}
