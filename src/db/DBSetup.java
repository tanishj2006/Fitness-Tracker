/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author pearl
 */
import java.sql.Connection;
import java.sql.Statement;

public class DBSetup {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Check if connection is established
            if (conn == null) {
                System.out.println("❌ Connection is null. Exiting...");
                return;
            }

            System.out.println("Creating tables in fitnessdb...");

            // Create users table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS fitnessdb.users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(50) UNIQUE NOT NULL,"
                    + "password VARCHAR(50) NOT NULL)");
            System.out.println("✔ Users table ready.");

            // Create meals table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS fitnessdb.meals ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "user_id INT,"
                    + "meal_name VARCHAR(100),"
                    + "calories INT,"
                    + "protein INT,"
                    + "date DATE,"
                    + "FOREIGN KEY(user_id) REFERENCES fitnessdb.users(id))");
            System.out.println("✔ Meals table ready.");

            // Create workouts table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS fitnessdb.workouts ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "user_id INT,"
                    + "workout_name VARCHAR(100),"
                    + "calories_burnt INT,"
                    + "date DATE,"
                    + "FOREIGN KEY(user_id) REFERENCES fitnessdb.users(id))");
            System.out.println("✔ Workouts table ready.");

            System.out.println("✅ DBSetup: All tables created successfully in fitnessdb.");

        } catch (Exception e) {
            System.out.println("❌ Error during DB setup:");
            e.printStackTrace();
        }
    }
}
