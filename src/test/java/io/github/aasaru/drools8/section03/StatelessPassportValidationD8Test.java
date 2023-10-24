package io.github.aasaru.drools8.section03;

import org.junit.jupiter.api.Test;

import static io.github.aasaru.drools.SystemOutTestUtil.recordLinesWrittenToSystemOut;
import static io.github.aasaru.drools.SystemOutTestUtil.getLinesWrittenToSystemOut;
import static org.assertj.core.api.Assertions.assertThat;

public class StatelessPassportValidationD8Test {

    @Test
    void testStep1() {
        recordLinesWrittenToSystemOut();
        StatelessPassportValidationD8.execute(1);

        assertThat(getLinesWrittenToSystemOut()).containsExactlyInAnyOrder(
            "Running step 1",
            "==== DROOLS SESSION START ==== ",
            "This passport is CORRECT",
            "This passport is CORRECT",
            "This passport is INVALID because it is expired or has no space for visa",
            "This passport is INVALID because it is expired or has no space for visa",
            "==== DROOLS SESSION END ==== ");
    }

    @Test
    void testStep2_executableModel_sameOutput() {
        recordLinesWrittenToSystemOut();
        StatelessPassportValidationD8.execute(2);

        assertThat(getLinesWrittenToSystemOut()).containsExactlyInAnyOrder(
            "Running step 2",
            "==== DROOLS SESSION START ==== ",
            "This passport is CORRECT",
            "This passport is CORRECT",
            "This passport is INVALID because it is expired",
            "This passport is INVALID because it has no space for visa",
            "==== DROOLS SESSION END ==== ");
    }
}
