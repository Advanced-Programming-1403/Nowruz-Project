package org.example;

import java.time.LocalDateTime;

public class Comment {
    private String text;
    private User author;
    private Song song;
    private LocalDateTime timestamp;

    public Comment(String text, User author, Song song) {
        this.text = text;
        this.author = author;
        this.song = song;
        this.timestamp = LocalDateTime.now();
    }

    // Getterها
    public String getText() { return text; }
    public User getAuthor() { return author; }
    public Song getSong() { return song; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Comment{" +
                "author=" + author.getUsername() +
                ", song=" + song.getTitle() +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
