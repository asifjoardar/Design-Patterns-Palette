package org.asif.playlist;

/**
 * A single track in a playlist.
 *
 * @param title  the song title
 * @param artist the performing artist
 */
public record Song(String title, String artist) {

    @Override
    public String toString() {
        return title + " — " + artist;
    }
}
