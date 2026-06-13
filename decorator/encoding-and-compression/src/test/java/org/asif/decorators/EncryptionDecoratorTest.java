package org.asif.decorators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EncryptionDecoratorTest {

    private static final String PLAINTEXT = "Name, Salary\nJohn Smith, 100000";

    @Test
    void writeThenReadRestoresOriginal() {
        DataSource encrypted = new EncryptionDecorator(new InMemoryDataSource());

        encrypted.writeData(PLAINTEXT);

        assertEquals(PLAINTEXT, encrypted.readData());
    }

    @Test
    void underlyingSourceStoresEncodedData() {
        InMemoryDataSource source = new InMemoryDataSource();
        DataSource encrypted = new EncryptionDecorator(source);

        encrypted.writeData(PLAINTEXT);

        assertNotEquals(PLAINTEXT, source.readData(),
                "Data persisted by the wrapped source should be encoded, not plaintext");
    }
}
