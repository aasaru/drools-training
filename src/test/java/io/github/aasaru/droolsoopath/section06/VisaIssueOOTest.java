/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.section06;

import io.github.aasaru.drools.TestUtil;
import io.github.aasaru.drools.domain.Visa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class VisaIssueOOTest {

  @Test
  void testStep1_ruleExecutionOrderAffectsResult_oneOrTwoOrFourVisasAreIssued() {
    int step = 1;

    String kieSessionName = "VisaIssueStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    Collection<Visa> visasInSession = VisaIssueOO.execute(step);

    Assertions.assertThat(visasInSession).hasSizeBetween(1, 4);
    Assertions.assertThat(visasInSession)
      .map(Visa::getPassportNumber)
      .contains("AU-EMILY-3");
  }

  @Test
  void testStep2_exactlyOneVisaIsIssued() {
    int step = 2;

    Collection<Visa> visasInSession = VisaIssueOO.execute(step);

    Assertions.assertThat(visasInSession).hasSize(1);
    Assertions.assertThat(visasInSession)
      .map(Visa::getPassportNumber)
      .containsExactlyInAnyOrder("AU-EMILY-3");
  }

}
