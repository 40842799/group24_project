package com.napier.devops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static java.lang.Thread.sleep;
import static com.napier.devops.Reports.*;

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
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Scanner scanner = null;
            boolean isInteractive = System.console() != null;
            if (isInteractive) {
                scanner = new Scanner(System.in);
            }

            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT COUNT(*) AS total FROM country");
            if (results.next()) {
                System.out.println("Connected to the World database.");
                System.out.println("Countries: " + results.getInt("total"));
            }

            System.out.println("=====================================");
            System.out.println("All the countries in the world organised by largest population to smallest.");
            System.out.println("=====================================");
            r1(connection);
            Thread.sleep(5000);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("All the countries in a continent organised by largest population to smallest.");
            System.out.println("=====================================");
            r2(connection);
            Thread.sleep(5000);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("All the countries in a region organised by largest population to smallest");
            System.out.println("=====================================");
            r3(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("The top `N` populated countries in the world ");
            System.out.println("=====================================");
            r4(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("The top `N` populated countries in a continent");
            System.out.println("=====================================");
            r5(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("The top `N` populated countries in a region");
            System.out.println("=====================================");
            r6(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("All the cities in the world organised by largest population to smallest");
            System.out.println("=====================================");
            r7(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("All the cities in a continent organised by largest population to smallest.");
            System.out.println("=====================================");
            r8(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("All the cities in a region organised by largest population to smallest");
            System.out.println("=====================================");
            r9(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("All the cities in a country organised by largest population to smallest.");
            System.out.println("=====================================");
            r10(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("All the cities in a district organised by largest population to smallest");
            System.out.println("=====================================");
            r11(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("The top `N` populated cities in the world");
            System.out.println("=====================================");
            r12(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("The top `N` populated cities in a continent ");
            System.out.println("=====================================");
            r13(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }
            System.out.println("=====================================");
            System.out.println("The top `N` populated cities in a region");
            System.out.println("=====================================");
            r14(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("The top `N` populated cities in a country ");
            System.out.println("=====================================");
            r15(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("The top `N` populated cities in a district");
            System.out.println("=====================================");
            r16(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }
            System.out.println("=====================================");
            System.out.println("All the capital cities in the world organised by largest population to smallest");
            System.out.println("=====================================");
            r17(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("All the capital cities in a continent organised by largest population to smallest");
            System.out.println("=====================================");
            r18(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("All the capital cities in a region organised by largest to smallest");
            System.out.println("=====================================");
            r19(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("The top `N` populated capital cities in the world");
            System.out.println("=====================================");
            r20(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }
            System.out.println("=====================================");
            System.out.println("The top `N` populated capital cities in a continent ");
            System.out.println("=====================================");
            r21(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }
            System.out.println("=====================================");
            System.out.println("The top `N` populated capital cities in a region ");
            System.out.println("=====================================");
            r22(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }
            System.out.println("=====================================");
            System.out.println("The population of people, people living in cities, and people not living in cities in each continent");
            System.out.println("=====================================");
            r23(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }
            System.out.println("=====================================");
            System.out.println("The population of people, people living in cities, and people not living in cities in each region");
            System.out.println("=====================================");
            r24(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }

            System.out.println("=====================================");
            System.out.println("The population of people, people living in cities, and people not living in cities in each country.");
            System.out.println("=====================================");
            r25(connection);
            System.out.println("Press ENTER to continue...");
            if (isInteractive) {
                scanner.nextLine();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}