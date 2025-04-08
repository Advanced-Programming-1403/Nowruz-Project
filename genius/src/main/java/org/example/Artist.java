package org.example;

import java.util.ArrayList;
import java.util.List;

public class Artist extends Account {
    private List<Song> songs;
    private List<Album> albums;

    public Artist(String username, String password, String email) {
        super(username, password, email);
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
    }

    // Getterها
    public List<Song> getSongs() {
        return songs;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    // متدهای اضافه کردن
    public void addSong(Song song) {
        songs.add(song);
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", songs=" + songs.size() +
                ", albums=" + albums.size() +
                '}';
    }
}
