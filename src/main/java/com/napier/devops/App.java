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

            Report r = new Report();

            r.createConnection();
            
            int choice, N;
            System.out.println("1. All the countries in the world organised by largest population to smallest.");
            System.out.println("2. All the countries in a continent organised by largest population to smallest.");
            System.out.println("3. All the countries in a region organised by largest population to smallest");
            System.out.print("4. The top `N` populated countries in the world ");
            System.out.print("\t5.  populated countries in a continent");
            System.out.print("\t6.  populated countries in a region");
            System.out.println("7. All the cities in the world organised by largest population to smallest");
            System.out.println("8. All the cities in a continent organised by largest population to smallest.");
            System.out.println("9. All the cities in a region organised by largest population to smallest");
            System.out.println("10. All the cities in a country organised by largest population to smallest.");
            System.out.println("11. All the cities in a district organised by largest population to smallest");
            System.out.println("12. The top `N` populated cities in the world");
            System.out.println("13. populated cities in a continent ");
            System.out.println("14. The top `N` populated cities in a region");
            System.out.println("15. The top `N` populated cities in a country ");
            System.out.println("16. The top `N` populated cities in a district");
            System.out.println("17. All the capital cities in the world organised by largest population to smallest");
            System.out.println("18. All the capital cities in a continent organised by largest population to smallest");
            System.out.println("19. All the capital cities in a region organised by largest to smallest");
            System.out.println("20. The top `N` populated capital cities in the world");
            System.out.println("21. The top `N` populated capital cities in a continent ");
            System.out.println("22. The top `N` populated capital cities in a region ");
            System.out.println("23. The population of people, people living in cities, and people not living in cities in each continent");
            System.out.println("24. The population of people, people living in cities, and people not living in cities in each region");
            System.out.println("25. The population of people, people living in cities, and people not living in cities in each country.");
            Scanner s = new Scanner(System.in);

            System.out.print("Give your choice number, Which report you want to generate ? ");
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    r.connectRunQuery("SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC");
                    break;
                case 2:
                    r.connectRunQuery("SELECT DISTINCT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name FROM country JOIN city ON country.Code = city.CountryCode WHERE Continent = ANY(SELECT Continent FROM country) ORDER BY Population DESC");
                    break;
                case 3:
                    r.connectRunQuery("SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = ANY(SELECT Region FROM country)  ORDER BY Population DESC ");
                    break;
                case 4:
                    System.out.print("How many top record for which you want to generate report?");
                    N = s.nextInt();

                    r.connectRunQuery("SELECT Code, Name, Continent, Region, Population, Capital FROM country  ORDER BY Population DESC LIMIT 0, " + N);
                    break;
                case 5:
                    System.out.print("How many top record for which you want to generate report?");
                    N = s.nextInt();

                    r.connectRunQuery("SELECT Code, Name, Continent, Region, Population, Capital FROM country  WHERE Continent = ANY(SELECT Continent FROM country) ORDER BY Population DESC LIMIT 0, " + N);
                    break;
                case 6:
                    System.out.print("How many top record for which you want to generate report?");
                    N = s.nextInt();

                    r.connectRunQuery("SELECT Code, Name, Continent, Region, Population, Capital FROM country  WHERE Region = ANY(SELECT Region FROM country) ORDER BY Population DESC LIMIT 0, " + N);
                    break;
                case 7:
                    r.connectRunQuery("SELECT city.Name AS `CITY NAME`, country.Name AS `COUNTRY NAME`, city.District, city.Population  FROM city JOIN country ON country.Code = city.CountryCode  ORDER BY city.Population DESC");
                    break;
                case 8:
                    r.connectRunQuery("SELECT city.Name AS 'CITY NAME',  country.Name AS 'COUNTRY NAME', city.District, city.Population FROM city JOIN country ON country.Code = city.CountryCode  WHERE country.Continent = ANY(SELECT Continent FROM country)  ORDER BY city.Population DESC");
                    break;
                case 9:
                    r.connectRunQuery("SELECT city.Name AS CITY NAME,  country.Name AS `COUNTRY NAME`, city.District,  city.Population  FROM city  JOIN country ON country.Code = city.CountryCode  WHERE country.Region = ANY(SELECT Region FROM country) ORDER BY city.Population DESC");
                    break;
            }

            /*
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
             */
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
