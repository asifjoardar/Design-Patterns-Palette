package org.asif.decorators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompressionDecoratorTest {

    private static final String PLAINTEXT = "Name, Salary\nJohn Smith, 100000\nSteven Jobs, 912000";
    private static final int DEFAULT_LEVEL = 6;
    private static final int CUSTOM_LEVEL = 9;

    @Test
    void writeThenReadRestoresOriginal() {
        DataSource compressed = new CompressionDecorator(new InMemoryDataSource());

        compressed.writeData(PLAINTEXT);

        assertEquals(PLAINTEXT, compressed.readData());
    }

    @Test
    void defaultCompressionLevelIsExposed() {
        CompressionDecorator compressed = new CompressionDecorator(new InMemoryDataSource());

        assertEquals(DEFAULT_LEVEL, compressed.getCompressionLevel());
    }

    @Test
    void roundTripWorksWithCustomCompressionLevel() {
        CompressionDecorator compressed = new CompressionDecorator(new InMemoryDataSource());

        compressed.setCompressionLevel(CUSTOM_LEVEL);

        assertEquals(CUSTOM_LEVEL, compressed.getCompressionLevel());
        compressed.writeData(PLAINTEXT);
        assertEquals(PLAINTEXT, compressed.readData());
    }
}
