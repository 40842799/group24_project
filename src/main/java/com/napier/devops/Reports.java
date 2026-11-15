package com.napier.devops;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class Reports
{
     // private final String url = "JDBC:mysql://localhost/world";
     // private String user = "root";
     // private String password = "";

     // private String url = System.getenv("DB_URL");
     // private String user = System.getenv("DB_USER");
     // private String password = System.getenv("DB_PASSWORD");

    String url = envOr("DB_URL",
            "jdbc:mysql://localhost:3306/world?allowPublicKeyRetrieval=true&useSSL=false");
    String user = envOr("DB_USER", "world_app");
    String password = envOr("DB_PASSWORD", "world_example");

    private Connection connection;

    public Reports() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected Successfully...");
        } catch (Exception e) {
            System.out.println("Database Connection Error: " + e.getMessage());
        }
    }

    private static String envOr(String name, String fallback) {
        String value = System.getenv(name);
        return (value == null || value.isBlank()) ? fallback : value;
    }

    // Constructor for testing with injected connections
    //   public ReportPrintMy(Connection connection) {
    //this.connection = connection;
    //}

    private static final String COUNTRY_SELECT =
            "SELECT country.Code, country.Name, country.Continent, country.Region, "
                    + "country.Population, capital.Name AS Capital "
                    + "FROM country "
                    + "LEFT JOIN city AS capital ON capital.ID = country.Capital ";

    private static final String CITY_SELECT =
            "SELECT city.Name, country.Name AS Country, city.District, city.Population "
                    + "FROM city "
                    + "JOIN country ON country.Code = city.CountryCode ";

    private static final String CAPITAL_SELECT =
            "SELECT city.Name, country.Name AS Country, city.Population "
                    + "FROM country "
                    + "JOIN city ON city.ID = country.Capital ";

    /**
     * 1. Country Reports (Report-01 - Report-06) to printCountryReport()
     * filterColumn: "Continent", "Region", or null (for world database)
     */
    public void printCountryReport(String filterColumn, String filterValue, Integer limit) throws SQLException {
        StringBuilder sql = new StringBuilder(COUNTRY_SELECT);
        List<Object> params = new ArrayList<>();

        if (filterColumn != null && !filterColumn.isEmpty()) {
            sql.append("WHERE country.").append(filterColumn).append(" = ? ");
            params.add(filterValue);
        }

        sql.append("ORDER BY country.Population DESC, country.Name ASC ");

        if (limit != null && limit > 0) {
            sql.append("LIMIT ?");
            params.add(limit);
        }

        executeQuery(sql.toString(), params.toArray());
    }

    /**
     * 2. City Reports (Report-07 - Report-16) to printCityReport()
     * filterColumn: "Continent", "Region", "Name" (country name), "District", or null (world)
     */
    public void printCityReport(String filterColumn, String filterValue, Integer limit) throws SQLException {
        StringBuilder sql = new StringBuilder(CITY_SELECT);
        List<Object> params = new ArrayList<>();

        if (filterColumn != null && !filterColumn.isEmpty()) {
            if (filterColumn.equals("Continent") || filterColumn.equals("Region")) {
                sql.append("WHERE country.").append(filterColumn).append(" = ? ");
            } else if (filterColumn.equals("Country")) {
                sql.append("WHERE country.Name = ? ");
            } else if (filterColumn.equals("District")) {
                sql.append("WHERE city.District = ? ");
            }
            params.add(filterValue);
        }

        sql.append("ORDER BY city.Population DESC, city.Name ASC ");

        if (limit != null && limit > 0) {
            sql.append("LIMIT ?");
            params.add(limit);
        }

        executeQuery(sql.toString(), params.toArray());
    }

    /**
     * 3. Capital City Reports (Report-17 - Report-22) to printCapitalReport()
     * filterColumn: "Continent", "Region", or null (world)
     */
    public void printCapitalReport(String filterColumn, String filterValue, Integer limit) throws SQLException {
        StringBuilder sql = new StringBuilder(CAPITAL_SELECT);
        List<Object> params = new ArrayList<>();

        if (filterColumn != null && !filterColumn.isEmpty()) {
            sql.append("WHERE country.").append(filterColumn).append(" = ? ");
            params.add(filterValue);
        }

        sql.append("ORDER BY city.Population DESC, city.Name ASC ");

        if (limit != null && limit > 0) {
            sql.append("LIMIT ?");
            params.add(limit);
        }

        executeQuery(sql.toString(), params.toArray());
    }

    // City populations are summed per country first, so a country is never counted once per city.
    private static final String BREAKDOWN_COLUMNS =
            "SUM(cou.Population) AS PeoplePopulation, " +
            "SUM(IFNULL(c.CityPop, 0)) AS LivinginCityPopulation, " +
            "ROUND(SUM(IFNULL(c.CityPop, 0)) / SUM(cou.Population) * 100, 2) AS CityPercent, " +
            "SUM(cou.Population) - SUM(IFNULL(c.CityPop, 0)) AS NotLivingInPopulation, " +
            "ROUND((SUM(cou.Population) - SUM(IFNULL(c.CityPop, 0))) / SUM(cou.Population) * 100, 2) AS NonCityPercent ";
    private static final String BREAKDOWN_FROM =
            "FROM country cou LEFT JOIN " +
            "(SELECT CountryCode, SUM(Population) AS CityPop FROM city GROUP BY CountryCode) c " +
            "ON cou.Code = c.CountryCode ";

    /**
     * 4. Population Breakdown & Aggregate Reports (Report-23 - Report-31) to printPopulationReport()
     * reportType: "CONTINENT", "REGION", "COUNTRY_BREAKDOWN", "WORLD", "POPULATION_BY_CONTAINER"
     */
    public void printPopulationReport(String reportType, String targetValue) throws SQLException {
        String sql;
        List<Object> params = new ArrayList<>();

        switch (reportType.toUpperCase()) {
            case "CONTINENT_BREAKDOWN":
                sql = "SELECT cou.Continent AS Continent, " + BREAKDOWN_COLUMNS + BREAKDOWN_FROM + "GROUP BY cou.Continent";
                break;
            case "REGION_BREAKDOWN":
                sql = "SELECT cou.Region AS Region, " + BREAKDOWN_COLUMNS + BREAKDOWN_FROM + "GROUP BY cou.Region";
                break;
            case "COUNTRY_BREAKDOWN":
                sql = "SELECT cou.Name AS Country, " + BREAKDOWN_COLUMNS + BREAKDOWN_FROM + "GROUP BY cou.Name ORDER BY cou.Name";
                break;
            case "WORLD_POPULATION":
                sql = "SELECT SUM(Population) AS WorldPopulation FROM country";
                break;
            case "POPULATION_BY_CONTINENT":
                sql = "SELECT Continent, SUM(Population) AS Population FROM country WHERE Continent = ? GROUP BY Continent";
                params.add(targetValue);
                break;
            case "POPULATION_BY_REGION":
                sql = "SELECT Region, SUM(Population) AS Population FROM country WHERE Region = ? GROUP BY Region";
                params.add(targetValue);
                break;
            case "POPULATION_BY_COUNTRY":
                sql = "SELECT Name AS Country, Population FROM country WHERE Name = ?";
                params.add(targetValue);
                break;
            case "POPULATION_BY_DISTRICT":
                sql = "SELECT District, SUM(Population) AS Population FROM city WHERE District = ? GROUP BY District";
                params.add(targetValue);
                break;
            case "POPULATION_BY_CITY":
                sql = "SELECT city.Name AS City, country.Name AS Country, city.District, city.Population " +
                        "FROM city JOIN country ON country.Code = city.CountryCode WHERE city.Name = ? " +
                        "ORDER BY city.Population DESC, country.Name ASC";
                params.add(targetValue);
                break;
            default:
                throw new IllegalArgumentException("Invalid population report type: " + reportType);
        }

        executeQuery(sql, params.toArray());
    }

    /**
     * 5. Language Speaker Statistics Report (Replaces R32) to printLanguageReport()
     */
    public void printLanguageReport() throws SQLException {
        String sql = "SELECT cl.Language, ROUND(SUM(co.population * cl.Percentage / 100), 0) AS NoOfPeople, " +
                "ROUND(SUM(co.Population * cl.Percentage / 100) * 100.0 / (SELECT SUM(Population) FROM country), 2) AS WorldPercentage " +
                "FROM countrylanguage cl, country co WHERE cl.countrycode = co.code " +
                "GROUP BY cl.language " +
                "HAVING cl.language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "ORDER BY NoOfPeople DESC, cl.language ASC";
        executeQuery(sql);
    }

    private void executeQuery(String sql, Object... parameters) throws SQLException {
        if (connection == null) {
            throw new SQLException("Database connection is not initialized.");
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }

            try (ResultSet results = statement.executeQuery()) {
                ResultSetMetaData data = results.getMetaData();
                int columns = data.getColumnCount();

                String[] columnTitle = new String[columns];
                int[] columnSize = new int[columns];

                for (int i = 0; i < columns; i++) {
                    columnTitle[i] = data.getColumnLabel(i + 1);
                    columnSize[i] = columnTitle[i].length();
                }

                List<String[]> records = new ArrayList<>();
                while (results.next()) {
                    String[] record = new String[columns];
                    for (int i = 0; i < columns; i++) {
                        record[i] = results.getString(i + 1);
                        if (record[i] == null) {
                            record[i] = "NULL";
                        }
                        columnSize[i] = Math.max(columnSize[i], record[i].length());
                    }
                    records.add(record);
                }

                // System.out.println("\n");
                recordPrint(columnTitle, columnSize);
                displayLine(columnSize);

                for (String[] row : records) {
                    recordPrint(row, columnSize);
                }
                displayLine(columnSize);

                if (exportFile != null) {
                    writeCsv(columnTitle, records);
                }
            }
        }
    }

    private Path exportDir;
    private Path exportFile;

    public void setExportDir(Path dir) {
        this.exportDir = dir;
    }

    // Picks the CSV file for the next report; null exportDir turns exporting off
    public void startReport(String name) {
        exportFile = (exportDir == null) ? null : exportDir.resolve(name + ".csv");
    }

    private void writeCsv(String[] header, List<String[]> records) {
        List<String> lines = new ArrayList<>();
        lines.add(csvLine(header));
        for (String[] row : records) {
            lines.add(csvLine(row));
        }
        try {
            Files.createDirectories(exportFile.getParent());
            Files.write(exportFile, lines, StandardCharsets.UTF_8);
            System.out.println("Exported to " + exportFile);
        } catch (IOException e) {
            System.out.println("Could not export " + exportFile + ": " + e.getMessage());
        }
    }

    static String csvLine(String[] values) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                line.append(',');
            }
            String v = values[i] == null ? "" : values[i];
            if (v.contains(",") || v.contains("\"") || v.contains("\n")) {
                v = "\"" + v.replace("\"", "\"\"") + "\"";
            }
            line.append(v);
        }
        return line.toString();
    }

    static void recordPrint(String[] dataValues, int[] colWidth) {
        for (int i = 0; i < dataValues.length; i++) {
            if (dataValues[i] != null && !dataValues[i].isEmpty() && Character.isDigit(dataValues[i].charAt(0)))
                System.out.printf("| %" + colWidth[i] + "s ", dataValues[i]);
            else
                System.out.printf("| %-" + colWidth[i] + "s ", dataValues[i]);
        }
        System.out.println("|");
    }

    static void displayLine(int[] widths) {
        for (int width : widths) {
            System.out.print("+-");
            System.out.print("-".repeat(width));
            System.out.print("-");
        }
        System.out.println("+");
    }

}
