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

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.TestUtil;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.Validation;
import io.github.aasaru.drools.domain.VisaApplication;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class VisaApplicationValidationTest {

  @Test
  void testStep1() {
    Common.disposeSession = false;
    int step = 1;

    String kieSessionName = "VisaApplicationStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    VisaApplicationValidation.execute(step);

    KieSession ksession = TestUtil.getKieSession("VisaApplicationStep", step);

    List<VisaApplication> visaApplicationsInSession = new ArrayList<>();
    TestUtil.addObjectsOfType(ksession, visaApplicationsInSession, VisaApplication.class);

    Map<String, VisaApplication> visaApplicationMap = visaApplicationsInSession.stream()
            .collect(Collectors.toMap(VisaApplication::getPassportNumber, Function.identity()));

    assertThat(visaApplicationMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(visaApplicationMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.PASSED)));


    List<Passport> passportsInSession = new ArrayList<>();
    TestUtil.addObjectsOfType(ksession, passportsInSession, Passport.class);
    Map<String, Passport> passportMap = passportsInSession.stream()
            .collect(Collectors.toMap(Passport::getPassportNumber, Function.identity()));

    assertThat(passportMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(passportMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.PASSED)));

    ksession.dispose();
  }

  @Test
  void testStep2() {
    Common.disposeSession = false;
    int step = 2;

    String kieSessionName = "VisaApplicationStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    VisaApplicationValidation.execute(step);

    KieSession ksession = TestUtil.getKieSession("VisaApplicationStep", step);

    List<VisaApplication> visaApplicationsInSession = new ArrayList<>();
    TestUtil.addObjectsOfType(ksession, visaApplicationsInSession, VisaApplication.class);

    Map<String, VisaApplication> visaApplicationMap = visaApplicationsInSession.stream()
            .collect(Collectors.toMap(VisaApplication::getPassportNumber, Function.identity()));

    assertThat(visaApplicationMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(visaApplicationMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.FAILED)));


    List<Passport> passportsInSession = new ArrayList<>();
    TestUtil.addObjectsOfType(ksession, passportsInSession, Passport.class);
    Map<String, Passport> passportMap = passportsInSession.stream()
            .collect(Collectors.toMap(Passport::getPassportNumber, Function.identity()));

    assertThat(passportMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(passportMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.PASSED)));

    ksession.dispose();
  }

  @Test
  void testStep3() {
    Common.disposeSession = false;
    int step = 3;

    String kieSessionName = "VisaApplicationStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    VisaApplicationValidation.execute(step);

    KieSession ksession = TestUtil.getKieSession("VisaApplicationStep", step);

    List<VisaApplication> visaApplicationsInSession = new ArrayList<>();
    TestUtil.addObjectsOfType(ksession, visaApplicationsInSession, VisaApplication.class);

    Map<String, VisaApplication> visaApplicationMap = visaApplicationsInSession.stream()
            .collect(Collectors.toMap(VisaApplication::getPassportNumber, Function.identity()));

    assertThat(visaApplicationMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(visaApplicationMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(visaApplicationMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.FAILED)));


    List<Passport> passportsInSession = new ArrayList<>();
    TestUtil.addObjectsOfType(ksession, passportsInSession, Passport.class);
    Map<String, Passport> passportMap = passportsInSession.stream()
            .collect(Collectors.toMap(Passport::getPassportNumber, Function.identity()));

    assertThat(passportMap.get("CA-SARAH-1").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("CA-SIMON-2").getValidation(), is(equalTo(Validation.FAILED)));
    assertThat(passportMap.get("AU-EMILY-3").getValidation(), is(equalTo(Validation.PASSED)));
    assertThat(passportMap.get("AU-JAMES-4").getValidation(), is(equalTo(Validation.PASSED)));

    ksession.dispose();
  }

}
