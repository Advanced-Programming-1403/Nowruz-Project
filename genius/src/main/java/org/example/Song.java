package org.example;

import java.util.ArrayList;
import java.util.List;

public class Song {
    private String title;
    private String lyrics;
    private String artistUsername; // instead of full Artist object
    private Album album;
    private int likes;
    private List<Comment> comments;

    public Song(String title, String lyrics, String artistUsername, Album album) {
        this.title = title;
        this.lyrics = lyrics;
        this.artistUsername = artistUsername;
        this.album = album;
        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    public String getTitle() { return title; }
    public String getLyrics() { return lyrics; }
    public String getArtistUsername() { return artistUsername; }
    public Album getAlbum() { return album; }
    public int getLikes() { return likes; }
    public List<Comment> getComments() { return comments; }

    public void setTitle(String title) { this.title = title; }
    public void setLyrics(String lyrics) { this.lyrics = lyrics; }
    public void setAlbum(Album album) { this.album = album; }

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
                ", artistUsername='" + artistUsername + '\'' +
                ", likes=" + likes +
                ", comments=" + comments.size() +
                '}';
    }
}
