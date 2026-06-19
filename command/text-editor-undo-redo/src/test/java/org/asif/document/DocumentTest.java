package org.asif.document;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentTest {

    private static final int WORLD_LENGTH = 6;
    private static final int MORE_THAN_AVAILABLE = 100;

    @Test
    void appendAddsTextToTheEnd() {
        Document document = new Document();

        document.append("Hello");
        document.append(", World");

        assertEquals("Hello, World", document.getContent());
    }

    @Test
    void deleteLastReturnsAndRemovesTheTrailingText() {
        Document document = new Document();
        document.append("Hello, World");

        String removed = document.deleteLast(WORLD_LENGTH);

        assertEquals(" World", removed);
        assertEquals("Hello,", document.getContent());
    }

    @Test
    void deleteLastIsCappedAtTheDocumentLength() {
        Document document = new Document();
        document.append("Hi");

        String removed = document.deleteLast(MORE_THAN_AVAILABLE);

        assertEquals("Hi", removed);
        assertEquals("", document.getContent());
    }
}
