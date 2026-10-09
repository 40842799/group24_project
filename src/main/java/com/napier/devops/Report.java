package com.napier.devops;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
// import java.awt.*;

public class Report {
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
        try {

            results = statement.executeQuery(s);

            // Create table model
            model = new DefaultTableModel();

           // setSize(800, 400);
           // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           // setLocationRelativeTo(null);

            // Get column information
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Add column names to JTable
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnLabel(i));
            }

            // Add rows from ResultSet
            while (results.next()) {

                Object[] row = new Object[columnCount];

                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = results.getObject(i);
                }

                model.addRow(row);
            }

            // Get the column names
            ResultSetMetaData md = results.getMetaData();
            columnCount = md.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(md.getColumnLabel(i));
            }

            /*
            results.next() moves to the next database record.
            results.getObject(i) reads a column value.
            model.addRow(row) inserts that record into the JTable.
            */

            while (results.next()) {
                Object[] row = new Object[columnCount];

                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = results.getObject(i);
                }

                model.addRow(row);

                // Create JTable
                table = new JTable(model);

                // Add table to scroll pane
                JScrollPane scrollPane = new JScrollPane(table);
                // add(scrollPane, BorderLayout.CENTER);

                JOptionPane.showMessageDialog(null, model, "Print Report", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (
                SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Database Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }

}
