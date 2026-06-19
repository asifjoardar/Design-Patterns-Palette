package org.asif.playlist;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Walks the same playlist from the last song to the first. Having a second,
 * independent iterator over the same collection — without exposing how the
 * songs are stored — is exactly the strength of the Iterator pattern.
 */
public final class ReversePlaylistIterator implements java.util.Iterator<Song> {
    private final List<Song> songs;
    private int cursor;

    ReversePlaylistIterator(final List<Song> songs) {
        this.songs = songs;
        this.cursor = songs.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return cursor >= 0;
    }

    @Override
    public Song next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Reached the start of the playlist");
        }
        return songs.get(cursor--);
    }
}
