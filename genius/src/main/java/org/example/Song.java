package org.example;

import java.util.ArrayList;
import java.util.List;

public class Song {
    private String title;
    private String lyrics;
    private Artist artist;
    private Album album;
    private int likes;
    private List<Comment> comments;

    public Song(String title, String lyrics, Artist artist, Album album) {
        this.title = title;
        this.lyrics = lyrics;
        this.artist = artist;
        this.album = album;
        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    // Getterها
    public String getTitle() { return title; }
    public String getLyrics() { return lyrics; }
    public Artist getArtist() { return artist; }
    public Album getAlbum() { return album; }
    public int getLikes() { return likes; }
    public List<Comment> getComments() { return comments; }

    // Setterها
    public void setTitle(String title) { this.title = title; }
    public void setLyrics(String lyrics) { this.lyrics = lyrics; }
    public void setAlbum(Album album) { this.album = album; }

    // عملکردها
    public void like() {
        likes++;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", artist=" + artist.getUsername() +
                ", likes=" + likes +
                ", comments=" + comments.size() +
                '}';
    }
}
