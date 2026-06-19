package org.asif.report;

import java.util.List;

/**
 * Exports the same report as a JSON array of objects. It reuses the exact same
 * algorithm skeleton but overrides the hooks it needs: brackets to open and
 * close the array, a comma between rows, and a row rendered as a JSON object.
 * JSON has no separate header line, so {@link #header} returns nothing.
 */
public final class JsonReportExporter extends ReportExporter {

    @Override
    protected String open() {
        return "[\n";
    }

    @Override
    protected String header(final List<String> columns) {
        return "";
    }

    @Override
    protected String row(final List<String> columns, final List<String> values) {
        final StringBuilder object = new StringBuilder("  {");
        for (int i = 0; i < columns.size(); i++) {
            if (i > 0) {
                object.append(", ");
            }
            object.append('"').append(columns.get(i)).append("\": \"").append(values.get(i)).append('"');
        }
        object.append('}');
        return object.toString();
    }

    @Override
    protected String rowSeparator() {
        return ",\n";
    }

    @Override
    protected String footer() {
        return "\n";
    }

    @Override
    protected String close() {
        return "]\n";
    }
}
