package com.napier.devops;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class Report extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    String url = "", user = "", password = "";
    Connection connection;
    Statement statement;
    ResultSet results;

    public void createConnection() throws java.sql.SQLException {
        url = System.getenv("DB_URL");
        user = System.getenv("DB_USER");
        password = System.getenv("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            throw new IllegalStateException(
                    "Set DB_URL, DB_USER and DB_PASSWORD before running.");
        }

        // Close the connection and query resources automatically.
        //try {
        connection = DriverManager.getConnection(url, user, password);
        Scanner scanner = new Scanner(System.in);
        statement = connection.createStatement();
        PreparedStatement pstatement;

        /*   results = statement.executeQuery("SELECT COUNT(*) AS total FROM country");
        if (results.next()) {
            System.out.println("Connected to the World database.");
            System.out.println("Countries: " + results.getInt("total"));
        }*/

    }

    public void reportPrint(String s) throws java.sql.SQLException {
        results = statement.executeQuery(s);
    }

}
