/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.section04;

import org.junit.jupiter.api.Test;

import static io.github.aasaru.drools.SystemOutTestUtil.getLinesWrittenToSystemOut;
import static io.github.aasaru.drools.SystemOutTestUtil.recordLinesWrittenToSystemOut;
import static org.assertj.core.api.Assertions.assertThat;

class StatefulPassportValidationOOTest {

  @Test
  void testStep1_recordSystemOut_correctOutput() {
    recordLinesWrittenToSystemOut();
    StatefulPassportValidationOO.execute(1);

    assertThat(getLinesWrittenToSystemOut()).containsExactlyInAnyOrder(
      "Running step 1",
      "==== DROOLS START ==== ",
      "Passport[no:CA-SARAH-1, name:Sarah Murphy] is INVALID because it is expired",
      "Passport[no:CA-SIMON-2, name:Simon Murphy] is INVALID because it has no space for visa",
      "Passport[no:AU-EMILY-3, name:Emily Brown] is without validation info, consider CORRECT for now",
      "Passport[no:AU-JAMES-4, name:James Brown] is without validation info, consider CORRECT for now",
      "==== DROOLS FINISHED. 4 RULES WERE FIRED ==== ",
      "==== PASSPORTS AFTER RULES WERE FIRED === ",
      "Passport[no:CA-SARAH-1, name:Sarah Murphy] verdict: FAILED, cause: ",
      "Passport[no:CA-SIMON-2, name:Simon Murphy] verdict: FAILED, cause: ",
      "Passport[no:AU-EMILY-3, name:Emily Brown] verdict: PASSED, cause: ",
      "Passport[no:AU-JAMES-4, name:James Brown] verdict: PASSED, cause: ");
  }

  @Test
  void testStep2_recordSystemOut_correctOutput() {
    recordLinesWrittenToSystemOut();
    StatefulPassportValidationOO.execute(2);

    assertThat(getLinesWrittenToSystemOut()).containsExactlyInAnyOrder(
      "Running step 2",
      "==== DROOLS START ==== ",
      "Passport[no:CA-SARAH-1, name:Sarah Murphy] is INVALID because it is expired",
      "Passport[no:CA-SIMON-2, name:Simon Murphy] is INVALID because it has no space for visa",
      "Passport[no:AU-EMILY-3, name:Emily Brown] is without validation info, consider CORRECT for now",
      "Passport[no:AU-JAMES-4, name:James Brown] is without validation info, consider CORRECT for now",
      "==== DROOLS FINISHED. 4 RULES WERE FIRED ==== ",
      "==== PASSPORTS AFTER RULES WERE FIRED === ",
      "Passport[no:CA-SARAH-1, name:Sarah Murphy] verdict: FAILED, cause: passport is expired",
      "Passport[no:CA-SIMON-2, name:Simon Murphy] verdict: FAILED, cause: passport has no space for visa",
      "Passport[no:AU-EMILY-3, name:Emily Brown] verdict: PASSED, cause: ",
      "Passport[no:AU-JAMES-4, name:James Brown] verdict: PASSED, cause: ");
  }
}
