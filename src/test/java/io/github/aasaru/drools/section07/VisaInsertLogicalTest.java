/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.section07;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.TestUtil;
import io.github.aasaru.drools.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.aasaru.drools.repository.ApplicationRepository.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class VisaInsertLogicalTest {

  @Test
  void shouldExecuteAllSteps() {
    VisaInsertLogical.execute(1);
    VisaInsertLogical.execute(2);
    VisaInsertLogical.execute(3);
    VisaInsertLogical.execute(4);
  }

  @Test
  void testStep1() {
    int step = 1;

    Common.disposeSession = false;
    SessionData sessionData = VisaInsertLogical.execute(step);

    Assertions.assertThat(sessionData.visas)
      .map(Visa::getPassportNumber)
      .containsExactlyInAnyOrder("AU-EMILY-3");

    String kieSessionName = "VisaInsertLogicalStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);
  }

  @Test
  void testStep2() {
    int step = 2;

    Common.disposeSession = false;
    VisaInsertLogical.execute(step);

    KieSession ksession = TestUtil.getKieSession("VisaInsertLogicalStep", step);

    assertThat(getPassportNumbersOfValidPassports(ksession), containsInAnyOrder(EMILY_PASSPORT_NUMBER, JAMES_PASSPORT_NUMBER));
    assertThat(getPassportNumbersOfInvalidPassports(ksession), containsInAnyOrder(SARAH_PASSPORT_NUMBER, SIMON_PASSPORT_NUMBER));

    ksession.dispose();
  }

  @Test
  void testStep3() {
    int step = 3;

    Common.disposeSession = false;
    VisaInsertLogical.execute(step);

    KieSession ksession = TestUtil.getKieSession("VisaInsertLogicalStep", step);


    Collection<ValidVisaApplication> validVisaApplications = new HashSet<>();
    addObjectsOfType(ksession, validVisaApplications, ValidVisaApplication.class);
    assertThat(validVisaApplications.stream().findFirst().get().getVisaApplication().getPassportNumber(), is(equalTo(EMILY_PASSPORT_NUMBER)));


    Collection<InvalidVisaApplication> invalidVisaApplications = new HashSet<>();
    addObjectsOfType(ksession, invalidVisaApplications, InvalidVisaApplication.class);
    List<String> passportNumbersOfInvalidVisaApplicaitons = passportNumbersOfInvalidVisaApplications(invalidVisaApplications);
    assertThat(passportNumbersOfInvalidVisaApplicaitons, containsInAnyOrder(JAMES_PASSPORT_NUMBER, SARAH_PASSPORT_NUMBER, SIMON_PASSPORT_NUMBER));


    assertThat(getPassportNumbersOfValidPassports(ksession), containsInAnyOrder(EMILY_PASSPORT_NUMBER, JAMES_PASSPORT_NUMBER));
    assertThat(getPassportNumbersOfInvalidPassports(ksession), containsInAnyOrder(SARAH_PASSPORT_NUMBER, SIMON_PASSPORT_NUMBER));

    ksession.dispose();
  }

  private List<String> passportNumbersOfInvalidVisaApplications(Collection<InvalidVisaApplication> invalidVisaApplications) {
    return invalidVisaApplications.stream().map(InvalidVisaApplication::getVisaApplication).map(VisaApplication::getPassportNumber).collect(Collectors.toList());
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private void addObjectsOfType(KieSession ksession, Collection collection, Class type) {

    for (var object : ksession.getObjects()) {
      if (object.getClass() == type) {
        collection.add(type.cast(object));
      }
    }

  }


  private List<String> getPassportNumbersOfValidPassports(KieSession ksession) {
    Collection<ValidPassport> validPassports = new HashSet<>();
    addObjectsOfType(ksession, validPassports, ValidPassport.class);

    return validPassports.stream()
            .map(ValidPassport::getPassport)
            .map(Passport::getPassportNumber)
            .collect(Collectors.toList());
  }

  private List<String> getPassportNumbersOfInvalidPassports(KieSession ksession) {
    Collection<InvalidPassport> invalidPassports = new HashSet<>();
    addObjectsOfType(ksession, invalidPassports, InvalidPassport.class);


    return invalidPassports.stream()
            .map(InvalidPassport::getPassport)
            .map(Passport::getPassportNumber)
            .collect(Collectors.toList());
  }


}
