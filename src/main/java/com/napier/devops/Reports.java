package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Reports {

    // ============================================================
    // All countries in the world
    // ============================================================
    public static void r1(Connection connection)
            throws SQLException {

        String sql =
                "SELECT Code, Name, Continent, Region, Population, Capital " +
                        "FROM country " +
                        "ORDER BY Population DESC";

        executeQuery(connection, sql);
    }


    // ============================================================
    // All countries in a continent organised by largest population to smallest.
    // ============================================================
    public static void r2(Connection connection)
            throws SQLException {

        String sql =
                "SELECT DISTINCT country.Code, country.Name, country.Continent, " +
                        "country.Region, country.Population, city.Name " +
                        "FROM country " +
                        "JOIN city ON country.Code = city.CountryCode " +
                        "WHERE Continent = ANY(SELECT Continent FROM country) " +
                        "ORDER BY Population DESC";

        executeQuery(connection, sql);
    }


    // ============================================================
    // All countries in a region
    // ============================================================
    public static void r3(Connection connection)
            throws SQLException {

        String sql =
                "SELECT Code, Name, Continent, Region, Population, Capital " +
                        "FROM country " +
                        "WHERE Region = ANY(SELECT Region FROM country) " +
                        "ORDER BY Population DESC";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Top N populated countries in the world
    // ============================================================
    public static void r4(Connection connection) throws SQLException {

        String sql =
                "SELECT Code, Name, Continent, Region, Population, Capital " +
                        "FROM country " +
                        "ORDER BY Population DESC " +
                        "LIMIT 0, 20";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Top N populated countries in a continent
    // ============================================================
    public static void r5(Connection connection)
            throws SQLException {

        String sql =
                "SELECT Code, Name, Continent, Region, Population, Capital " +
                        "FROM country " +
                        "WHERE Continent = ANY(SELECT Continent FROM country)" +
                        "ORDER BY Population DESC " +
                        "LIMIT 0, 20";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Top N populated countries in a region
    // ============================================================
    public static void r6(Connection connection)
            throws SQLException {

        String sql =
                "SELECT Code, Name, Continent, Region, Population, Capital " +
                        "FROM country " +
                        "WHERE Region = ANY(SELECT Region FROM country)" +
                        "ORDER BY Population DESC " +
                        "LIMIT 0, 20";

        executeQuery(connection, sql);
    }


    // ============================================================
    // All cities in the world
    // ============================================================
    public static void r7(Connection connection)
            throws SQLException {

        String sql =
                "SELECT city.Name AS `CITY NAME`, " +
                        "country.Name AS `COUNTRY NAME`, " +
                        "city.District, " +
                        "city.Population " +
                        "FROM city " +
                        "JOIN country ON country.Code = city.CountryCode " +
                        "ORDER BY city.Population DESC";

        executeQuery(connection, sql);
    }


    // ============================================================
    // All cities in a continent
    // ============================================================
    public static void r8(Connection connection)
            throws SQLException {

        String sql =
                "SELECT city.Name AS `CITY NAME`, " +
                        "country.Name AS `COUNTRY NAME`, " +
                        "city.District, " +
                        "city.Population " +
                        "FROM city " +
                        "JOIN country ON country.Code = city.CountryCode " +
                        "WHERE country.Continent = ANY(SELECT Continent FROM country) " +
                        "ORDER BY city.Population DESC";

        executeQuery(connection, sql);
    }


    // ============================================================
    // All cities in a region
    // ============================================================
    public static void r9(Connection connection)
            throws SQLException {

        String sql =
                "SELECT city.Name AS `CITY NAME`, " +
                        "country.Name AS `COUNTRY NAME`, " +
                        "city.District, " +
                        "city.Population " +
                        "FROM city " +
                        "JOIN country ON country.Code = city.CountryCode " +
                        "WHERE country.Region = ANY(SELECT Region FROM country)" +
                        "ORDER BY city.Population DESC ";

        executeQuery(connection, sql);
    }


    // ============================================================
    // All cities in a country organized by largest population to smallest
    // ============================================================
    public static void r10(Connection connection)
            throws SQLException {

        String sql =
                "SELECT city.Name, city.CountryCode, " +
                        "city.District, city.Population " +
                        "FROM city " +
                        "JOIN country ON country.Code = city.CountryCode " +
                        "ORDER BY city.Population DESC, city.Name ASC ";

        executeQuery(connection, sql);
    }


    // ============================================================
    // All cities in a district
    // ============================================================
    public static void r11(Connection connection)
            throws SQLException {

        String sql =
                "SELECT District, Name, Population " +
                        "FROM city " +
                        "ORDER BY District ASC, Name ASC, Population DESC ";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Top N populated cities in the world
    // ============================================================
    public static void r12(Connection connection)
            throws SQLException {

        String sql =
                "SELECT Name, CountryCode, District, Population " +
                        "FROM city " +
                        "ORDER BY Population DESC " +
                        "LIMIT 0, 20";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Top N populated cities in a continent
    // ============================================================
    public static void r13(Connection connection)
            throws SQLException {

        String sql =
                "SELECT city.Name AS `CITY NAME`, " +
                        "country.Continent AS `Continent`, " +
                        "city.Population " +
                        "FROM city " +
                        "JOIN country ON country.Code = city.CountryCode " +
                        "WHERE country.Continent = ANY(SELECT Continent FROM country) " +
                        "ORDER BY city.Population DESC " +
                        "LIMIT 0, 20";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Top N populated cities in a region
    // ============================================================
    public static void r14(Connection connection)
            throws SQLException {

        String sql =
                "SELECT city.Name AS `CITY NAME`, " +
                        "country.Region AS `Region`, " +
                        "city.Population " +
                        "FROM city " +
                        "JOIN country ON country.Code = city.CountryCode " +
                        "WHERE country.Region = ANY(SELECT Region FROM country) " +
                        "ORDER BY city.Population DESC " +
                        "LIMIT 0, 20";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Top N populated cities in a country
    // ============================================================
    public static void r15(Connection connection)
            throws SQLException {

        String sql =
                "SELECT city.Name AS `CITY NAME`, " +
                        "country.Name AS `COUNTRY NAME`, " +
                        "city.Population " +
                        "FROM city " +
                        "JOIN country ON country.Code = city.CountryCode " +
                        "WHERE country.Name = ANY(SELECT Name FROM country) " +
                        "ORDER BY city.Population DESC " +
                        "LIMIT 0, 20";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Top N populated cities in a district
    // ============================================================
    public static void r16(Connection connection)
            throws SQLException {

        String sql =
                "SELECT District, Name, Population " +
                        "FROM city " +
                        "ORDER BY District ASC, Name ASC, Population DESC " +
                        "LIMIT 0, 20";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Capital cities of all countries
    // ============================================================
    public static void r17(Connection connection) throws SQLException {

        String sql =
                "SELECT country.Name AS `COUNTRY NAME`, " +
                        "city.Name AS `CAPITAL CITY`, " +
                        "city.Population " +
                        "FROM country " +
                        "JOIN city ON city.ID = country.Capital " +
                        "ORDER BY city.Population DESC";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Capital cities in a continent organized by largest population to smallest
    // ============================================================
    public static void r18(Connection connection)
            throws SQLException {

        String sql =
                "SELECT DISTINCT country.Name AS `COUNTRY NAME`, " +
                        "city.Name AS `CAPITAL CITY`, " +
                        "country.Continent AS `CONTINENT`, " +
                        "max(city.Population) AS `Population` " +
                        "FROM country " +
                        "JOIN city ON city.CountryCode = country.Code " +
                        "WHERE country.Continent = ANY(SELECT Continent FROM country) " +
                        "GROUP BY country.Name, city.Name, country.Continent  " +
                        "ORDER BY MAX(city.Population) DESC";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Capital cities in a region organised by largest to smallest
    // ============================================================
    public static void r19(Connection connection)
            throws SQLException {

        String sql =
                "SELECT country.Name AS `COUNTRY NAME`, " +
                        "city.Name AS `CAPITAL CITY`, " +
                        "country.Region AS `REGION`, " +
                        "city.Population " +
                        "FROM country " +
                        "JOIN city ON city.CountryCode = country.Code " +
                        "WHERE country.Region = ANY(SELECT Region FROM country) " +
                        "ORDER BY city.Population DESC";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Top N populated capital cities in the world
    // ============================================================
    public static void r20(Connection connection) throws SQLException {

        String sql =
                "SELECT DISTINCT country.Name AS `COUNTRY NAME`, " +
                        "city.Name AS `CAPITAL CITY`, " +
                        "max(city.Population) AS `Population` " +
                        "FROM country " +
                        "JOIN city ON city.CountryCode = country.Code " +
                        "WHERE country.Name = ANY(SELECT Name FROM country) " +
                        "GROUP BY country.Name, city.Name " +
                        "ORDER BY MAX(city.Population) DESC " +
                        "LIMIT 0, 20";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Top N populated capital cities in a continent
    // ============================================================
    public static void r21(Connection connection)
            throws SQLException {

        String sql =
                "SELECT country.Name AS `COUNTRY NAME`, " +
                        "city.Name AS `CAPITAL CITY`, " +
                        "country.Continent AS `CONTINENT`, " +
                        "max(city.Population) AS `Population` " +
                        "FROM country " +
                        "JOIN city ON city.CountryCode = country.Code " +
                        "WHERE country.Continent = ANY(SELECT Continent FROM country) " +
                        "GROUP BY country.Name, city.Name, country.Continent  " +
                        "ORDER BY MAX(city.Population) DESC " +
                        "LIMIT 0, 20";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Top N populated capital cities in a region
    // ============================================================
    public static void r22(Connection connection)
            throws SQLException {

        String sql =
                "SELECT DISTINCT country.Name AS `COUNTRY NAME`, " +
                        "city.Name AS `CAPITAL CITY`, " +
                        "country.Region AS `REGION`, " +
                        "max(city.Population) AS `Population` " +
                        "FROM country " +
                        "JOIN city ON city.CountryCode = country.Code " +
                        "WHERE country.Region = ANY(SELECT Region FROM country) " +
                        "GROUP BY country.Name, city.Name, country.Region  " +
                        "ORDER BY MAX(city.Population) DESC " +
                        "LIMIT 0, 20";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Population by continent
    //
    // Shows:
    // - Total population of countries
    // - Population living in cities
    // - Population not living in cities
    // ============================================================
    public static void r23(Connection connection) throws SQLException {

        String sql =
                "SELECT country.Continent AS `CONTINENT`, " +
                        "SUM(country.Population) AS `TOTAL POPULATION`, " +
                        "COALESCE(SUM(city_population.CityPopulation), 0) AS `CITY POPULATION`, " +
                        "(SUM(country.Population) - " +
                        " COALESCE(SUM(city_population.CityPopulation), 0)) " +
                        "AS `POPULATION NOT IN CITIES` " +
                        "FROM country " +
                        "LEFT JOIN (" +
                        "    SELECT CountryCode, SUM(Population) AS CityPopulation " +
                        "    FROM city " +
                        "    GROUP BY CountryCode" +
                        ") city_population " +
                        "ON city_population.CountryCode = country.Code " +
                        "GROUP BY country.Continent " +
                        "ORDER BY country.Continent";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Population by region
    //
    // Shows:
    // - Total population
    // - Population living in cities
    // - Population not living in cities
    // ============================================================
    public static void r24(Connection connection) throws SQLException {

        String sql =
                "SELECT country.Region AS `REGION`, " +
                        "SUM(country.Population) AS `TOTAL POPULATION`, " +
                        "COALESCE(SUM(city_population.CityPopulation), 0) AS `CITY POPULATION`, " +
                        "(SUM(country.Population) - " +
                        " COALESCE(SUM(city_population.CityPopulation), 0)) " +
                        "AS `POPULATION NOT IN CITIES` " +
                        "FROM country " +
                        "LEFT JOIN (" +
                        "    SELECT CountryCode, SUM(Population) AS CityPopulation " +
                        "    FROM city " +
                        "    GROUP BY CountryCode" +
                        ") city_population " +
                        "ON city_population.CountryCode = country.Code " +
                        "GROUP BY country.Region " +
                        "ORDER BY country.Region";

        executeQuery(connection, sql);
    }


    // ============================================================
    // Population by country
    //
    // Shows:
    // - Total population
    // - Population living in cities
    // - Population not living in cities
    // ============================================================
    public static void r25(Connection connection) throws SQLException {

        String sql =
                "SELECT country.Name AS `COUNTRY`, " +
                        "country.Population AS `TOTAL POPULATION`, " +
                        "COALESCE(city_population.CityPopulation, 0) AS `CITY POPULATION`, " +
                        "(country.Population - " +
                        " COALESCE(city_population.CityPopulation, 0)) " +
                        "AS `POPULATION NOT IN CITIES` " +
                        "FROM country " +
                        "LEFT JOIN (" +
                        "    SELECT CountryCode, SUM(Population) AS CityPopulation " +
                        "    FROM city " +
                        "    GROUP BY CountryCode" +
                        ") city_population " +
                        "ON city_population.CountryCode = country.Code " +
                        "ORDER BY country.Population DESC";

        executeQuery(connection, sql);
    }

    // ============================================================
    // Runs a SQL query and prints the results
    // ============================================================
    private static void executeQuery(Connection connection, String sql, Object... parameters)
            throws SQLException {

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            // Add parameters such as continent, region, country or N
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }

            try (ResultSet results = statement.executeQuery()) {

                ResultSetMetaData metadata = results.getMetaData();

                int columnCount = metadata.getColumnCount();

                // Print column headings
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(
                            metadata.getColumnLabel(i));

                    if (i < columnCount) {
                        System.out.print(" | ");
                    }
                }

                System.out.println();

                // Print rows
                while (results.next()) {

                    for (int i = 1; i <= columnCount; i++) {

                        System.out.print(results.getString(i));

                        if (i < columnCount) {
                            System.out.print(" | ");
                        }
                    }

                    System.out.println();
                }
            }
        }
    }
}