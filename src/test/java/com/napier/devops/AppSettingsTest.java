package com.napier.devops;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppSettingsTest {

    @Test
    void validTopNIsAccepted() {
        PopulationReportingApp.Settings settings =
                PopulationReportingApp.Settings.parse(new String[]{"--n=5"});

        assertEquals(5, settings.n);
    }

    @Test
    void zeroTopNIsRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                PopulationReportingApp.Settings.parse(new String[]{"--n=0"})
        );
    }

    @Test
    void negativeTopNIsRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                PopulationReportingApp.Settings.parse(new String[]{"--n=-5"})
        );
    }

    @Test
    void nonNumericTopNIsRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                PopulationReportingApp.Settings.parse(new String[]{"--n=abc"})
        );
    }

    @Test
    void missingTopNUsesDefault() {
        PopulationReportingApp.Settings settings =
                PopulationReportingApp.Settings.parse(new String[]{});

        assertEquals(5, settings.n);
    }
}
