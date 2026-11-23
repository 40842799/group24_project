package com.napier.devops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/*
runApp:
  -- runs the app and catches everything it prints, so the tests can read it.
allReportsPrint:
  -- runs the app and checks that no database error appears
     and that the first and last reports (Report-1 and Report-32) printed.
csvFilesAreSaved:
  -- runs the app with --export pointing at a temporary folder,
     then checks that the CSV files for Report-1 and Report-32 exist and the first isn't empty.
*/

// Requires a running "world" database (see DB_URL / DB_USER / DB_PASSWORD)
public class PopulationReportingAppIT {

    private String runApp(String... args) throws Exception {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream text = new ByteArrayOutputStream();
        System.setOut(new PrintStream(text, true));
        try {
            PopulationReportingApp.main(args);
        } finally {
            System.setOut(oldOut);
        }
        return text.toString();
    }

    @Test
    void allReportsPrint() throws Exception {
        String output = runApp();
        assertFalse(output.contains("Database Connection Error"), output);
        assertTrue(output.contains("Report-1:"));
        assertTrue(output.contains("Report-32:"));
    }

    @Test
    void csvFilesAreSaved(@TempDir Path dir) throws Exception {
        runApp("--export=" + dir);
        Path report1 = dir.resolve("Report-1.csv");
        Path report32 = dir.resolve("Report-32.csv");
        assertTrue(Files.exists(report1), "Report-1.csv missing");
        assertTrue(Files.exists(report32), "Report-32.csv missing");
        assertTrue(Files.size(report1) > 0);
    }
}
