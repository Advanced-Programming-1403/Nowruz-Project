package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎬 Starting Genius CLI...");

        try {
            AppMenu menu = new AppMenu();
            menu.start();
            System.out.println("✅ Program finished.");
        } catch (Exception e) {
            System.err.println("❌ An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
