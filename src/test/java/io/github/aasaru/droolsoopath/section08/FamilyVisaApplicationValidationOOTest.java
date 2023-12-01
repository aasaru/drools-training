/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */
package io.github.aasaru.droolsoopath.section08;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.GroupLeader;
import io.github.aasaru.drools.domain.InvalidFamilyVisaApplication;
import io.github.aasaru.drools.domain.SessionData;
import io.github.aasaru.drools.domain.Visa;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class FamilyVisaApplicationValidationOOTest {

  @Test
  void shouldExecuteAllSteps() {
    FamilyVisaApplicationValidationOO.execute(1);
    FamilyVisaApplicationValidationOO.execute(2);

    emulateInputFromKeyboard("yes");
    FamilyVisaApplicationValidationOO.execute(3);

    FamilyVisaApplicationValidationOO.execute(4);
  }

  @Test
  void test_runStep1_invalidVisaApplication10isFoundTwoTimes() {
    Common.disposeSession = false;
    int step = 1;

    SessionData sessionData = FamilyVisaApplicationValidationOO.execute(step);

    Assertions.assertThat(sessionData.invalidFamilyVisaApplications).hasSize(2);
    Assertions.assertThat(sessionData.invalidFamilyVisaApplications).extracting("familyVisaApplication.applicationId").containsOnly(10);

    assertVisasForEmilyAndJamesWereIssued(sessionData.visas);
  }

  @Test
  void test_runStep2_invalidVisaApplication10isFoundOnlyOnce() {
    Common.disposeSession = false;
    int step = 2;

    SessionData sessionData = FamilyVisaApplicationValidationOO.execute(step);

    assertOnlyVisaApplication10isMarkedInvalid(sessionData.invalidFamilyVisaApplications);
    assertVisasForEmilyAndJamesWereIssued(sessionData.visas);
  }

  @Test
  void testStep3_makeEveryone3yearsYounger_bothVisaApplicationsAreMarkedInvalid() {
    Common.disposeSession = false;
    int step = 3;
    emulateInputFromKeyboard("yes");

    SessionData sessionData = FamilyVisaApplicationValidationOO.execute(step);

    assertBothFamilyVisaApplicationsAreMarkedAsInvalid(sessionData.invalidFamilyVisaApplications);
    assertNoVisasAreIssued(sessionData.visas);
  }

  @Test
  void testStep3_doNotMakeAnyoneYounger_visaApplication11isNotMarkedInvalid() {
    Common.disposeSession = false;
    int step = 3;
    emulateInputFromKeyboard("no");

    SessionData sessionData = FamilyVisaApplicationValidationOO.execute(step);

    assertOnlyVisaApplication10isMarkedInvalid(sessionData.invalidFamilyVisaApplications);
    assertVisasForEmilyAndJamesWereIssued(sessionData.visas);
  }

  @Test
  void testStep4_jamesIsSelectedAsGroupLeader() {
    Common.disposeSession = false;
    int step = 4;

    SessionData sessionData = FamilyVisaApplicationValidationOO.execute(step);

    assertOnlyVisaApplication10isMarkedInvalid(sessionData.invalidFamilyVisaApplications);
    assertVisasForEmilyAndJamesWereIssued(sessionData.visas);
    assertJamesIsSetAsGroupLeader(sessionData.groupLeaders);
  }

  @Test
  void testStep5_jamesIsSelectedAsGroupLeader() {
    Common.disposeSession = false;
    int step = 5;

    SessionData sessionData = FamilyVisaApplicationValidationOO.execute(step);

    assertOnlyVisaApplication10isMarkedInvalid(sessionData.invalidFamilyVisaApplications);
    assertVisasForEmilyAndJamesWereIssued(sessionData.visas);
    assertJamesIsSetAsGroupLeader(sessionData.groupLeaders);
  }

  private static void assertNoVisasAreIssued(Collection<Visa> visas) {
    Assertions.assertThat(visas).isEmpty();
  }

  private static void assertBothFamilyVisaApplicationsAreMarkedAsInvalid(Collection<InvalidFamilyVisaApplication> invalidFamilyVisaApplications) {
    Assertions.assertThat(invalidFamilyVisaApplications).extracting("familyVisaApplication.applicationId").containsOnly(10, 11);
  }


  private static void assertOnlyVisaApplication10isMarkedInvalid(Collection<InvalidFamilyVisaApplication> invalidApplications) {
    System.out.println(invalidApplications);
    Assertions.assertThat(invalidApplications).extracting("familyVisaApplication.applicationId").containsOnly(10);
  }


  private static void assertVisasForEmilyAndJamesWereIssued(Collection<Visa> visas) {
    Assertions.assertThat(visas).extracting("passportNumber").containsOnly("AU-EMILY-3", "AU-JAMES-4");
  }

  private static void assertJamesIsSetAsGroupLeader(Collection<GroupLeader> groupLeaders) {
    Assertions.assertThat(groupLeaders).extracting("passport.name").containsOnly("James Brown");
  }

  public static void emulateInputFromKeyboard(String inputFromKeyboard) {
    System.setIn(new ByteArrayInputStream(inputFromKeyboard.getBytes(StandardCharsets.UTF_8)));
  }

}
