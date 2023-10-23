package io.github.aasaru.drools.section09;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class StatelessPassportValidationOoPathTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    void catchSystemOut() {
        System.setOut(new PrintStream(outContent));
    }

    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    void shouldExecuteAllSteps() {
        catchSystemOut();
        StatelessPassportValidationOoPath.execute(1);
        restoreSystemOut();

        String[] lines = outContent.toString().split("\\n");

        assertThat(lines).containsExactlyInAnyOrder(
            "Running step 1",
            "==== DROOLS SESSION START ==== ",
            "This passport is CORRECT",
            "This passport is CORRECT",
            "This passport is INVALID because it is expired or has no space for visa",
            "This passport is INVALID because it is expired or has no space for visa",
            "==== DROOLS SESSION END ==== ");

        System.out.println("outContent:" + outContent);
    }
}
