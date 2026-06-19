package org.asif.playlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlaylistTest {

    private static final Song FIRST = new Song("Bohemian Rhapsody", "Queen");
    private static final Song SECOND = new Song("Hotel California", "Eagles");
    private static final Song THIRD = new Song("Stairway to Heaven", "Led Zeppelin");

    private Playlist playlist;

    @BeforeEach
    void setUp() {
        playlist = new Playlist();
        playlist.add(FIRST);
        playlist.add(SECOND);
        playlist.add(THIRD);
    }

    private static List<Song> drain(final Iterator<Song> iterator) {
        List<Song> collected = new ArrayList<>();
        while (iterator.hasNext()) {
            collected.add(iterator.next());
        }
        return collected;
    }

    @Test
    void forwardIteratorWalksFromFirstToLast() {
        assertEquals(List.of(FIRST, SECOND, THIRD), drain(playlist.iterator()));
    }

    @Test
    void reverseIteratorWalksFromLastToFirst() {
        assertEquals(List.of(THIRD, SECOND, FIRST), drain(playlist.reverseIterator()));
    }

    @Test
    void forEachLoopUsesTheForwardIterator() {
        List<Song> collected = new ArrayList<>();
        for (Song song : playlist) {
            collected.add(song);
        }
        assertEquals(List.of(FIRST, SECOND, THIRD), collected);
    }

    @Test
    void twoIteratorsTraverseIndependently() {
        Iterator<Song> a = playlist.iterator();
        Iterator<Song> b = playlist.iterator();

        a.next();          // advance only the first iterator
        assertEquals(FIRST, b.next(), "second iterator must start fresh");
    }

    @Test
    void callingNextPastTheEndThrows() {
        Iterator<Song> iterator = drainIteratorToEnd();

        assertFalse(iterator.hasNext());
        assertThrows(java.util.NoSuchElementException.class, iterator::next);
    }

    @Test
    void emptyPlaylistHasNothingToIterate() {
        Playlist empty = new Playlist();

        assertTrue(drain(empty.iterator()).isEmpty());
    }

    private Iterator<Song> drainIteratorToEnd() {
        Iterator<Song> iterator = playlist.iterator();
        drain(iterator);
        return iterator;
    }
}
