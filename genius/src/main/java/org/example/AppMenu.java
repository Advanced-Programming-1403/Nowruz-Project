package org.example;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppMenu {
    private List<Account> accounts;
    private final String accountFile = "accounts.json";


    public AppMenu() {
        accounts = DataManager.loadData(accountFile, new TypeToken<List<Account>>() {}.getType());
        if (accounts == null) accounts = new ArrayList<>();
        ensureDefaultAdmin();

        songs = DataManager.loadData(songFile, new TypeToken<List<Song>>() {}.getType());
        if (songs == null) songs = new ArrayList<>();

        albums = DataManager.loadData(albumFile, new TypeToken<List<Album>>() {}.getType());
        if (albums == null) albums = new ArrayList<>();

        try {
            comments = DataManager.loadData(commentFile, new TypeToken<List<Comment>>() {}.getType());
            if (comments == null) comments = new ArrayList<>();
            System.out.println("✅ Comments loaded.");
        } catch (Exception e) {
            System.err.println("❌ Failed to load comments: " + e.getMessage());
            e.printStackTrace();
            comments = new ArrayList<>();
        }


    }

    private void ensureDefaultAdmin() {
        boolean hasAdmin = accounts.stream().anyMatch(acc -> acc instanceof Admin);
        if (!hasAdmin) {
            Admin defaultAdmin = new Admin("admin", "admin123", "admin@genius.com");
            accounts.add(defaultAdmin);
            System.out.println("⚙️ Default admin created: username=admin, password=admin123");
        }
    }

    private void adminMenu(Scanner scanner, Admin admin) {
        while (true) {
            System.out.println("\n🔐 Admin Panel:");
            System.out.println("1. Approve Artist");
            System.out.println("2. View Accounts");
            System.out.println("0. Log out");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> approveArtist(scanner);
                case "2" -> viewAccounts();
                case "0" -> {
                    System.out.println("👋 Logged out.");
                    return;
                }
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }

    private void viewAccounts() {
    }


    private final String songFile = "songs.json";
    private List<Song> songs;

    private final String albumFile = "albums.json";
    private List<Album> albums;

    private void listPendingArtists() {
        System.out.println("=== Pending Artists ===");
        boolean found = false;
        for (Account acc : accounts) {
            if (acc instanceof Artist artist && !artist.isApproved()) {
                System.out.println("- " + artist.getUsername() + " | " + artist.getEmail());
                found = true;
            }
        }
        if (!found) System.out.println("No pending artists.");
    }

    private void approveArtist(Scanner scanner) {
        System.out.print("Enter artist username to approve: ");
        String username = scanner.nextLine();

        for (Account acc : accounts) {
            if (acc instanceof Artist artist && artist.getUsername().equals(username)) {
                if (artist.isApproved()) {
                    System.out.println("✅ Already approved.");
                } else {
                    artist.approve();
                    DataManager.saveData(accounts, accountFile);
                    System.out.println("🎉 Artist approved successfully!");
                }
                return;
            }
        }
        System.out.println("❌ Artist not found.");
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Genius CLI Menu ===");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. List Accounts");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    signUp(scanner);
                    break;
                case "2":
                    logIn(scanner);
                    break;
                case "3":
                    listAccounts();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }

        // Save on exit
        DataManager.saveData(accounts, accountFile);
    }

    private void signUp(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("Select role (1. User, 2. Artist): ");
        String roleInput = scanner.nextLine();

        switch (roleInput) {
            case "1" -> {
                User newUser = new User(username, password, email);
                accounts.add(newUser);
                System.out.println("✅ User registered successfully.");
            }
            case "2" -> {
                Artist newArtist = new Artist(username, password, email);
                accounts.add(newArtist);
                System.out.println("🎤 Artist registered. Awaiting admin approval.");
            }
            default -> System.out.println("❌ Invalid role.");
        }
    }

    private void logIn(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {

                // مسیر برای آرتیست
                switch (account) {
                    case Artist artist -> {
                        if (!artist.isApproved()) {
                            System.out.println("⛔ Your artist account is not yet approved by admin.");
                            return;
                        }
                        System.out.println("🎤 Welcome, artist " + artist.getUsername());
                        artistMenu(artist); // وارد منوی آرتیست شو

                        return; // وارد منوی آرتیست شو
                    }


                    // مسیر برای ادمین
                    case Admin admin -> {
                        System.out.println("🛡️ Welcome, Admin " + admin.getUsername());
                        adminMenu(scanner, admin);

                        return;
                    }


                    // مسیر برای یوزر عادی
                    case User user -> {
                        System.out.println("👋 Welcome, user " + user.getUsername());
                        userMenu(user); // اینو بعداً پیاده‌سازی می‌کنیم

                        return; // اینو بعداً پیاده‌سازی می‌کنیم
                    }
                    default -> {
                    }
                }

            }
        }

        System.out.println("❌ Invalid credentials.");
    }

    private void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("⚠️ No accounts found.");
            return;
        }

        System.out.println("=== Registered Accounts ===");
        for (Account acc : accounts) {
            System.out.println("- " + acc.getUsername() + " | " + acc.getEmail() + " | " + acc.getRole());
        }
    }

    private void artistMenu(Artist artist) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Artist Panel ===");
            System.out.println("1. Create new song");
            System.out.println("2. View my songs");
            System.out.println("3. Create new album");
            System.out.println("4. View my albums");
            System.out.println("0. Log out");
            System.out.print("Choose: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> createSong(scanner, artist);
                case "2" -> viewArtistSongs(artist);
                case "3" -> createAlbum(scanner, artist);
                case "4" -> viewArtistAlbums(artist);
                case "0" -> {
                    System.out.println("🚪 Logged out.");
                    running = false;
                }
                default -> System.out.println("Invalid choice.");
            }
        }

        DataManager.saveData(albums, albumFile);
        DataManager.saveData(songs, songFile);
    }

    private void createSong(Scanner scanner, Artist artist) {
        System.out.print("Song title: ");
        String title = scanner.nextLine();

        System.out.println("Enter lyrics (end with a line containing only '###'):");
        StringBuilder lyricsBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("###")) {
            lyricsBuilder.append(line).append("\n");
        }
        String lyrics = lyricsBuilder.toString().trim();

        Song newSong = new Song(title, lyrics, artist.getUsername(), null);
        songs.add(newSong);
        artist.addSong(newSong);

        System.out.print("Add this song to an album? (y/n): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            List<Album> artistAlbums = albums.stream()
                    .filter(a -> a.getArtist().getUsername().equals(artist.getUsername()))
                    .toList();

            if (artistAlbums.isEmpty()) {
                System.out.println("⚠️ No albums found.");
            } else {
                for (int i = 0; i < artistAlbums.size(); i++) {
                    System.out.println((i + 1) + ". " + artistAlbums.get(i).getTitle());
                }

                System.out.print("Select album number: ");
                int albumIndex = Integer.parseInt(scanner.nextLine()) - 1;

                if (albumIndex >= 0 && albumIndex < artistAlbums.size()) {
                    Album selectedAlbum = artistAlbums.get(albumIndex);
                    selectedAlbum.addSong(newSong);
                    newSong.setAlbum(selectedAlbum);
                    System.out.println("🎶 Song added to album: " + selectedAlbum.getTitle());
                } else {
                    System.out.println("❌ Invalid album selection.");
                }
            }
        }

        System.out.println("✅ Song created successfully!");
    }



    private void viewArtistSongs(Artist artist) {
        System.out.println("🎵 Songs by " + artist.getUsername() + ":");
        boolean found = false;
        for (Song song : songs) {
            if (song.getArtistUsername().equals(artist.getUsername())) {
                System.out.println("- " + song.getTitle());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No songs found.");
        }
    }

    private void createAlbum(Scanner scanner, Artist artist) {
        System.out.print("Album title: ");
        String title = scanner.nextLine();

        Album album = new Album(title, artist);
        albums.add(album);
        artist.addAlbum(album);

        System.out.println("✅ Album created successfully!");
    }

    private void viewArtistAlbums(Artist artist) {
        System.out.println("📀 Albums by " + artist.getUsername() + ":");
        boolean found = false;
        for (Album album : albums) {
            if (album.getArtist().getUsername().equals(artist.getUsername())) {
                System.out.println("- " + album.getTitle() + " (" + album.getSongs().size() + " songs)");
                found = true;
            }
        }
        if (!found) System.out.println("No albums found.");
    }

    private void userMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. View all songs");
            System.out.println("2. Like a song");
            System.out.println("3. Comment on a song");
            System.out.println("4. View comments of a song");
            System.out.println("5. Search songs");
            System.out.println("6. View songs by artist");
            System.out.println("7. View top liked songs");
            System.out.println("8. Add song to favorites");
            System.out.println("9. View favorites");
            System.out.println("10. Create new playlist");
            System.out.println("11. Add song to playlist");
            System.out.println("12. View my playlists");
            System.out.println("13. Search song in my playlists");
            System.out.println("14. Remove song from favorites");
            System.out.println("0. Log out");
            System.out.print("Choose: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> listAllSongs();
                case "2" -> likeSong(scanner);
                case "3" -> commentOnSong(scanner, user);
                case "4" -> viewComments(scanner);
                case "5" -> searchSongs(scanner);
                case "6" -> viewSongsByArtist(scanner);
                case "7" -> showTopLikedSongs();
                case "8" -> addSongToFavorites(scanner, user);
                case "9" -> viewFavorites(user);
                case "10" -> createPlaylist(scanner, user);
                case "11" -> addSongToPlaylist(scanner, user);
                case "12" -> viewPlaylists(user);
                case "13" -> searchSongInPlaylists(scanner, user);
                case "14" -> removeFavorite(scanner, user);
                case "0" -> {
                    System.out.println("🚪 Logged out.");
                    running = false;
                }
                default -> System.out.println("Invalid choice.");
            }
        }

        // Save changes
        DataManager.saveData(songs, songFile);
        DataManager.saveData(comments, commentFile);
    }

    private void listAllSongs() {
        System.out.println("🎵 All Songs:");
        if (songs.isEmpty()) {
            System.out.println("No songs available.");
            return;
        }
        for (int i = 0; i < songs.size(); i++) {
            Song s = songs.get(i);
            System.out.printf("%d. %s by %s (%d likes, %d comments)%n",
                    i + 1, s.getTitle(), s.getArtistUsername(), s.getLikes(), s.getComments().size());
        }
    }

    private void likeSong(Scanner scanner) {
        listAllSongs();
        System.out.print("Enter song number to like: ");
        int choice = Integer.parseInt(scanner.nextLine()) - 1;

        if (choice >= 0 && choice < songs.size()) {
            songs.get(choice).like();
            System.out.println("❤️ Song liked!");
        } else {
            System.out.println("❌ Invalid selection.");
        }
    }

    private final String commentFile = "comments.json";
    private List<Comment> comments;

    private void commentOnSong(Scanner scanner, User user) {
        listAllSongs();
        System.out.print("Enter song number to comment: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < songs.size()) {
            Song song = songs.get(index);

            System.out.print("Enter your comment: ");
            String text = scanner.nextLine();

            Comment comment = new Comment(text, user, song);
            song.addComment(comment);
            comments.add(comment);

            System.out.println("💬 Comment added!");
        } else {
            System.out.println("❌ Invalid song number.");
        }
    }

    private void viewComments(Scanner scanner) {
        listAllSongs();
        System.out.print("Enter song number to view comments: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < songs.size()) {
            Song song = songs.get(index);
            System.out.println("💬 Comments for " + song.getTitle() + ":");
            for (Comment c : song.getComments()) {
                System.out.printf("- %s (%s): %s%n",
                        c.getAuthor().getUsername(), c.getTimestamp(), c.getText());
            }
            if (song.getComments().isEmpty()) {
                System.out.println("No comments yet.");
            }
        } else {
            System.out.println("❌ Invalid selection.");
        }
    }

    private void searchSongs(Scanner scanner) {
        System.out.print("🔍 Enter song title or artist username to search: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Song song : songs) {
            if (song.getTitle().toLowerCase().contains(keyword) ||
                    song.getArtistUsername().toLowerCase().contains(keyword)) {
                System.out.printf("🎵 %s by %s (%d likes)%n",
                        song.getTitle(), song.getArtistUsername(), song.getLikes());
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ No songs matched your search.");
        }
    }

    private void viewSongsByArtist(Scanner scanner) {
        System.out.print("Enter artist username: ");
        String artistName = scanner.nextLine();

        boolean found = false;
        for (Song song : songs) {
            if (song.getArtistUsername().equalsIgnoreCase(artistName)) {
                System.out.println("🎵 " + song.getTitle());
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ No songs found for this artist.");
        }
    }

    private void showTopLikedSongs() {
        System.out.println("🔥 Top Liked Songs:");

        songs.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getLikes(), s1.getLikes()))
                .limit(5)
                .forEach(song ->
                        System.out.printf("🎵 %s by %s - ❤️ %d likes%n",
                                song.getTitle(), song.getArtistUsername(), song.getLikes()));
    }

    private void addSongToFavorites(Scanner scanner, User user) {
        listAllSongs();
        System.out.print("Enter song number to add to favorites: ");
        int choice = Integer.parseInt(scanner.nextLine()) - 1;

        if (choice >= 0 && choice < songs.size()) {
            user.addFavorite(songs.get(choice));
            System.out.println("❤️ Song added to your favorites.");
        } else {
            System.out.println("❌ Invalid selection.");
        }
    }

    private void viewFavorites(User user) {
        List<Song> favs = user.getFavorites();
        if (favs.isEmpty()) {
            System.out.println("You have no favorite songs.");
            return;
        }

        System.out.println("🎵 Your Favorite Songs:");
        for (Song song : favs) {
            System.out.printf("- %s by %s%n", song.getTitle(), song.getArtistUsername());
        }
    }

    private void createPlaylist(Scanner scanner, User user) {
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();

        Playlist playlist = new Playlist(name);
        user.addPlaylist(playlist);

        System.out.println("✅ Playlist created.");
    }

    private void addSongToPlaylist(Scanner scanner, User user) {
        List<Playlist> playlists = user.getPlaylists();
        if (playlists.isEmpty()) {
            System.out.println("❗ You have no playlists. Create one first.");
            return;
        }

        System.out.println("Select a playlist:");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getName());
        }

        int playlistIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (playlistIndex < 0 || playlistIndex >= playlists.size()) {
            System.out.println("❌ Invalid selection.");
            return;
        }

        Playlist selected = playlists.get(playlistIndex);
        listAllSongs();
        System.out.print("Enter song number to add: ");
        int songIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (songIndex < 0 || songIndex >= songs.size()) {
            System.out.println("❌ Invalid song.");
            return;
        }

        selected.addSong(songs.get(songIndex));
        System.out.println("🎶 Song added to playlist.");
    }

    private void viewPlaylists(User user) {
        List<Playlist> playlists = user.getPlaylists();
        if (playlists.isEmpty()) {
            System.out.println("You have no playlists.");
            return;
        }

        for (Playlist p : playlists) {
            System.out.println("📂 " + p.getName());
            for (Song s : p.getSongs()) {
                System.out.printf("  - %s by %s%n", s.getTitle(), s.getArtistUsername());
            }
        }
    }

    private void searchSongInPlaylists(Scanner scanner, User user) {
        System.out.print("🔍 Enter song title to search in playlists: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;

        for (Playlist p : user.getPlaylists()) {
            for (Song s : p.getSongs()) {
                if (s.getTitle().toLowerCase().contains(keyword)) {
                    System.out.printf("🎵 %s found in playlist '%s'%n", s.getTitle(), p.getName());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("❌ No matching songs found in your playlists.");
        }
    }

    private void removeFavorite(Scanner scanner, User user) {
        List<Song> favs = user.getFavorites();
        if (favs.isEmpty()) {
            System.out.println("💔 You have no favorite songs.");
            return;
        }

        System.out.println("🎵 Your Favorites:");
        for (int i = 0; i < favs.size(); i++) {
            Song s = favs.get(i);
            System.out.printf("%d. %s by %s%n", i + 1, s.getTitle(), s.getArtistUsername());
        }

        System.out.print("Select song number to remove: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < favs.size()) {
            Song removed = favs.remove(index);
            System.out.println("❌ Removed: " + removed.getTitle());
        } else {
            System.out.println("❌ Invalid selection.");
        }
    }

}

