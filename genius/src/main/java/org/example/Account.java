package org.example;

public class Account {
    protected String username;
    protected String password;
    protected String email;

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Account() {

    }

    // Getterها
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }

    // Setterها
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public enum Role {
        USER,
        ARTIST,
        ADMIN
    }
    protected Role role;

    public Role getRole() {
        return role;
    }

}
