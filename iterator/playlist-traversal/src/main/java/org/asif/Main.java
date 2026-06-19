package org.asif;

import org.asif.playlist.Playlist;
import org.asif.playlist.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * Demo: build a playlist and traverse it two different ways — forwards via a
 * for-each loop, then backwards via a second iterator — without ever touching
 * how the playlist stores its songs.
 */
public final class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(final String[] args) {
        final Playlist playlist = new Playlist();
        playlist.add(new Song("Bohemian Rhapsody", "Queen"));
        playlist.add(new Song("Hotel California", "Eagles"));
        playlist.add(new Song("Stairway to Heaven", "Led Zeppelin"));

        LOGGER.info("Playing forwards:");
        for (final Song song : playlist) {          // uses PlaylistIterator under the hood
            LOGGER.info("  ▶ {}", song);
        }

        LOGGER.info("Playing backwards:");
        final Iterator<Song> reverse = playlist.reverseIterator();
        while (reverse.hasNext()) {
            LOGGER.info("  ◀ {}", reverse.next());
        }
    }
}
