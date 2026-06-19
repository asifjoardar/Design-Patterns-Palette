package org.asif.report;

import java.util.List;

/**
 * The abstract class that owns the <em>template method</em>, {@link #export}.
 * It fixes the overall algorithm for turning a report into text — open, write a
 * header, write each row (separated as needed), write a footer, close — and lets
 * subclasses fill in the format-specific steps.
 *
 * <p>{@code export} is {@code final} on purpose: subclasses customise the
 * <em>steps</em>, never the <em>order</em>. {@link #header} and {@link #row} are
 * the required steps; the rest ({@link #open}, {@link #rowSeparator},
 * {@link #footer}, {@link #close}) are <em>hooks</em> with empty defaults that a
 * format overrides only if it needs them.</p>
 */
public abstract class ReportExporter {

    /**
     * The template method: the unchanging skeleton of the export algorithm.
     *
     * @param report the data to export
     * @return the fully formatted report
     */
    public final String export(final Report report) {
        final StringBuilder out = new StringBuilder();
        out.append(open());
        out.append(header(report.columns()));

        final List<List<String>> rows = report.rows();
        for (int i = 0; i < rows.size(); i++) {
            if (i > 0) {
                out.append(rowSeparator());
            }
            out.append(row(report.columns(), rows.get(i)));
        }

        out.append(footer());
        out.append(close());
        return out.toString();
    }

    /**
     * @param columns the column names
     * @return the rendered header for this format
     */
    protected abstract String header(List<String> columns);

    /**
     * @param columns the column names (useful for key/value formats)
     * @param values  the values for one row
     * @return the rendered row for this format
     */
    protected abstract String row(List<String> columns, List<String> values);

    /** @return text emitted before the header (default: none) */
    protected String open() {
        return "";
    }

    /** @return text placed between consecutive rows (default: none) */
    protected String rowSeparator() {
        return "";
    }

    /** @return text emitted after the last row (default: none) */
    protected String footer() {
        return "";
    }

    /** @return text emitted at the very end (default: none) */
    protected String close() {
        return "";
    }
}
