package org.example;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String title;
    private Artist artist;
    private List<Song> songs;

    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    // Getterها
    public String getTitle() { return title; }
    public Artist getArtist() { return artist; }
    public List<Song> getSongs() { return songs; }

    // Setterها
    public void setTitle(String title) { this.title = title; }

    // اضافه کردن آهنگ
    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                ", artist=" + artist.getUsername() +
                ", songs=" + songs.size() +
                '}';
    }
}
