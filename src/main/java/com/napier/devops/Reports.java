package com.napier.devops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Population reports against the MySQL World sample database.
 * Column sets follow the SET09803 assessment brief.
 */
public class Reports {

    // Country report: Code, Name, Continent, Region, Population, Capital (name)
    private static final String COUNTRY_SELECT =
            "SELECT country.Code, country.Name, country.Continent, country.Region, "
                    + "country.Population, capital.Name AS Capital "
                    + "FROM country "
                    + "LEFT JOIN city AS capital ON capital.ID = country.Capital ";

    // City report: Name, Country, District, Population
    private static final String CITY_SELECT =
            "SELECT city.Name, country.Name AS Country, city.District, city.Population "
                    + "FROM city "
                    + "JOIN country ON country.Code = city.CountryCode ";

    // Capital city report: Name, Country, Population
    private static final String CAPITAL_SELECT =
            "SELECT city.Name, country.Name AS Country, city.Population "
                    + "FROM country "
                    + "JOIN city ON city.ID = country.Capital ";

    /**
     * R1: All countries in the world by population DESC.
     */
    public static void r1(Connection connection) throws SQLException {
        executeQuery(connection, COUNTRY_SELECT + "ORDER BY country.Population DESC");
    }

    /**
     * R2: All countries in a continent by population DESC.
     */
    public static void r2(Connection connection, String continent) throws SQLException {
        executeQuery(
                connection,
                COUNTRY_SELECT + "WHERE country.Continent = ? ORDER BY country.Population DESC",
                continent);
    }

    /**
     * R3: All countries in a region by population DESC.
     */
    public static void r3(Connection connection, String region) throws SQLException {
        executeQuery(
                connection,
                COUNTRY_SELECT + "WHERE country.Region = ? ORDER BY country.Population DESC",
                region);
    }

    /**
     * R4: Top N countries in the world.
     */
    public static void r4(Connection connection, int n) throws SQLException {
        executeQuery(
                connection,
                COUNTRY_SELECT + "ORDER BY country.Population DESC LIMIT ?",
                n);
    }

    /**
     * R5: Top N countries in a continent.
     */
    public static void r5(Connection connection, String continent, int n) throws SQLException {
        executeQuery(
                connection,
                COUNTRY_SELECT
                        + "WHERE country.Continent = ? "
                        + "ORDER BY country.Population DESC LIMIT ?",
                continent,
                n);
    }

    /**
     * R6: Top N countries in a region.
     */
    public static void r6(Connection connection, String region, int n) throws SQLException {
        executeQuery(
                connection,
                COUNTRY_SELECT
                        + "WHERE country.Region = ? "
                        + "ORDER BY country.Population DESC LIMIT ?",
                region,
                n);
    }

    /**
     * R7: All cities in the world by population DESC.
     */
    public static void r7(Connection connection) throws SQLException {
        executeQuery(connection, CITY_SELECT + "ORDER BY city.Population DESC");
    }

    /**
     * R8: All cities in a continent by population DESC.
     */
    public static void r8(Connection connection, String continent) throws SQLException {
        executeQuery(
                connection,
                CITY_SELECT + "WHERE country.Continent = ? ORDER BY city.Population DESC",
                continent);
    }

    /**
     * R9: All cities in a region by population DESC.
     */
    public static void r9(Connection connection, String region) throws SQLException {
        executeQuery(
                connection,
                CITY_SELECT + "WHERE country.Region = ? ORDER BY city.Population DESC",
                region);
    }

    /**
     * R10: All cities in a country by population DESC.
     */
    public static void r10(Connection connection, String country) throws SQLException {
        executeQuery(
                connection,
                CITY_SELECT + "WHERE country.Name = ? ORDER BY city.Population DESC",
                country);
    }

    /**
     * R11: All cities in a district by population DESC.
     */
    public static void r11(Connection connection, String district) throws SQLException {
        executeQuery(
                connection,
                CITY_SELECT + "WHERE city.District = ? ORDER BY city.Population DESC",
                district);
    }

    /**
     * R12: Top N cities in the world.
     */
    public static void r12(Connection connection, int n) throws SQLException {
        executeQuery(connection, CITY_SELECT + "ORDER BY city.Population DESC LIMIT ?", n);
    }

    /**
     * R13: Top N cities in a continent.
     */
    public static void r13(Connection connection, String continent, int n) throws SQLException {
        executeQuery(
                connection,
                CITY_SELECT
                        + "WHERE country.Continent = ? "
                        + "ORDER BY city.Population DESC LIMIT ?",
                continent,
                n);
    }

    /**
     * R14: Top N cities in a region.
     */
    public static void r14(Connection connection, String region, int n) throws SQLException {
        executeQuery(
                connection,
                CITY_SELECT
                        + "WHERE country.Region = ? "
                        + "ORDER BY city.Population DESC LIMIT ?",
                region,
                n);
    }

    /**
     * R15: Top N cities in a country.
     */
    public static void r15(Connection connection, String country, int n) throws SQLException {
        executeQuery(
                connection,
                CITY_SELECT
                        + "WHERE country.Name = ? "
                        + "ORDER BY city.Population DESC LIMIT ?",
                country,
                n);
    }

    /**
     * R16: Top N cities in a district.
     */
    public static void r16(Connection connection, String district, int n) throws SQLException {
        executeQuery(
                connection,
                CITY_SELECT
                        + "WHERE city.District = ? "
                        + "ORDER BY city.Population DESC LIMIT ?",
                district,
                n);
    }

    /**
     * R17: All capital cities in the world by population DESC.
     */
    public static void r17(Connection connection) throws SQLException {
        executeQuery(connection, CAPITAL_SELECT + "ORDER BY city.Population DESC");
    }

    /**
     * R18: All capital cities in a continent by population DESC.
     */
    public static void r18(Connection connection, String continent) throws SQLException {
        executeQuery(
                connection,
                CAPITAL_SELECT + "WHERE country.Continent = ? ORDER BY city.Population DESC",
                continent);
    }

    /**
     * R19: All capital cities in a region by population DESC.
     */
    public static void r19(Connection connection, String region) throws SQLException {
        executeQuery(
                connection,
                CAPITAL_SELECT + "WHERE country.Region = ? ORDER BY city.Population DESC",
                region);
    }

    /**
     * R20: Top N capital cities in the world.
     */
    public static void r20(Connection connection, int n) throws SQLException {
        executeQuery(connection, CAPITAL_SELECT + "ORDER BY city.Population DESC LIMIT ?", n);
    }

    /**
     * R21: Top N capital cities in a continent.
     */
    public static void r21(Connection connection, String continent, int n) throws SQLException {
        executeQuery(
                connection,
                CAPITAL_SELECT
                        + "WHERE country.Continent = ? "
                        + "ORDER BY city.Population DESC LIMIT ?",
                continent,
                n);
    }

    /**
     * R22: Top N capital cities in a region.
     */
    public static void r22(Connection connection, String region, int n) throws SQLException {
        executeQuery(
                connection,
                CAPITAL_SELECT
                        + "WHERE country.Region = ? "
                        + "ORDER BY city.Population DESC LIMIT ?",
                region,
                n);
    }

    /**
     * R23: People / in cities / not in cities for each continent (with %).
     */
    public static void r23(Connection connection) throws SQLException {
        executeQuery(connection, populationBreakdownSql("country.Continent", "Continent"));
    }

    /**
     * R24: People / in cities / not in cities for each region (with %).
     */
    public static void r24(Connection connection) throws SQLException {
        executeQuery(connection, populationBreakdownSql("country.Region", "Region"));
    }

    /**
     * R25: People / in cities / not in cities for each country (with %).
     */
    public static void r25(Connection connection) throws SQLException {
        String sql =
                "SELECT country.Name AS Country, "
                        + "country.Population AS TotalPopulation, "
                        + "COALESCE(city_totals.CityPopulation, 0) AS CityPopulation, "
                        + "ROUND(COALESCE(city_totals.CityPopulation, 0) * 100.0 "
                        + "/ NULLIF(country.Population, 0), 2) AS CityPercentage, "
                        + "(country.Population - COALESCE(city_totals.CityPopulation, 0)) "
                        + "AS NotInCitiesPopulation, "
                        + "ROUND((country.Population - COALESCE(city_totals.CityPopulation, 0)) "
                        + "* 100.0 / NULLIF(country.Population, 0), 2) AS NotInCitiesPercentage "
                        + "FROM country "
                        + "LEFT JOIN ("
                        + "  SELECT CountryCode, SUM(Population) AS CityPopulation "
                        + "  FROM city GROUP BY CountryCode"
                        + ") city_totals ON city_totals.CountryCode = country.Code "
                        + "ORDER BY country.Population DESC";
        executeQuery(connection, sql);
    }

    /**
     * R26: Population of the world.
     */
    public static void r26(Connection connection) throws SQLException {
        executeQuery(connection, "SELECT SUM(Population) AS WorldPopulation FROM country");
    }

    /**
     * R27: Population of a continent.
     */
    public static void r27(Connection connection, String continent) throws SQLException {
        executeQuery(
                connection,
                "SELECT Continent, SUM(Population) AS Population "
                        + "FROM country WHERE Continent = ? GROUP BY Continent",
                continent);
    }

    /**
     * R28: Population of a region.
     */
    public static void r28(Connection connection, String region) throws SQLException {
        executeQuery(
                connection,
                "SELECT Region, SUM(Population) AS Population "
                        + "FROM country WHERE Region = ? GROUP BY Region",
                region);
    }

    /**
     * R29: Population of a country.
     */
    public static void r29(Connection connection, String country) throws SQLException {
        executeQuery(
                connection,
                "SELECT Name AS Country, Population FROM country WHERE Name = ?",
                country);
    }

    /**
     * R30: Population of a district.
     */
    public static void r30(Connection connection, String district) throws SQLException {
        executeQuery(
                connection,
                "SELECT District, SUM(Population) AS Population "
                        + "FROM city WHERE District = ? GROUP BY District",
                district);
    }

    /**
     * R31: Population of a city.
     */
    public static void r31(Connection connection, String city) throws SQLException {
        executeQuery(
                connection,
                "SELECT Name AS City, District, Population FROM city WHERE Name = ?",
                city);
    }

    /**
     * R32: Speakers of Chinese, English, Hindi, Spanish, Arabic
     * with percentage of world population, greatest to smallest.
     */
    public static void r32(Connection connection) throws SQLException {
        String sql =
                "SELECT countrylanguage.Language, "
                        + "ROUND(SUM(country.Population * countrylanguage.Percentage / 100)) "
                        + "AS Speakers, "
                        + "ROUND("
                        + "  SUM(country.Population * countrylanguage.Percentage / 100) * 100.0 "
                        + "  / (SELECT SUM(Population) FROM country), 2"
                        + ") AS WorldPercentage "
                        + "FROM countrylanguage "
                        + "JOIN country ON country.Code = countrylanguage.CountryCode "
                        + "WHERE countrylanguage.Language IN "
                        + "('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') "
                        + "GROUP BY countrylanguage.Language "
                        + "ORDER BY Speakers DESC";
        executeQuery(connection, sql);
    }

    private static String populationBreakdownSql(String groupExpr, String label) {
        return "SELECT " + groupExpr + " AS `" + label + "`, "
                + "SUM(country.Population) AS TotalPopulation, "
                + "COALESCE(SUM(city_totals.CityPopulation), 0) AS CityPopulation, "
                + "ROUND(COALESCE(SUM(city_totals.CityPopulation), 0) * 100.0 "
                + "/ NULLIF(SUM(country.Population), 0), 2) AS CityPercentage, "
                + "(SUM(country.Population) - COALESCE(SUM(city_totals.CityPopulation), 0)) "
                + "AS NotInCitiesPopulation, "
                + "ROUND((SUM(country.Population) - COALESCE(SUM(city_totals.CityPopulation), 0)) "
                + "* 100.0 / NULLIF(SUM(country.Population), 0), 2) AS NotInCitiesPercentage "
                + "FROM country "
                + "LEFT JOIN ("
                + "  SELECT CountryCode, SUM(Population) AS CityPopulation "
                + "  FROM city GROUP BY CountryCode"
                + ") city_totals ON city_totals.CountryCode = country.Code "
                + "GROUP BY " + groupExpr + " "
                + "ORDER BY " + groupExpr;
    }

    private static void executeQuery(Connection connection, String sql, Object... parameters)
            throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }

            ResultSet results = statement.executeQuery();

            try {

                ResultSetMetaData metadata = results.getMetaData();
                int columnCount = metadata.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metadata.getColumnLabel(i));
                    if (i < columnCount) {
                        System.out.print(" | ");
                    }
                }
                System.out.println();

                while (results.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(results.getString(i));
                        if (i < columnCount) {
                            System.out.print(" | ");
                        }
                    }
                    System.out.println();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}