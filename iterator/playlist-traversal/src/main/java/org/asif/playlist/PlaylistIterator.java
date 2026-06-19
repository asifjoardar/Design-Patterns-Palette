package org.asif.playlist;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Walks a playlist from the first song to the last. It implements the standard
 * {@link java.util.Iterator}, so it works with for-each loops, while keeping the
 * playlist's internal storage completely hidden from the caller.
 */
public final class PlaylistIterator implements java.util.Iterator<Song> {
    private final List<Song> songs;
    private int cursor;

    PlaylistIterator(final List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        return cursor < songs.size();
    }

    @Override
    public Song next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Reached the end of the playlist");
        }
        return songs.get(cursor++);
    }
}
