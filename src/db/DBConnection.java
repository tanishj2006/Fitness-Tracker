/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/fitnessdb"; // Database ka naam
    private static final String USERNAME = "root"; // Aapka MySQL username
    private static final String PASSWORD = "pearl123"; // Aapka MySQL password

    // Connection return karna
    public static Connection getConnection() throws SQLException {
        try {
            // Connection ko establish karna
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Database connection failed.", e);
        }
    }
}


/**
 *
 * @author pearl
 */
