package org.asif.report;

import java.util.List;

/**
 * Exports a report as CSV. It only needs to define the two required steps — the
 * header line and each row line — and leaves every hook at its empty default.
 */
public final class CsvReportExporter extends ReportExporter {

    @Override
    protected String header(final List<String> columns) {
        return String.join(",", columns) + "\n";
    }

    @Override
    protected String row(final List<String> columns, final List<String> values) {
        return String.join(",", values) + "\n";
    }
}
