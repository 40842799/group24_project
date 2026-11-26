package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Requires a running "world" database (see DB_URL / DB_USER / DB_PASSWORD)
public class ReportsIT {

    static Reports reports;

    @BeforeAll
    static void init() {
        reports = new Reports();
    }

    @Test
    void printCountryReportWorld() {
        assertDoesNotThrow(() -> reports.printCountryReport(null, null, null));
    }

    @Test
    void printCountryReportTopNByContinent() {
        assertDoesNotThrow(() -> reports.printCountryReport("Continent", "Europe", 5));
    }

    @Test
    void printCityReportWorld() {
        assertDoesNotThrow(() -> reports.printCityReport(null, null, null));
    }

    @Test
    void printCapitalReportByRegion() {
        assertDoesNotThrow(() -> reports.printCapitalReport("Region", "Western Europe", null));
    }

    @Test
    void printPopulationReportWorld() {
        assertDoesNotThrow(() -> reports.printPopulationReport("WORLD_POPULATION", null));
    }

    @Test
    void printLanguageReport() {
        assertDoesNotThrow(() -> reports.printLanguageReport());
    }

    // Country reports
    @Test
    void printCountryReportByContinent() {
        assertDoesNotThrow(() -> reports.printCountryReport("Continent", "Europe", null));
    }

    @Test
    void printCountryReportByRegion() {
        assertDoesNotThrow(() -> reports.printCountryReport("Region", "Western Europe", null));
    }

    @Test
    void printCountryReportTopNWorld() {
        assertDoesNotThrow(() -> reports.printCountryReport(null, null, 5));
    }

    @Test
    void printCountryReportTopNByRegion() {
        assertDoesNotThrow(() -> reports.printCountryReport("Region", "Western Europe", 5));
    }

    // City reports
    @Test
    void printCityReportByContinent() {
        assertDoesNotThrow(() -> reports.printCityReport("Continent", "Europe", null));
    }

    @Test
    void printCityReportByRegion() {
        assertDoesNotThrow(() -> reports.printCityReport("Region", "Western Europe", null));
    }

    @Test
    void printCityReportByCountry() {
        assertDoesNotThrow(() -> reports.printCityReport("Country", "United Kingdom", null));
    }

    @Test
    void printCityReportByDistrict() {
        assertDoesNotThrow(() -> reports.printCityReport("District", "England", null));
    }

    @Test
    void printCityReportTopNWorld() {
        assertDoesNotThrow(() -> reports.printCityReport(null, null, 5));
    }

    @Test
    void printCityReportTopNByContinent() {
        assertDoesNotThrow(() -> reports.printCityReport("Continent", "Europe", 5));
    }

    @Test
    void printCityReportTopNByRegion() {
        assertDoesNotThrow(() -> reports.printCityReport("Region", "Western Europe", 5));
    }

    @Test
    void printCityReportTopNByCountry() {
        assertDoesNotThrow(() -> reports.printCityReport("Country", "United Kingdom", 5));
    }

    @Test
    void printCityReportTopNByDistrict() {
        assertDoesNotThrow(() -> reports.printCityReport("District", "England", 5));
    }

    // Capital reports
    @Test
    void printCapitalReportWorld() {
        assertDoesNotThrow(() -> reports.printCapitalReport(null, null, null));
    }

    @Test
    void printCapitalReportByContinent() {
        assertDoesNotThrow(() -> reports.printCapitalReport("Continent", "Europe", null));
    }

    @Test
    void printCapitalReportTopNWorld() {
        assertDoesNotThrow(() -> reports.printCapitalReport(null, null, 5));
    }

    @Test
    void printCapitalReportTopNByContinent() {
        assertDoesNotThrow(() -> reports.printCapitalReport("Continent", "Europe", 5));
    }

    @Test
    void printCapitalReportTopNByRegion() {
        assertDoesNotThrow(() -> reports.printCapitalReport("Region", "Western Europe", 5));
    }

    // Population reports
    @Test
    void populationContinentBreakdown() {
        assertDoesNotThrow(() -> reports.printPopulationReport("CONTINENT_BREAKDOWN", null));
    }

    @Test
    void populationRegionBreakdown() {
        assertDoesNotThrow(() -> reports.printPopulationReport("REGION_BREAKDOWN", null));
    }

    @Test
    void populationCountryBreakdown() {
        assertDoesNotThrow(() -> reports.printPopulationReport("COUNTRY_BREAKDOWN", null));
    }

    @Test
    void populationByContinent() {
        assertDoesNotThrow(() -> reports.printPopulationReport("POPULATION_BY_CONTINENT", "Europe"));
    }

    @Test
    void populationByRegion() {
        assertDoesNotThrow(() -> reports.printPopulationReport("POPULATION_BY_REGION", "Western Europe"));
    }

    @Test
    void populationByCountry() {
        assertDoesNotThrow(() -> reports.printPopulationReport("POPULATION_BY_COUNTRY", "United Kingdom"));
    }

    @Test
    void populationByDistrict() {
        assertDoesNotThrow(() -> reports.printPopulationReport("POPULATION_BY_DISTRICT", "England"));
    }

    @Test
    void populationByCity() {
        assertDoesNotThrow(() -> reports.printPopulationReport("POPULATION_BY_CITY", "Edinburgh"));
    }

    @Test
    void populationTypeIsCaseInsensitive() {
        assertDoesNotThrow(() -> reports.printPopulationReport("world_population", null));
    }

    @Test
    void populationInvalidTypeThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> reports.printPopulationReport("TEST", null));
    }

    // Data checks: each report is exported to a CSV file and the rows are checked
    interface ReportCall {
        void run(Reports r) throws Exception;
    }

    // Runs one report and returns its CSV lines (first line is the header)
    private static List<String> runToCsv(Path dir, ReportCall call) throws Exception {
        Reports r = new Reports();
        r.setExportDir(dir);
        r.startReport("out");
        call.run(r);
        return Files.readAllLines(dir.resolve("out.csv"));
    }

    @Test
    void allCountriesAreListed(@TempDir Path dir) throws Exception {
        List<String> lines = runToCsv(dir, r -> r.printCountryReport(null, null, null));
        assertEquals(239, lines.size() - 1);
    }

    @Test
    void allCitiesAreListed(@TempDir Path dir) throws Exception {
        List<String> lines = runToCsv(dir, r -> r.printCityReport(null, null, null));
        assertEquals(4079, lines.size() - 1);
    }

    @Test
    void europeHas46Countries(@TempDir Path dir) throws Exception {
        List<String> lines = runToCsv(dir, r -> r.printCountryReport("Continent", "Europe", null));
        assertEquals(46, lines.size() - 1);
    }

    @Test
    void topFiveCountriesStartWithChina(@TempDir Path dir) throws Exception {
        List<String> lines = runToCsv(dir, r -> r.printCountryReport(null, null, 5));
        assertEquals(5, lines.size() - 1);
        assertTrue(lines.get(1).contains("China"));
    }

    @Test
    void worldPopulationValue(@TempDir Path dir) throws Exception {
        List<String> lines = runToCsv(dir, r -> r.printPopulationReport("WORLD_POPULATION", null));
        assertEquals("6078749450", lines.get(1));
    }

    @Test
    void languageReportHasFiveLanguages(@TempDir Path dir) throws Exception {
        List<String> lines = runToCsv(dir, r -> r.printLanguageReport());
        assertEquals(5, lines.size() - 1);
    }


    @Test
    void csvExportFailureThrowsException(@TempDir Path dir) throws Exception {
        Path invalidLocation = dir.resolve("not-a-directory");
        Files.writeString(invalidLocation, "This is a file, not a folder");

        Reports report = new Reports();
        report.setExportDir(invalidLocation);
        report.startReport("CSV Export Bug Test");

        assertThrows(IllegalStateException.class,
                () -> report.printLanguageReport());
    }
  }
