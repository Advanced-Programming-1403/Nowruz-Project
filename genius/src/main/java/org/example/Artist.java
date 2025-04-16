package org.example;

import java.util.ArrayList;
import java.util.List;

public class Artist extends Account {
    private boolean approved;
    private List<Song> songs;
    private List<Album> albums;

    public Artist() {
        super();
        this.role = Role.ARTIST;
        this.approved = false;
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
    }

    public Artist(String username, String password, String email) {
        super(username, password, email);
        this.role = Role.ARTIST;
        this.approved = false;
        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
    }

    public boolean isApproved() {
        return approved;
    }

    public void approve() {
        this.approved = true;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
    }

    public List<Album> getAlbums() {
        return albums;
    }
}
