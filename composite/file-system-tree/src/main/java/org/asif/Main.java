package org.asif;

import org.asif.fs.Directory;
import org.asif.fs.FileLeaf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo: build a small file tree out of files and nested directories, then ask
 * the root for its total size and a printable tree — both work the same whether
 * a node is a single file or a directory full of them.
 */
public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private static final long RESUME_SIZE = 240L;
    private static final long PHOTO_SIZE = 1_800L;
    private static final long SONG_SIZE = 4_200L;
    private static final long NOTE_SIZE = 60L;

    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(final String[] args) {
        final Directory root = new Directory("home")
                .add(new FileLeaf("notes.txt", NOTE_SIZE))
                .add(new Directory("documents")
                        .add(new FileLeaf("resume.pdf", RESUME_SIZE))
                        .add(new Directory("photos")
                                .add(new FileLeaf("trip.jpg", PHOTO_SIZE))))
                .add(new Directory("music")
                        .add(new FileLeaf("song.mp3", SONG_SIZE)));

        LOGGER.info("Total size of '{}' = {} bytes", root.name(), root.sizeInBytes());
        LOGGER.info("Tree:\n{}", root.tree(""));
    }
}
