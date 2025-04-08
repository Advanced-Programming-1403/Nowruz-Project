package org.example;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("ali", "123", "ali@mail.com"));
        users.add(new User("zahra", "456", "zahra@mail.com"));

        // Save data
        DataManager.saveData(users, "users.json");

        // Load data
        List<User> loadedUsers = DataManager.loadData("users.json", new TypeToken<List<User>>() {}.getType());

        if (loadedUsers != null) {
            System.out.println("Loaded users count: " + loadedUsers.size());
        }
    }
}
