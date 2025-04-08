package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

public class DataManager {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();


    public static <T> void saveData(List<T> data, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
            System.out.println("✅ Data saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Error saving data: " + e.getMessage());
        }
    }

    public static <T> List<T> loadData(String filePath, Type typeOfT) {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, typeOfT);
        } catch (IOException e) {
            System.err.println("❌ Error loading data: " + e.getMessage());
            return null;
        }
    }
}
