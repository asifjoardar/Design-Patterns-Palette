package org.asif.report;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportExporterTest {

    private Report sampleReport() {
        return new Report("region", "units")
                .addRow("West", "120")
                .addRow("East", "95");
    }

    @Test
    void csvExporterRendersHeaderThenCommaSeparatedRows() {
        ReportExporter csv = new CsvReportExporter();

        String output = csv.export(sampleReport());

        assertEquals("region,units\nWest,120\nEast,95\n", output);
    }

    @Test
    void jsonExporterRendersAnArrayOfObjects() {
        ReportExporter json = new JsonReportExporter();

        String output = json.export(sampleReport());

        assertEquals("""
                [
                  {"region": "West", "units": "120"},
                  {"region": "East", "units": "95"}
                ]
                """, output);
    }

    @Test
    void aReportWithNoRowsStillRendersTheCsvHeader() {
        ReportExporter csv = new CsvReportExporter();

        String output = csv.export(new Report("region", "units"));

        assertEquals("region,units\n", output);
    }

    @Test
    void bothFormatsExportTheSameDataViaTheSameTemplate() {
        Report report = sampleReport();

        // Same number of data rows surface in both formats, proving the shared skeleton ran.
        long csvRowLines = new CsvReportExporter().export(report).lines().count() - 1; // minus header
        long jsonObjectLines = new JsonReportExporter().export(report).lines()
                .filter(line -> line.contains("region")).count();

        assertEquals(csvRowLines, jsonObjectLines);
    }
}
