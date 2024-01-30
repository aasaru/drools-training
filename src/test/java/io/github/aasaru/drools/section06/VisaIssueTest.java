/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.section06;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.TestUtil;
import io.github.aasaru.drools.domain.Visa;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.aasaru.drools.section08.FamilyVisaApplicationValidationTest.emulateInputFromKeyboard;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

class VisaIssueTest {

  @Test
  void shouldExecuteAllSteps() {
    VisaIssue.execute(1);
    VisaIssue.execute(2);
    VisaIssue.execute(3);
    VisaIssue.execute(4);

    emulateInputFromKeyboard("yes");
    VisaIssue.execute(5);

    VisaIssue.execute(6);
  }

  @Test
  void testStep1_ruleExecutionOrderAffectsResult_oneOrTwoOrFourVisasAreIssued() {
    int step = 1;

    String kieSessionName = "VisaIssueStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    Collection<Visa> visasInSession = VisaIssue.execute(step);

    Assertions.assertThat(visasInSession).hasSizeBetween(1, 4);
    Assertions.assertThat(visasInSession)
      .map(Visa::getPassportNumber)
      .contains("AU-EMILY-3");
  }

  @Test
  void testStep2_exactlyOneVisaIsIssued() {
    int step = 2;

    Collection<Visa> visasInSession = VisaIssue.execute(step);

    Assertions.assertThat(visasInSession).hasSize(1);
    Assertions.assertThat(visasInSession)
      .map(Visa::getPassportNumber)
      .containsExactlyInAnyOrder("AU-EMILY-3");
  }

  @Test
  void testStep3_exactlyOneVisaIsIssued() {
    int step = 3;

    Collection<Visa> visasInSession = VisaIssue.execute(step);

    Assertions.assertThat(visasInSession).hasSize(1);
    Assertions.assertThat(visasInSession)
      .map(Visa::getPassportNumber)
      .containsExactlyInAnyOrder("AU-EMILY-3");
  }

  @Test
  void testStep4() {
    int step = 4;

    Common.disposeSession = false;
    VisaIssue.execute(step);

    KieSession ksession = TestUtil.getKieSession("VisaIssueStep", step);

    List<Visa> visasInSession = new ArrayList<>();

    TestUtil.addObjectsOfType(ksession, visasInSession, Visa.class);

    assertThat(visasInSession.size(), CoreMatchers.is(equalTo(1)));
    assertThat(visasInSession.get(0).getPassportNumber(), CoreMatchers.is(equalTo("AU-EMILY-3")));

    ksession.dispose();
  }

  @Test
  void testStep5_noAnswer() {
    int step = 5;
    String kieSessionName = "VisaIssueStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    Common.disposeSession = false;

    emulateInputFromKeyboard("no");
    VisaIssue.execute(step);

    KieSession ksession = TestUtil.getKieSession(kieSessionName);

    List<Visa> visasInSession = new ArrayList<>();

    TestUtil.addObjectsOfType(ksession, visasInSession, Visa.class);

    assertThat(visasInSession.size(), CoreMatchers.is(equalTo(2)));
    assertThat(visasInSession.stream().map(Visa::getPassportNumber).collect(Collectors.toList()),
            containsInAnyOrder("AU-EMILY-3", "CA-SIMON-2"));

    ksession.dispose();
  }

  @Test
  void testStep5_yesAnswer() {
    int step = 5;
    String kieSessionName = "VisaIssueStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    Common.disposeSession = false;

    emulateInputFromKeyboard("yes");
    VisaIssue.execute(step);

    KieSession ksession = TestUtil.getKieSession(kieSessionName);

    List<Visa> visasInSession = new ArrayList<>();
    TestUtil.addObjectsOfType(ksession, visasInSession, Visa.class);

    assertThat(visasInSession.size(), CoreMatchers.is(equalTo(0)));

    ksession.dispose();
  }

  @Test
  void testStep6() {
    int step = 6;

    Common.disposeSession = false;
    VisaIssue.execute(step);

    KieSession ksession = TestUtil.getKieSession("VisaIssueStep", step);

    List<Visa> visasInSession = new ArrayList<>();

    TestUtil.addObjectsOfType(ksession, visasInSession, Visa.class);

    assertThat(visasInSession.size(), CoreMatchers.is(equalTo(1)));
    assertThat(visasInSession.get(0).getPassportNumber(), CoreMatchers.is(equalTo("AU-EMILY-3")));

    ksession.dispose();
  }

}
