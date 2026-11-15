package com.napier.devops;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the console formatting helpers in {@link Reports}.
 * These need no database connection.
 */
public class ReportsTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    @BeforeEach
    void captureOutput() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void restoreOutput() {
        System.setOut(originalOut);
    }

    @NonNull
    String output() {
        return out.toString().replace("\r\n", "\n");
    }

    @Test
    void lineHasBorderForEachColumn() {
        Reports.displayLine(new int[]{3, 5});
        assertEquals("+-----+-------+\n", output());
    }

    @Test
    void textIsLeftAligned() {
        Reports.recordPrint(new String[]{"Paris"}, new int[]{8});
        assertEquals("| Paris    |\n", output());
    }

    @Test
    void numbersAreRightAligned() {
        Reports.recordPrint(new String[]{"42"}, new int[]{5});
        assertEquals("|    42 |\n", output());
    }

    @Test
    void textAndNumbersTogether() {
        Reports.recordPrint(new String[]{"Oslo", "700000"}, new int[]{6, 8});
        assertEquals("| Oslo   |   700000 |\n", output());
    }

    @Test
    void nullValueTest() {
        Reports.recordPrint(new String[]{null}, new int[]{4});
        assertEquals("| null |\n", output());
    }

    @Test
    void emptyStringTest() {
        Reports.recordPrint(new String[]{""}, new int[]{3});
        assertEquals("|     |\n", output());
    }

    @Test
    void noValuesPassed() {
        Reports.recordPrint(new String[0], new int[0]);
        assertEquals("|\n", output());
    }

    @Test
    void csvPlainValues() {
        assertEquals("Paris,France,2138551", Reports.csvLine(new String[]{"Paris", "France", "2138551"}));
    }

    @Test
    void csvQuotesCommasAndQuotes() {
        assertEquals("\"Washington, D.C.\",\"say \"\"hi\"\"\"",
                Reports.csvLine(new String[]{"Washington, D.C.", "say \"hi\""}));
    }

    @Test
    void csvNullBecomesEmpty() {
        assertEquals("a,,b", Reports.csvLine(new String[]{"a", null, "b"}));
    }
}
