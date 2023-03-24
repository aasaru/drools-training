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
import org.drools.core.impl.StatefulKnowledgeSessionImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
  void testStep1_testItDoesntFail() {
    int step = 1;

    String kieSessionName = "VisaIssueStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    VisaIssue.execute(step);
  }

  @Test
  void testStep2() {
    int step = 2;

    Common.disposeSession = false;
    VisaIssue.execute(step);

    KieSession ksession = TestUtil.getKieSession("VisaIssueStep", step);

    List<Visa> visasInSession = new ArrayList<>();

    TestUtil.addObjectsOfType(ksession, visasInSession, Visa.class);

    assertThat(visasInSession.size(), CoreMatchers.is(equalTo(1)));
    assertThat(visasInSession.get(0).getPassportNumber(), CoreMatchers.is(equalTo("AU-EMILY-3")));
  }

  @Test
  void testStep3() {
    int step = 3;

    Common.disposeSession = false;
    VisaIssue.execute(step);

    KieSession ksession = TestUtil.getKieSession("VisaIssueStep", step);

    List<Visa> visasInSession = new ArrayList<>();

    TestUtil.addObjectsOfType(ksession, visasInSession, Visa.class);

    assertThat(visasInSession.size(), CoreMatchers.is(equalTo(1)));
    assertThat(visasInSession.get(0).getPassportNumber(), CoreMatchers.is(equalTo("AU-EMILY-3")));
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

    ksession.dispose();

    assertThat(visasInSession.size(), CoreMatchers.is(equalTo(2)));
    assertThat(visasInSession.stream().map(Visa::getPassportNumber).collect(Collectors.toList()),
            containsInAnyOrder("AU-EMILY-3", "CA-SIMON-2"));
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

    ksession.dispose();

    assertThat(visasInSession.size(), CoreMatchers.is(equalTo(0)));
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
  }

  @Test
  void testStep7() {
    int step = 7;

    Common.disposeSession = false;
    VisaIssue.execute(step);

    KieSession ksession = TestUtil.getKieSession("VisaIssueStep", step);

    //List<Visa> visasInSession = new ArrayList<>();

   // TestUtil.addObjectsOfType(ksession, visasInSession, Visa.class);

    Object ruleUnit = ((StatefulKnowledgeSessionImpl) ksession).getEntryPointMap().get("io.github.aasaru.drools.section06.step7.Section06RuleUnit.passports").getRuleUnit();
    Section06RuleUnit ru = (Section06RuleUnit) ruleUnit;
    //List<Visa> visasInSession = ru.getVisas();

    List<Visa> visasInSession = StreamSupport.stream(ru.getVisas().spliterator(), false)
            .collect(Collectors.toList());

    assertThat(visasInSession.size(), CoreMatchers.is(equalTo(1)));
    assertThat(visasInSession.get(0).getPassportNumber(), CoreMatchers.is(equalTo("AU-EMILY-3")));
  }

}
