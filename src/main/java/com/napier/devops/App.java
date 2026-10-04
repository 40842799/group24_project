package com.napier.devops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws SQLException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            throw new IllegalStateException(
                    "Set DB_URL, DB_USER and DB_PASSWORD before running.");
        }

        // Close the connection and query resources automatically.
        try (Connection connection =
                     DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(
                     "SELECT COUNT(*) AS total FROM country")) {

            if (results.next()) {
                System.out.println("Connected to the World database.");
                System.out.println("Countries: " + results.getInt("total"));
            }
        }
    }
}