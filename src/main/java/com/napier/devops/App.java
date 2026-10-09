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
        if (wantsHelp(args)) {
            System.out.println(Settings.USAGE);
            return;
        }
        Settings settings;
        try {
            settings = Settings.parse(args);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println(Settings.USAGE);
            System.exit(2);
            return;
        }

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            throw new IllegalStateException(
                    "Set DB_URL, DB_USER and DB_PASSWORD before running.");
        }

        // Report inputs. With no arguments these are the fixed demonstration values used in CI,
        // so GitHub Actions and docker compose run unattended. Options override them.
        final String demoContinent = settings.continent;
        final String demoRegion = settings.region;
        final String demoCountry = settings.country;
        final String demoDistrict = settings.district;
        final String demoCity = settings.city;
        final int demoN = settings.n;

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

            run("R01: All the countries in the world organised by largest population to smallest.",
                    () -> r1(connection), scanner, isInteractive);

            run("R02: All the countries in the continent " + demoContinent + " organised by largest population to smallest.",
                    () -> r2(connection, demoContinent), scanner, isInteractive);

            run("R03: All the countries in the region " + demoRegion + " organised by largest population to smallest.",
                    () -> r3(connection, demoRegion), scanner, isInteractive);

            run("R04: The top " + demoN + " populated countries in the world.",
                    () -> r4(connection, demoN), scanner, isInteractive);

            run("R05: The top " + demoN + " populated countries in the continent " + demoContinent + ".",
                    () -> r5(connection, demoContinent, demoN), scanner, isInteractive);

            run("R06: The top " + demoN + " populated countries in the region " + demoRegion + ".",
                    () -> r6(connection, demoRegion, demoN), scanner, isInteractive);

            run("R07: All the cities in the world organised by largest population to smallest.",
                    () -> r7(connection), scanner, isInteractive);

            run("R08: All the cities in the continent " + demoContinent + " organised by largest population to smallest.",
                    () -> r8(connection, demoContinent), scanner, isInteractive);

            run("R09: All the cities in the region " + demoRegion + " organised by largest population to smallest.",
                    () -> r9(connection, demoRegion), scanner, isInteractive);

            run("R10: All the cities in the country " + demoCountry + " organised by largest population to smallest.",
                    () -> r10(connection, demoCountry), scanner, isInteractive);

            run("R11: All the cities in the district " + demoDistrict + " organised by largest population to smallest.",
                    () -> r11(connection, demoDistrict), scanner, isInteractive);

            run("R12: The top " + demoN + " populated cities in the world.",
                    () -> r12(connection, demoN), scanner, isInteractive);

            run("R13: The top " + demoN + " populated cities in the continent " + demoContinent + ".",
                    () -> r13(connection, demoContinent, demoN), scanner, isInteractive);

            run("R14: The top " + demoN + " populated cities in the region " + demoRegion + ".",
                    () -> r14(connection, demoRegion, demoN), scanner, isInteractive);

            run("R15: The top " + demoN + " populated cities in the country " + demoCountry + ".",
                    () -> r15(connection, demoCountry, demoN), scanner, isInteractive);

            run("R16: The top " + demoN + " populated cities in the district " + demoDistrict + ".",
                    () -> r16(connection, demoDistrict, demoN), scanner, isInteractive);

            run("R17: All the capital cities in the world organised by largest population to smallest.",
                    () -> r17(connection), scanner, isInteractive);

            run("R18: All the capital cities in the continent " + demoContinent + " organised by largest population to smallest.",
                    () -> r18(connection, demoContinent), scanner, isInteractive);

            run("R19: All the capital cities in the region " + demoRegion + " organised by largest to smallest.",
                    () -> r19(connection, demoRegion), scanner, isInteractive);

            run("R20: The top " + demoN + " populated capital cities in the world.",
                    () -> r20(connection, demoN), scanner, isInteractive);

            run("R21: The top " + demoN + " populated capital cities in the continent " + demoContinent + ".",
                    () -> r21(connection, demoContinent, demoN), scanner, isInteractive);

            run("R22: The top " + demoN + " populated capital cities in the region " + demoRegion + ".",
                    () -> r22(connection, demoRegion, demoN), scanner, isInteractive);

            run("R23: Population of people, living in cities, and not living in cities in each continent.",
                    () -> r23(connection), scanner, isInteractive);

            run("R24: Population of people, living in cities, and not living in cities in each region.",
                    () -> r24(connection), scanner, isInteractive);

            run("R25: Population of people, living in cities, and not living in cities in each country.",
                    () -> r25(connection), scanner, isInteractive);

            run("R26: The population of the world.",
                    () -> r26(connection), scanner, isInteractive);

            run("R27: The population of the continent " + demoContinent + ".",
                    () -> r27(connection, demoContinent), scanner, isInteractive);

            run("R28: The population of the region " + demoRegion + ".",
                    () -> r28(connection, demoRegion), scanner, isInteractive);

            run("R29: The population of the country " + demoCountry + ".",
                    () -> r29(connection, demoCountry), scanner, isInteractive);

            run("R30: The population of the district " + demoDistrict + ".",
                    () -> r30(connection, demoDistrict), scanner, isInteractive);

            run("R31: The population of the city " + demoCity + ".",
                    () -> r31(connection, demoCity), scanner, isInteractive);

            run("R32: Number of people who speak Chinese, English, Hindi, Spanish, Arabic.",
                    () -> r32(connection), scanner, isInteractive);
        }
    }

    private static boolean wantsHelp(String[] args) {
        for (String arg : args) {
            if (arg.equals("--help") || arg.equals("-h")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Report inputs: the fixed demonstration values by default, optionally overridden by
     * {@code --name=value} arguments. Nothing is prompted for, so unattended runs never wait.
     */
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
                        + "  --help              show this message\n"
                        + "With no options the defaults are used, so the program runs unattended.";

        final int n;
        final String continent;
        final String region;
        final String country;
        final String district;
        final String city;

        private Settings(int n, String continent, String region, String country, String district, String city) {
            this.n = n;
            this.continent = continent;
            this.region = region;
            this.country = country;
            this.district = district;
            this.city = city;
        }

        /** Reads {@code --name=value} arguments; throws IllegalArgumentException for anything invalid. */
        static Settings parse(String[] args) {
            int n = DEFAULT_N;
            String continent = DEFAULT_CONTINENT;
            String region = DEFAULT_REGION;
            String country = DEFAULT_COUNTRY;
            String district = DEFAULT_DISTRICT;
            String city = DEFAULT_CITY;

            for (String arg : args) {
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
                    default:
                        throw new IllegalArgumentException("Unknown option: --" + key);
                }
            }
            return new Settings(n, continent, region, country, district, city);
        }

        private static int parsePositive(String value) {
            try {
                int number = Integer.parseInt(value);
                if (number > 0) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // falls through to the error below
            }
            throw new IllegalArgumentException("N must be a positive whole number, got '" + value + "'");
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
