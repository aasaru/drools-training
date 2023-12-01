/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this work.
 *  If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.section03;

import org.junit.jupiter.api.Test;

import static io.github.aasaru.drools.SystemOutTestUtil.recordLinesWrittenToSystemOut;
import static io.github.aasaru.drools.SystemOutTestUtil.getLinesWrittenToSystemOut;
import static org.assertj.core.api.Assertions.assertThat;

public class PassportValidationOOTest {

    @Test
    void testStep1() {
        recordLinesWrittenToSystemOut();
        PassportValidationOO.execute(1);

        assertThat(getLinesWrittenToSystemOut()).containsExactlyInAnyOrder(
            "Running step 1",
            "==== DROOLS START ==== ",
            "This passport is CORRECT",
            "This passport is CORRECT",
            "This passport is INVALID because it is expired or has no space for visa",
            "This passport is INVALID because it is expired or has no space for visa",
            "==== DROOLS END ==== ");
    }

    @Test
    void testStep2_executableModel_sameOutput() {
        recordLinesWrittenToSystemOut();
        PassportValidationOO.execute(2);

        assertThat(getLinesWrittenToSystemOut()).containsExactlyInAnyOrder(
            "Running step 2",
            "==== DROOLS START ==== ",
            "This passport is CORRECT",
            "This passport is CORRECT",
            "This passport is INVALID because it is expired",
            "This passport is INVALID because it has no space for visa",
            "==== DROOLS END ==== ");
    }

    @Test
    void testStep3_recordSystemOut_correctOutput() {
        recordLinesWrittenToSystemOut();
        PassportValidationOO.execute(3);

        assertThat(getLinesWrittenToSystemOut()).containsExactlyInAnyOrder(
          "Running step 3",
          "==== DROOLS START ==== ",
          "Passport[no:AU-EMILY-3, name:Emily Brown] is CORRECT. It has 20 pages free.",
          "Passport[no:AU-JAMES-4, name:James Brown] is CORRECT. It has 10 pages free.",
          "Passport[no:CA-SARAH-1, name:Sarah Murphy] is INVALID because it is expired",
          "Passport[no:CA-SIMON-2, name:Simon Murphy] is INVALID because it has no space for visa",
          "==== DROOLS END ==== ");
    }

    @Test
    void testStep4_recordSystemOut_correctOutput() {
        recordLinesWrittenToSystemOut();
        PassportValidationOO.execute(4);

        assertThat(getLinesWrittenToSystemOut()).containsExactlyInAnyOrder(
          "Running step 4",
          "==== DROOLS START ==== ",
          "Passport[no:AU-EMILY-3, name:Emily Brown] is CORRECT. It has 20 pages free.",
          "Passport[no:AU-JAMES-4, name:James Brown] is CORRECT. It has 10 pages free.",
          "Passport[no:CA-SARAH-1, name:Sarah Murphy] is INVALID because it is expired",
          "Passport[no:CA-SIMON-2, name:Simon Murphy] is INVALID because it has no space for visa",
          "==== DROOLS END ==== ",
          "==== PASSPORTS AFTER RULES WERE FIRED ==== ",
          "Passport[no:CA-SARAH-1, name:Sarah Murphy] validation FAILED",
          "Passport[no:CA-SIMON-2, name:Simon Murphy] validation FAILED",
          "Passport[no:AU-EMILY-3, name:Emily Brown] validation PASSED",
          "Passport[no:AU-JAMES-4, name:James Brown] validation PASSED"
        );
    }

    @Test
    void testStep5_recordSystemOut_correctOutput() {
        recordLinesWrittenToSystemOut();
        PassportValidationOO.execute(5);

        assertThat(getLinesWrittenToSystemOut()).containsExactlyInAnyOrder(
          "Running step 5",
          "==== DROOLS START ==== ",
          "Passport[no:CA-SARAH-1, name:Sarah Murphy] is without validation info, consider CORRECT for now",
          "Passport[no:CA-SIMON-2, name:Simon Murphy] is without validation info, consider CORRECT for now",
          "Passport[no:AU-EMILY-3, name:Emily Brown] is without validation info, consider CORRECT for now",
          "Passport[no:AU-JAMES-4, name:James Brown] is without validation info, consider CORRECT for now",
          "Passport[no:CA-SARAH-1, name:Sarah Murphy] is INVALID because it is expired",
          "Passport[no:CA-SIMON-2, name:Simon Murphy] is INVALID because it has no space for visa",
          "==== DROOLS END ==== ",
          "==== PASSPORTS AFTER RULES WERE FIRED ==== ",
          "Passport[no:CA-SARAH-1, name:Sarah Murphy] validation FAILED",
          "Passport[no:CA-SIMON-2, name:Simon Murphy] validation FAILED",
          "Passport[no:AU-EMILY-3, name:Emily Brown] validation PASSED",
          "Passport[no:AU-JAMES-4, name:James Brown] validation PASSED"
        );
    }

    @Test
    void testStep6_recordSystemOut_correctOutput() {
        recordLinesWrittenToSystemOut();
        PassportValidationOO.execute(6);

        assertThat(getLinesWrittenToSystemOut()).containsExactlyInAnyOrder(
          "Running step 6",
          "==== DROOLS START ==== ",
          "Passport[no:CA-SARAH-1, name:Sarah Murphy] is without validation info, consider CORRECT for now",
          "Passport[no:CA-SIMON-2, name:Simon Murphy] is without validation info, consider CORRECT for now",
          "Passport[no:AU-EMILY-3, name:Emily Brown] is without validation info, consider CORRECT for now",
          "Passport[no:AU-JAMES-4, name:James Brown] is without validation info, consider CORRECT for now",
          "Passport[no:CA-SARAH-1, name:Sarah Murphy] is INVALID because it is expired",
          "Passport[no:CA-SIMON-2, name:Simon Murphy] is INVALID because it has no space for visa",
          "==== DROOLS END ==== ",
          "==== PASSPORTS AFTER RULES WERE FIRED ==== ",
          "Passport[no:CA-SARAH-1, name:Sarah Murphy] validation PASSED",
          "Passport[no:CA-SIMON-2, name:Simon Murphy] validation PASSED",
          "Passport[no:AU-EMILY-3, name:Emily Brown] validation PASSED",
          "Passport[no:AU-JAMES-4, name:James Brown] validation PASSED"
        );
    }

}
