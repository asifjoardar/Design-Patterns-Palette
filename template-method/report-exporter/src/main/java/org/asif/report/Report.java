package org.asif.report;

import java.util.ArrayList;
import java.util.List;

/**
 * The data being exported: a set of column names plus the rows under them.
 * Rows are added in a fluent style for convenience.
 */
public final class Report {
    private final List<String> columns;
    private final List<List<String>> rows = new ArrayList<>();

    public Report(final String... columns) {
        this.columns = List.of(columns);
    }

    /**
     * Adds a row of values (one per column) and returns this report so calls
     * can be chained.
     *
     * @param values the cell values for the row
     * @return this report
     */
    public Report addRow(final String... values) {
        rows.add(List.of(values));
        return this;
    }

    /**
     * @return the column names
     */
    public List<String> columns() {
        return columns;
    }

    /**
     * @return the rows added so far
     */
    public List<List<String>> rows() {
        return rows;
    }
}
