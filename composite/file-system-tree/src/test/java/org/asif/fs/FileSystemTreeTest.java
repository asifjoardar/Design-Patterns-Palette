package org.asif.fs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileSystemTreeTest {

    private static final long RESUME_SIZE = 240L;
    private static final long PHOTO_SIZE = 1_800L;
    private static final long NOTE_SIZE = 60L;
    private static final long EXPECTED_TOTAL = NOTE_SIZE + RESUME_SIZE + PHOTO_SIZE;

    private Directory sampleTree() {
        return new Directory("home")
                .add(new FileLeaf("notes.txt", NOTE_SIZE))
                .add(new Directory("documents")
                        .add(new FileLeaf("resume.pdf", RESUME_SIZE))
                        .add(new Directory("photos")
                                .add(new FileLeaf("trip.jpg", PHOTO_SIZE))));
    }

    @Test
    void aFileReportsItsOwnSize() {
        FileSystemNode file = new FileLeaf("resume.pdf", RESUME_SIZE);

        assertEquals(RESUME_SIZE, file.sizeInBytes());
    }

    @Test
    void aDirectorySumsEverythingItContainsRecursively() {
        FileSystemNode root = sampleTree();

        assertEquals(EXPECTED_TOTAL, root.sizeInBytes());
    }

    @Test
    void anEmptyDirectoryHasZeroSize() {
        assertEquals(0L, new Directory("empty").sizeInBytes());
    }

    @Test
    void filesAndDirectoriesAreTreatedUniformlyInTheTree() {
        String rendered = sampleTree().tree("");

        assertTrue(rendered.contains("home/"), "root directory line");
        assertTrue(rendered.contains("  notes.txt (60 B)"), "nested file line");
        assertTrue(rendered.contains("    photos/"), "deeply nested directory line");
        assertTrue(rendered.contains("      trip.jpg (1800 B)"), "leaf under nested directory");
    }
}
