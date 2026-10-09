package com.napier.devops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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

        // Demo filters used in CI / non-interactive runs.
        final String demoContinent = "Europe";
        final String demoRegion = "British Islands";
        final String demoCountry = "United Kingdom";
        final String demoDistrict = "England";
        final String demoCity = "London";
        final int demoN = 5;

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Scanner scanner = null;
            boolean isInteractive = System.console() != null;
            if (isInteractive) {
                scanner = new Scanner(System.in);
            }

            try (Statement statement = connection.createStatement();
                 ResultSet results = statement.executeQuery(
                         "SELECT COUNT(*) AS total FROM country")) {
                if (results.next()) {
                    System.out.println("Connected to the World database.");
                    System.out.println("Countries: " + results.getInt("total"));
                }
            }

            run("All the countries in the world organised by largest population to smallest.",
                    () -> r1(connection), scanner, isInteractive);

            run("All the countries in a continent organised by largest population to smallest.",
                    () -> r2(connection, demoContinent), scanner, isInteractive);

            run("All the countries in a region organised by largest population to smallest.",
                    () -> r3(connection, demoRegion), scanner, isInteractive);

            run("The top N populated countries in the world.",
                    () -> r4(connection, demoN), scanner, isInteractive);

            run("The top N populated countries in a continent.",
                    () -> r5(connection, demoContinent, demoN), scanner, isInteractive);

            run("The top N populated countries in a region.",
                    () -> r6(connection, demoRegion, demoN), scanner, isInteractive);

            run("All the cities in the world organised by largest population to smallest.",
                    () -> r7(connection), scanner, isInteractive);

            run("All the cities in a continent organised by largest population to smallest.",
                    () -> r8(connection, demoContinent), scanner, isInteractive);

            run("All the cities in a region organised by largest population to smallest.",
                    () -> r9(connection, demoRegion), scanner, isInteractive);

            run("All the cities in a country organised by largest population to smallest.",
                    () -> r10(connection, demoCountry), scanner, isInteractive);

            run("All the cities in a district organised by largest population to smallest.",
                    () -> r11(connection, demoDistrict), scanner, isInteractive);

            run("The top N populated cities in the world.",
                    () -> r12(connection, demoN), scanner, isInteractive);

            run("The top N populated cities in a continent.",
                    () -> r13(connection, demoContinent, demoN), scanner, isInteractive);

            run("The top N populated cities in a region.",
                    () -> r14(connection, demoRegion, demoN), scanner, isInteractive);

            run("The top N populated cities in a country.",
                    () -> r15(connection, demoCountry, demoN), scanner, isInteractive);

            run("The top N populated cities in a district.",
                    () -> r16(connection, demoDistrict, demoN), scanner, isInteractive);

            run("All the capital cities in the world organised by largest population to smallest.",
                    () -> r17(connection), scanner, isInteractive);

            run("All the capital cities in a continent organised by largest population to smallest.",
                    () -> r18(connection, demoContinent), scanner, isInteractive);

            run("All the capital cities in a region organised by largest to smallest.",
                    () -> r19(connection, demoRegion), scanner, isInteractive);

            run("The top N populated capital cities in the world.",
                    () -> r20(connection, demoN), scanner, isInteractive);

            run("The top N populated capital cities in a continent.",
                    () -> r21(connection, demoContinent, demoN), scanner, isInteractive);

            run("The top N populated capital cities in a region.",
                    () -> r22(connection, demoRegion, demoN), scanner, isInteractive);

            run("Population of people, living in cities, and not living in cities in each continent.",
                    () -> r23(connection), scanner, isInteractive);

            run("Population of people, living in cities, and not living in cities in each region.",
                    () -> r24(connection), scanner, isInteractive);

            run("Population of people, living in cities, and not living in cities in each country.",
                    () -> r25(connection), scanner, isInteractive);

            run("The population of the world.",
                    () -> r26(connection), scanner, isInteractive);

            run("The population of a continent.",
                    () -> r27(connection, demoContinent), scanner, isInteractive);

            run("The population of a region.",
                    () -> r28(connection, demoRegion), scanner, isInteractive);

            run("The population of a country.",
                    () -> r29(connection, demoCountry), scanner, isInteractive);

            run("The population of a district.",
                    () -> r30(connection, demoDistrict), scanner, isInteractive);

            run("The population of a city.",
                    () -> r31(connection, demoCity), scanner, isInteractive);

            run("Number of people who speak Chinese, English, Hindi, Spanish, Arabic.",
                    () -> r32(connection), scanner, isInteractive);
        }
    }

    @FunctionalInterface
    private interface ReportAction {
        void run() throws SQLException;
    }

    private static void run(
            String title, ReportAction action, Scanner scanner, boolean isInteractive)
            throws SQLException {
        System.out.println("=====================================");
        System.out.println(title);
        System.out.println("=====================================");
        action.run();
        System.out.println("Press ENTER to continue...");
        if (isInteractive && scanner != null) {
            scanner.nextLine();
        }
    }
}
