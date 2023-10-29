/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools8.section07;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.aasaru.drools.repository.ApplicationRepository.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class VisaAddLogicalD8Test {

  @Test
  void shouldExecuteAllSteps() {
    VisaAddLogicalD8.execute(1);
    VisaAddLogicalD8.execute(2);
    VisaAddLogicalD8.execute(3);
    VisaAddLogicalD8.execute(4);
  }

  @Test
  @Disabled // https://issues.redhat.com/browse/DROOLS-7583
  void testStep1() {
    int step = 1;

    Common.disposeSession = false;
    VisaAddLogicalD8.SessionData sessionData = VisaAddLogicalD8.execute(step);

    Assertions.assertThat(sessionData.visas)
      .map(Visa::getPassportNumber)
      .containsExactlyInAnyOrder("AU-EMILY-3");

  }

  @Disabled // https://issues.redhat.com/browse/DROOLS-7583
  @Test
  void testStep2() {
    int step = 2;

    VisaAddLogicalD8.SessionData sessionData = VisaAddLogicalD8.execute(step);

    assertThat(getPassportNumbersOfValidPassports(sessionData), containsInAnyOrder(EMILY_PASSPORT_NUMBER, JAMES_PASSPORT_NUMBER));
    assertThat(getPassportNumbersOfInvalidPassports(sessionData), containsInAnyOrder(SARAH_PASSPORT_NUMBER, SIMON_PASSPORT_NUMBER));
  }

  @Test
  @Disabled // https://issues.redhat.com/browse/DROOLS-7583
  void testStep3() {
    int step = 3;

    VisaAddLogicalD8.SessionData sessionData = VisaAddLogicalD8.execute(step);

    assertThat(sessionData.validVisaApplications.stream().findFirst().get().getVisaApplication().getPassportNumber(), is(equalTo(EMILY_PASSPORT_NUMBER)));

    List<String> passportNumbersOfInvalidVisaApplications = passportNumbersOfInvalidVisaApplications(sessionData.invalidVisaApplications);
    assertThat(passportNumbersOfInvalidVisaApplications, containsInAnyOrder(JAMES_PASSPORT_NUMBER, SARAH_PASSPORT_NUMBER, SIMON_PASSPORT_NUMBER));


    assertThat(getPassportNumbersOfValidPassports(sessionData), containsInAnyOrder(EMILY_PASSPORT_NUMBER, JAMES_PASSPORT_NUMBER));
    assertThat(getPassportNumbersOfInvalidPassports(sessionData), containsInAnyOrder(SARAH_PASSPORT_NUMBER, SIMON_PASSPORT_NUMBER));
  }

  private List<String> passportNumbersOfInvalidVisaApplications(Collection<InvalidVisaApplication> invalidVisaApplications) {
    return invalidVisaApplications.stream().map(InvalidVisaApplication::getVisaApplication).map(VisaApplication::getPassportNumber).collect(Collectors.toList());
  }

  private List<String> getPassportNumbersOfValidPassports(VisaAddLogicalD8.SessionData sessionData) {
    return sessionData.validPassports.stream()
            .map(ValidPassport::getPassport)
            .map(Passport::getPassportNumber)
            .collect(Collectors.toList());
  }

  private List<String> getPassportNumbersOfInvalidPassports(VisaAddLogicalD8.SessionData sessionData) {
    return sessionData.invalidPassports.stream()
            .map(InvalidPassport::getPassport)
            .map(Passport::getPassportNumber)
            .collect(Collectors.toList());
  }

}
