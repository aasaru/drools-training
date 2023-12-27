/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */
package io.github.aasaru.drools.section08;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.TestUtil;
import io.github.aasaru.drools.domain.GroupLeader;
import io.github.aasaru.drools.domain.InvalidFamilyVisaApplication;
import io.github.aasaru.drools.domain.Visa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class FamilyVisaApplicationValidationTest {

  @Test
  void shouldExecuteAllSteps() {
    FamilyVisaApplicationValidation.execute(1);
    FamilyVisaApplicationValidation.execute(2);

    emulateInputFromKeyboard("yes");
    FamilyVisaApplicationValidation.execute(3);

    FamilyVisaApplicationValidation.execute(4);
  }

  @Test
  @SuppressWarnings("unchecked")
  void test_runStep1_invalidVisaApplication10isFoundTwoTimes() {
    Common.disposeSession = false;
    int step = 1;

    FamilyVisaApplicationValidation.execute(step);

    KieSession ksession = TestUtil.getKieSession("FamilyVisaApplicationStep", step);

    Collection<InvalidFamilyVisaApplication> invalidApplications = (Collection<InvalidFamilyVisaApplication>) ksession.getObjects(o -> o.getClass() == InvalidFamilyVisaApplication.class);
    Assertions.assertThat(invalidApplications).hasSize(2);
    Assertions.assertThat(invalidApplications).extracting("familyVisaApplication.applicationId").containsOnly(10);

    asserVisasForEmilyAndJamesWereIssued(ksession);
    ksession.dispose();
  }

  @Test
  void test_runStep2_invalidVisaApplication10isFoundOnlyOnce() {
    Common.disposeSession = false;
    int step = 2;

    FamilyVisaApplicationValidation.execute(step);
    KieSession ksession = TestUtil.getKieSession("FamilyVisaApplicationStep", step);

    assertOnlyVisaApplication10isMarkedInvalid(ksession);
    asserVisasForEmilyAndJamesWereIssued(ksession);
    ksession.dispose();
  }

  @Test
  void testStep3_makeEveryone3yearsYounger_bothVisaApplicationsAreMarkedInvalid() {
    Common.disposeSession = false;
    int step = 3;
    emulateInputFromKeyboard("yes");

    FamilyVisaApplicationValidation.execute(step);
    KieSession ksession = TestUtil.getKieSession("FamilyVisaApplicationStep" + step + "true");

    assertBothFamilyVisaApplicationsAreMarkedAsInvalid(ksession);
    assertNoVisasAreIssued(ksession);
    ksession.dispose();
  }

  @Test
  void testStep3_doNotMakeAnyoneYounger_visaApplication11isNotMarkedInvalid() {
    Common.disposeSession = false;
    int step = 3;
    emulateInputFromKeyboard("no");

    FamilyVisaApplicationValidation.execute(step);
    KieSession ksession = TestUtil.getKieSession("FamilyVisaApplicationStep" + step + "false");

    assertOnlyVisaApplication10isMarkedInvalid(ksession);
    asserVisasForEmilyAndJamesWereIssued(ksession);
    ksession.dispose();
  }

  @Test
  void testStep4_jamesIsSelectedAsGroupLeader() {
    Common.disposeSession = false;
    int step = 4;

    FamilyVisaApplicationValidation.execute(step);
    KieSession ksession = TestUtil.getKieSession("FamilyVisaApplicationStep", step);

    assertOnlyVisaApplication10isMarkedInvalid(ksession);
    asserVisasForEmilyAndJamesWereIssued(ksession);
    assertJamesIsSetAsGroupLeader(ksession);
    ksession.dispose();
  }

  @Test
  void testStep5_jamesIsSelectedAsGroupLeader() {
    Common.disposeSession = false;
    int step = 5;

    FamilyVisaApplicationValidation.execute(step);
    KieSession ksession = TestUtil.getKieSession("FamilyVisaApplicationStep", step);

    assertOnlyVisaApplication10isMarkedInvalid(ksession);
    asserVisasForEmilyAndJamesWereIssued(ksession);
    assertJamesIsSetAsGroupLeader(ksession);
    ksession.dispose();
  }

  @SuppressWarnings("unchecked") 
  private static void assertNoVisasAreIssued(KieSession ksession) {
    Collection<Visa> visas = (Collection<Visa>) ksession.getObjects(o -> o.getClass() == Visa.class);
    Assertions.assertThat(visas).isEmpty();
  }

  @SuppressWarnings("unchecked") 
  private static void assertBothFamilyVisaApplicationsAreMarkedAsInvalid(KieSession ksession) {
    Collection<InvalidFamilyVisaApplication> invalidApplications = (Collection<InvalidFamilyVisaApplication>) ksession.getObjects(o -> o.getClass() == InvalidFamilyVisaApplication.class);
    Assertions.assertThat(invalidApplications).extracting("familyVisaApplication.applicationId").containsOnly(10, 11);
  }

  @SuppressWarnings("unchecked") 
  private static void assertOnlyVisaApplication10isMarkedInvalid(KieSession ksession) {
    Collection<InvalidFamilyVisaApplication> invalidApplications = (Collection<InvalidFamilyVisaApplication>) ksession.getObjects(o -> o.getClass() == InvalidFamilyVisaApplication.class);
    System.out.println(invalidApplications);
    Assertions.assertThat(invalidApplications).extracting("familyVisaApplication.applicationId").containsOnly(10);
  }

  @SuppressWarnings("unchecked") 
  private static void asserVisasForEmilyAndJamesWereIssued(KieSession ksession) {
    Collection<Visa> visas = (Collection<Visa>) ksession.getObjects(o -> o.getClass() == Visa.class);
    Assertions.assertThat(visas).extracting("passportNumber").containsOnly("AU-EMILY-3", "AU-JAMES-4");
  }

  @SuppressWarnings("unchecked") 
  private static void assertJamesIsSetAsGroupLeader(KieSession ksession) {
    Collection<GroupLeader> groupLeaders = (Collection<GroupLeader>) ksession.getObjects(o -> o.getClass() == GroupLeader.class);
    Assertions.assertThat(groupLeaders).extracting("passport.name").containsOnly("James Brown");
  }

  public static void emulateInputFromKeyboard(String inputFromKeyboard) {
    System.setIn(new ByteArrayInputStream(inputFromKeyboard.getBytes(StandardCharsets.UTF_8)));
  }

}
