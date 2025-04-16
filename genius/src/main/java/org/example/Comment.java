package org.example;

import java.time.LocalDateTime;

public class Comment {
    private String text;
    private User author;
    private String songTitle;
    private LocalDateTime timestamp;

    public Comment(String text, User author, Song song) {
        this.text = text;
        this.author = author;
        this.songTitle = song.getTitle();
        this.timestamp = LocalDateTime.now();
    }

    // Getterها
    public String getText() { return text; }
    public User getAuthor() { return author; }
    public String getSongTitle() { return songTitle; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Comment{" +
                "author=" + author.getUsername() +
                ", songTitle='" + songTitle + '\'' +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
