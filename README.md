# 🎶 Nowruz Genius CLI

A Java CLI application to manage a music platform with user accounts, albums, songs, comments, favorites, and playlists. Built with Gradle and Gson.

---

## 🚀 Features

✔️ **Login/Register system** with 3 roles:
- **Admin**: Approve artists
- **Artist**: Create albums & songs
- **User**: Like, comment, search, manage favorites & playlists

🎧 **Songs** have lyrics, comments, and like count  
📁 All data stored in `.json` files using Gson  
🎛 Fully menu-driven CLI interface

---

## 🧱 Project Structure

Nowruz-Project/
├── build.gradle
├── settings.gradle
├── README.md
├── accounts.json
├── albums.json
├── songs.json
├── comments.json
└── genius/
└── src/
└── main/
└── java/
└── org/example/
├── Main.java
├── AppMenu.java
├── Account.java
├── Admin.java
├── Artist.java
├── User.java
├── Song.java
├── Album.java
├── Comment.java
├── Playlist.java
├── Role.java
├── DataManager.java
└── AccountAdapterFactory.java

---

## 📂 Data Files

| File            | Description                      |
|-----------------|----------------------------------|
| `accounts.json` | Stores Admins, Artists, Users    |
| `albums.json`   | List of albums                   |
| `songs.json`    | All songs with lyrics            |
| `comments.json` | Comments on songs                |



## 👤 Default Test Accounts

| Role |Username |Password |
|----------|--------|---------|
|Admin|admin123|adminpass|
|Artist|artist123|artistpass|

You can log in and test the system right away.

---

## 📌 Notes

- 📝 All files are **auto-saved** (no need to press save).
- 🌐 The project is **100% in English** (inputs/outputs).
- 🖥️ **CLI-based**, no GUI required.


---

## 🙌 Author

Developed with ❤️ as part of a **Nowruz Java Project(genius)**  
by **Mohammad Hosseini**

> _Happy coding and enjoy the music vibes!_ 🎉🎶
