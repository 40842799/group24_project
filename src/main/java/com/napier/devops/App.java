package com.napier.devops;

import java.sql.*;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
*/
import java.util.Scanner;

import static java.lang.Thread.sleep;
import static com.napier.devops.Reports.*;

public class App {
    public static void main(String[] args) throws SQLException {

        Report r = new Report();
        r.createConnection();
        int choice;

        System.out.println("1. All the countries in the world organised by largest population to smallest.");
        System.out.println("2. All the countries in a continent organised by largest population to smallest.");
        System.out.println("3. All the countries in a region organised by largest population to smallest");
        System.out.println("4. The top `N` populated countries in the world ");
        System.out.println("5. The top `N` populated countries in a continent");
        System.out.println("6. The top `N` populated countries in a region");
        System.out.println("7. All the cities in the world organised by largest population to smallest");
        System.out.println("8. All the cities in a continent organised by largest population to smallest.");
        System.out.println("9. All the cities in a region organised by largest population to smallest");
        System.out.println("10. All the cities in a country organised by largest population to smallest.");
        System.out.println("11. All the cities in a district organised by largest population to smallest");
        System.out.println("12. The top `N` populated cities in the world");
        System.out.println("13. The top `N` populated cities in a continent ");
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
        //r.r25(connection);
        System.out.print("Give your choice number, Which report you want to generate ? ");
        choice = s.nextInt();

        switch (choice) {
            case 1:
                r.reportPrint("SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC");
                break;
            case 2:
                r.reportPrint("SELECT DISTINCT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name FROM country JOIN city ON country.Code = city.CountryCode WHERE Continent = ANY(SELECT Continent FROM country) ORDER BY Population DESC");
                break;
/*                case 3:
                    r.reportPrint("");
                    break;
                case 4:
                    r.reportPrint("");
                    break;
                case 5:
                    r.reportPrint("");
                    break;
                case 6:
                    r.reportPrint("");
                    break;
                case 7:
                    r.reportPrint("");
                    break;
                case 8:
                    r.reportPrint("");
                    break;
                case 9:
                    r.reportPrint("");
                    break;
                case 10:
                    r.reportPrint("");
                    break;
                case 11:
                    r.reportPrint("");
                    break;
                case 12:
                    r.reportPrint("");
                    break;
                case 13:
                    r.reportPrint("");
                    break;
                case 14:
                    r.reportPrint("");
                    break;
                case 15:
                    r.reportPrint("");
                    break;
                case 16:
                    r.reportPrint("");
                    break;
                case 17:
                    r.reportPrint("");
                    break;
                case 18:
                    r.reportPrint("");
                    break;
                case 19:
                    r.reportPrint("");
                    break;
                case 20:
                    r.reportPrint("");
                    break;
                case 21:
                    r.reportPrint("");
                    break;
                case 22:
                    r.reportPrint("");
                    break;
                case 23:
                   /* Population by continent

                    Shows:
                    - Total population of countries
                    - Population living in cities
                   - Population not living in cities */
                    /*r.reportPrint("SELECT country.Continent AS `CONTINENT`, SUM(country.Population) AS `TOTAL POPULATION`,  COALESCE(SUM(city_population.CityPopulation), 0) AS `CITY POPULATION`,  (SUM(country.Population) -   COALESCE(SUM(city_population.CityPopulation), 0))   AS `POPULATION NOT IN CITIES`  FROM country  LEFT JOIN ( SELECT CountryCode, SUM(Population) AS CityPopulation     FROM city    GROUP BY CountryCode) city_population  ON city_population.CountryCode = country.Code  GROUP BY country.Continent  ORDER BY country.Continent");
                    break;
                case 24:
                    r.reportPrint("SELECT country.Region AS `REGION`, SUM(country.Population) AS `TOTAL POPULATION`,  COALESCE(SUM(city_population.CityPopulation), 0) AS `CITY POPULATION`, \" +\n" + (SUM(country.Population) - COALESCE(SUM(city_population.CityPopulation), 0))AS POPULATION NOT IN CITIES FROM country LEFT JOIN(SELECT CountryCode, SUM(Population)AS CityPopulation FROM city GROUP BY CountryCode)city_population ON city_population.CountryCode = country.Code GROUP BY country.Region ORDER BY country.Region");
                    break;
                case 25:
                    r.reportPrint("SELECT country.Name AS COUNTRY, country.Population AS `TOTAL POPULATION`, COALESCE(city_population.CityPopulation, 0) AS `CITY POPULATION`, (country.Population - COALESCE(city_population.CityPopulation, 0)) AS `POPULATION NOT IN CITIES FROM country LEFT JOIN (SELECT CountryCode, SUM(Population) AS CityPopulation FROM city GROUP BY CountryCode) city_population ON city_population.CountryCode = country.Code ORDER BY country.Population DESC");
                    break;*/
            default:
                System.out.print("Wrong choice number, Please give proper number, Which report you want to generate ? ");
                break;
        }

        /*

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
    */

    }
}