/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.section05;

import io.github.aasaru.drools.TestUtil;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.SessionData;
import io.github.aasaru.drools.domain.Validation;
import io.github.aasaru.drools.domain.VisaApplication;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class VisaApplicationValidationOOTest {

  @Test
  void testStep1() {
    int step = 1;

    String kieSessionName = "VisaApplicationStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    SessionData sessionData = VisaApplicationValidationOO.execute(step);

    Collection<VisaApplication> visaApplicationsInSession = sessionData.visaApplications;

    Map<String, VisaApplication> visaApplicationMap = visaApplicationsInSession.stream()
            .collect(Collectors.toMap(VisaApplication::getPassportNumber, Function.identity()));

    assertThat(visaApplicationMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(visaApplicationMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.PASSED)));


    Map<String, Passport> passportMap = sessionData.passports.stream()
            .collect(Collectors.toMap(Passport::getPassportNumber, Function.identity()));

    assertThat(passportMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(passportMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.PASSED)));
  }

  @Test
  void testStep2() {
    int step = 2;

    String kieSessionName = "VisaApplicationStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    SessionData sessionData = VisaApplicationValidationOO.execute(step);

    Collection<VisaApplication> visaApplicationsInSession = sessionData.visaApplications;

    Map<String, VisaApplication> visaApplicationMap = visaApplicationsInSession.stream()
            .collect(Collectors.toMap(VisaApplication::getPassportNumber, Function.identity()));

    assertThat(visaApplicationMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(visaApplicationMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.FAILED)));


    Map<String, Passport> passportMap = sessionData.passports.stream()
            .collect(Collectors.toMap(Passport::getPassportNumber, Function.identity()));

    assertThat(passportMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(passportMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.PASSED)));
  }

  @Test
  void testStep3() {
    int step = 3;

    String kieSessionName = "VisaApplicationStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    SessionData sessionData = VisaApplicationValidationOO.execute(step);

    Collection<VisaApplication> visaApplicationsInSession = sessionData.visaApplications;

    Map<String, VisaApplication> visaApplicationMap = visaApplicationsInSession.stream()
            .collect(Collectors.toMap(VisaApplication::getPassportNumber, Function.identity()));

    assertThat(visaApplicationMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(visaApplicationMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.FAILED)));


    Map<String, Passport> passportMap = sessionData.passports.stream()
            .collect(Collectors.toMap(Passport::getPassportNumber, Function.identity()));

    assertThat(passportMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(passportMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.PASSED)));
  }

}
