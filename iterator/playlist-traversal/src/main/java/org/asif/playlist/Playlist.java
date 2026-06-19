package org.asif.playlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The <em>aggregate</em>: a collection of songs that knows how to hand out
 * iterators over itself. Callers traverse it through those iterators and never
 * touch the underlying list — so the playlist is free to change how it stores
 * songs without breaking any client code.
 *
 * <p>It implements {@link Iterable}, so the forward iterator also powers Java's
 * for-each loop.</p>
 */
public final class Playlist implements Iterable<Song> {
    private final List<Song> songs = new ArrayList<>();

    /**
     * Adds a song to the end of the playlist.
     *
     * @param song the song to add
     */
    public void add(final Song song) {
        songs.add(song);
    }

    /**
     * @return the number of songs in the playlist
     */
    public int size() {
        return songs.size();
    }

    /**
     * @return an iterator that walks the playlist from first to last
     */
    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator(songs);
    }

    /**
     * @return an iterator that walks the playlist from last to first
     */
    public Iterator<Song> reverseIterator() {
        return new ReversePlaylistIterator(songs);
    }
}
