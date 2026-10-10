package com.napier.devops;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
// import java.awt.*;

public class Report {
    String query;
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

    public void connectRunQuery(String runQuery) throws java.sql.SQLException {

        // Create a table model
        DefaultTableModel model = new DefaultTableModel();

        // Execute a SELECT query and get the result set
        query = runQuery;
        // Statement stat = null;
        // ResultSet results = null;

        try {
            statement = connection.createStatement();
            results = statement.executeQuery(query);

            // Get the column names
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++)
                columnNames[i - 1] = metaData.getColumnName(i);

            model.setColumnIdentifiers(columnNames);

            // Add the rows to the table model
            while (results.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++)
                    row[i - 1] = results.getObject(i);

                model.addRow(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (results != null) results.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // Create the JTable and set the model
        JTable table = new JTable(model);

        // Add the table to a scroll pane
        // Create the scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Create the frame and add the scroll pane to it
        JFrame frame = new JFrame("Report");
        frame.add(scrollPane);

        // Set the size and location of the frame
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);

    }

}
