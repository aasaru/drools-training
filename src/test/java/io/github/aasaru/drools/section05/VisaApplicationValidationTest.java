/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.section05;

import org.junit.jupiter.api.Test;

class VisaApplicationValidationTest {

  @Test
  void shouldExecuteAllSteps() {
    VisaApplicationValidation.execute(1);
    VisaApplicationValidation.execute(2);
    VisaApplicationValidation.execute(3);
  }
}
