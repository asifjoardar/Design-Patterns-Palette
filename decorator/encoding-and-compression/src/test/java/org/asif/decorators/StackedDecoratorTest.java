package org.asif.decorators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Decorators must be composable: stacking compression over encryption should
 * still round-trip cleanly, demonstrating the core strength of the pattern.
 */
class StackedDecoratorTest {

    private static final String PLAINTEXT = "Name, Salary\nJohn Smith, 100000";

    @Test
    void compressionOverEncryptionRoundTrips() {
        DataSource stacked = new CompressionDecorator(
                new EncryptionDecorator(new InMemoryDataSource()));

        stacked.writeData(PLAINTEXT);

        assertEquals(PLAINTEXT, stacked.readData());
    }
}
