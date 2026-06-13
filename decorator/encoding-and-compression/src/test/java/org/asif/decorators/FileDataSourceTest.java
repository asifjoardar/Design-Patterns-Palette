package org.asif.decorators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileDataSourceTest {

    private static final String PLAINTEXT = "Name, Salary\nJohn Smith, 100000";

    @Test
    void writeThenReadRoundTripsThroughAFile(@TempDir Path tempDir) {
        File file = tempDir.resolve("output.txt").toFile();
        DataSource source = new FileDataSource(file.getAbsolutePath());

        source.writeData(PLAINTEXT);

        assertEquals(PLAINTEXT, source.readData());
    }
}
