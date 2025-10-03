/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.Statement;

public class DBSetup {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            if (conn == null) {
                System.out.println("Connection is null. Exiting...");
                return;
            }

            // First drop child tables, then parent
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            stmt.executeUpdate("DROP TABLE IF EXISTS workouts");
            stmt.executeUpdate("DROP TABLE IF EXISTS meals");
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            // Users table
            stmt.executeUpdate("CREATE TABLE users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(50) UNIQUE NOT NULL,"
                    + "password VARCHAR(50) NOT NULL,"
                    + "weight DOUBLE,"
                    + "height DOUBLE)");

            // Meals table
            stmt.executeUpdate("CREATE TABLE meals ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "user_id INT,"
                    + "meal_name VARCHAR(100),"
                    + "calories INT,"
                    + "date DATE,"
                    + "FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE)");

            // Workouts table
            stmt.executeUpdate("CREATE TABLE workouts ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "user_id INT,"
                    + "workout_name VARCHAR(100),"
                    + "duration_mins INT,"
                    + "calories_burned INT,"
                    + "date DATE DEFAULT CURRENT_DATE,"
                    + "FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE)");

            // Insert ek default user (id = 1)
            stmt.executeUpdate("INSERT INTO users (name, password, weight, height) "
                    + "VALUES ('testuser', '1234', 70, 175)");

            System.out.println("Tables recreated successfully with default user!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
