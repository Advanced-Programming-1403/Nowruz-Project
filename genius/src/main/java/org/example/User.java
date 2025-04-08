package org.example;

import java.util.ArrayList;
import java.util.List;

public class User extends Account {
    private List<Song> favoriteSongs;
    private List<Comment> comments;

    public User(String username, String password, String email) {
        super(username, password, email);
        this.favoriteSongs = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    // Getterها
    public List<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    public List<Comment> getComments() {
        return comments;
    }

    // متدهای اضافه کردن
    public void addFavoriteSong(Song song) {
        favoriteSongs.add(song);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", favoriteSongs=" + favoriteSongs.size() +
                ", comments=" + comments.size() +
                '}';
    }
}
