
package com.napier.devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SettingsValidationTest {

    @Test
    void emptyCountryValueIsRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                PopulationReportingApp.Settings.parse(
                        new String[]{"--country="}
                )
        );
    }

    @Test
    void unknownOptionIsRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                PopulationReportingApp.Settings.parse(
                        new String[]{"--unknown=test"}
                )
        );
    }

    @Test
    void negativeNumberIsRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                PopulationReportingApp.Settings.parse(
                        new String[]{"--n=-5"}
                )
        );
    }
}
