package com.napier.devops;

import java.sql.SQLException;

public class PopulationReportingApp
{
    public static void main(String[] args) throws SQLException {
        Settings settings;
        try {
            settings = Settings.parse(args);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println(Settings.USAGE);
            System.exit(2);
            return;
        }

        //String url = "JDBC:mysql://localhost/world";
        //String user = "root";
        //String password = "";

        final String continent = settings.continent;
        final String region = settings.region;
        final String country = settings.country;
        final String district = settings.district;
        final String city = settings.city;
        final int n = settings.n;

        // Connection connection = DriverManager.getConnection(url, user, password)

        try {
            // Instantiate PrintReport with the active connection
            Reports report = new Reports();
            activeReports = report;
            if (settings.exportDir != null) {
                report.setExportDir(java.nio.file.Paths.get(settings.exportDir));
            }

            run("Report-1: All the countries in the world organised by largest population to smallest.",
                    () -> report.printCountryReport(null, null, null));

            run("Report-2: All the countries in the continent " + continent + " organised by largest population to smallest.",
                    () -> report.printCountryReport("Continent", continent, null));

            run("Report-3: All the countries in the region " + region + " organised by largest population to smallest.",
                    () -> report.printCountryReport("Region", region, null));

            run("Report-4: The top " + n + " populated countries in the world.",
                    () -> report.printCountryReport(null, null, n));

            run("Report-5: The top " + n + " populated countries in the continent " + continent + ".",
                    () -> report.printCountryReport("Continent", continent, n));

            run("Report-6: The top " + n + " populated countries in the region " + region + ".",
                    () -> report.printCountryReport("Region", region, n));

            run("Report-7: All the cities in the world organised by largest population to smallest.",
                    () -> report.printCityReport(null, null, null));

            run("Report-8: All the cities in the continent " + continent + " organised by largest population to smallest.",
                    () -> report.printCityReport("Continent", continent, null));

            run("Report-9: All the cities in the region " + region + " organised by largest population to smallest.",
                    () -> report.printCityReport("Region", region, null));

            run("Report-10: All the cities in the country " + country + " organised by largest population to smallest.",
                    () -> report.printCityReport("Country", country, null));

            run("Report-11: All the cities in the district " + district + " organised by largest population to smallest.",
                    () -> report.printCityReport("District", district, null));

            run("Report-12: The top " + n + " populated cities in the world.",
                    () -> report.printCityReport(null, null, n));

            run("Report-13: The top " + n + " populated cities in the continent " + continent + ".",
                    () -> report.printCityReport("Continent", continent, n));

            run("Report-14: The top " + n + " populated cities in the region " + region + ".",
                    () -> report.printCityReport("Region", region, n));

            run("Report-15: The top " + n + " populated cities in the country " + country + ".",
                    () -> report.printCityReport("Country", country, n));

            run("Report-16: The top " + n + " populated cities in the district " + district + ".",
                    () -> report.printCityReport("District", district, n));

            run("Report-17: All the capital cities in the world organised by largest population to smallest.",
                    () -> report.printCapitalReport(null, null, null));

            run("Report-18: All the capital cities in the continent " + continent + " organised by largest population to smallest.",
                    () -> report.printCapitalReport("Continent", continent, null));

            run("Report-19: All the capital cities in the region " + region + " organised by largest to smallest.",
                    () -> report.printCapitalReport("Region", region, null));

            run("Report-20: The top " + n + " populated capital cities in the world.",
                    () -> report.printCapitalReport(null, null, n));

            run("Report-21: The top " + n + " populated capital cities in the continent " + continent + ".",
                    () -> report.printCapitalReport("Continent", continent, n));

            run("Report-22: The top " + n + " populated capital cities in the region " + region + ".",
                    () -> report.printCapitalReport("Region", region, n));

            run("Report-23: Population of people, living in cities, and not living in cities in each continent.",
                    () -> report.printPopulationReport("CONTINENT_BREAKDOWN", null));

            run("Report-24: Population of people, living in cities, and not living in cities in each region.",
                    () -> report.printPopulationReport("REGION_BREAKDOWN", null));

            run("Report-25: Population of people, living in cities, and not living in cities in each country.",
                    () -> report.printPopulationReport("COUNTRY_BREAKDOWN", null));

            run("Report-26: The population of the world.",
                    () -> report.printPopulationReport("WORLD_POPULATION", null));

            run("Report-27: The population of the continent " + continent + ".",
                    () -> report.printPopulationReport("POPULATION_BY_CONTINENT", continent));

            run("Report-28: The population of the region " + region + ".",
                    () -> report.printPopulationReport("POPULATION_BY_REGION", region));

            run("Report-29: The population of the country " + country + ".",
                    () -> report.printPopulationReport("POPULATION_BY_COUNTRY", country));

            run("Report-30: The population of the district " + district + ".",
                    () -> report.printPopulationReport("POPULATION_BY_DISTRICT", district));

            run("Report-31: The population of the city " + city + ".",
                    () -> report.printPopulationReport("POPULATION_BY_CITY", city));

            run("Report-32: Number of people who speak Chinese, English, Hindi, Spanish, Arabic.",
                    () -> report.printLanguageReport());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    private static Reports activeReports;

    private static void run(String title, ReportAction action) throws SQLException {
        System.out.println("=====================================");
        System.out.println(title);
        System.out.println("=====================================");
        int colon = title.indexOf(':');
        if (activeReports != null && colon > 0) {
            activeReports.startReport(title.substring(0, colon));
        }
        action.run();
        System.out.println();
    }

    @FunctionalInterface
    private interface ReportAction {
        void run() throws SQLException;
    }

    static final class Settings {
        static final int DEFAULT_N = 5;
        static final String DEFAULT_CONTINENT = "Europe";
        static final String DEFAULT_REGION = "Western Europe";
        static final String DEFAULT_COUNTRY = "United Kingdom";
        static final String DEFAULT_DISTRICT = "England";
        static final String DEFAULT_CITY = "Edinburgh";

        static final String USAGE =
                "Usage: java -jar devopsApp.jar [options]\n"
                        + "  --n=<number>        N for the Top-N reports (default " + DEFAULT_N + ")\n"
                        + "  --continent=<name>  continent for the continent reports (default " + DEFAULT_CONTINENT + ")\n"
                        + "  --region=<name>     region for the region reports (default " + DEFAULT_REGION + ")\n"
                        + "  --country=<name>    country for the country reports (default " + DEFAULT_COUNTRY + ")\n"
                        + "  --district=<name>   district for the district reports (default " + DEFAULT_DISTRICT + ")\n"
                        + "  --city=<name>       city for the city population report (default " + DEFAULT_CITY + ")\n"
                        + "  --export=<folder>   also save each report as a CSV file in this folder\n"
                        + "  --help              show this message\n"
                        + "With no options the defaults are used, so the program runs unattended.";

        final int n;
        final String continent;
        final String region;
        final String country;
        final String district;
        final String city;
        final String exportDir;

        private Settings(int n, String continent, String region, String country, String district, String city,
                         String exportDir) {
            this.exportDir = exportDir;
            this.n = n;
            this.continent = continent;
            this.region = region;
            this.country = country;
            this.district = district;
            this.city = city;
        }

        static Settings parse(String[] args) {
            int n = DEFAULT_N;
            String continent = DEFAULT_CONTINENT;
            String region = DEFAULT_REGION;
            String country = DEFAULT_COUNTRY;
            String district = DEFAULT_DISTRICT;
            String city = DEFAULT_CITY;
            String exportDir = null;

            for (String arg : args) {
                if (arg.equals("--help") || arg.equals("-h")) {
                    System.out.println(USAGE);
                    System.exit(0);
                }
                int equals = arg.indexOf('=');
                if (!arg.startsWith("--") || equals < 0) {
                    throw new IllegalArgumentException("Unrecognised argument: " + arg);
                }
                String key = arg.substring(2, equals);
                String value = arg.substring(equals + 1).trim();
                if (value.isEmpty()) {
                    throw new IllegalArgumentException("Option --" + key + " needs a value");
                }
                switch (key) {
                    case "n":
                        n = parsePositive(value);
                        break;
                    case "continent":
                        continent = value;
                        break;
                    case "region":
                        region = value;
                        break;
                    case "country":
                        country = value;
                        break;
                    case "district":
                        district = value;
                        break;
                    case "city":
                        city = value;
                        break;
                    case "export":
                        exportDir = value;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown option: --" + key);
                }
            }
            return new Settings(n, continent, region, country, district, city, exportDir);
        }

        private static int parsePositive(String value) {
            try {
                int number = Integer.parseInt(value);
                if (number > 0) {
                    return number;
                }
            } catch (NumberFormatException ignored) {}
            throw new IllegalArgumentException("N must be a positive whole number, got '" + value + "'");
        }
    }

}

