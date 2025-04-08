package org.example;

public class Admin extends Account {

    public Admin(String username, String password, String email) {
        super(username, password, email);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    // می‌تونیم اینجا بعداً متدهایی مثل deleteUser، deleteSong و ... هم اضافه کنیم
}
