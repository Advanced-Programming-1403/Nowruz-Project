package org.example;

import java.util.ArrayList;
import java.util.List;

public class User extends Account {
    private List<Song> favoriteSongs;
    private List<Comment> comments;
    private List<Song> favorites;
    private List<Playlist> playlists;

    public User() {
        super();
        this.role = Role.USER;
        this.favorites = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public User(String username, String password, String email) {
        super(username, password, email);
        this.role = Role.USER;
        this.favorites = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public void addFavorite(Song song) {
        favorites.add(song);
    }

    public List<Song> getFavorites() {
        return favorites;
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public List<Playlist> getPlaylists() {
        return playlists;
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
