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
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.VisaApplication;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.drools.section03.PassportUnit;
import org.drools.ruleunit.RuleUnitExecutor;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.List;

public class VisaApplicationValidation {
  public static void main(final String[] args) {
    execute(Common.promptForStep(5, args, 1, 4));
  }


  static void execute(int step) {
    System.out.println("Running step " + step);
    KieContainer kieClasspathContainer = KieServices.Factory.get().getKieClasspathContainer();
    KieSession ksession = kieClasspathContainer.newKieSession("VisaApplicationStep" + step);

    List<Passport> passports = ApplicationRepository.getPassports();
    List<VisaApplication> visaApplications = ApplicationRepository.getVisaApplications();

    if (step < 4) {
      passports.forEach(ksession::insert);
      visaApplications.forEach(ksession::insert);
      System.out.println("==== DROOLS SESSION START ==== ");
      ksession.fireAllRules();
      if (Common.disposeSession) {
        ksession.dispose();
      }
      System.out.println("==== DROOLS SESSION END ==== ");
    }
    else {
      KieBase kbase = kieClasspathContainer.getKieBase("section05.step"+step);
      RuleUnitExecutor executor = RuleUnitExecutor.create().bind(kbase);
      PassportVisaApplicationUnit passportVisaApplicationUnit = getRuleUnit(step, passports, visaApplications);
      executor.run(passportVisaApplicationUnit);
    }

    System.out.println("==== PASSPORTS AFTER DROOLS SESSION === ");
    passports.forEach(passport -> System.out.println(passport + " verdict: " + passport.getValidation()));

    System.out.println("==== APPLICATIONS STATE AFTER DROOLS SESSION === ");
    visaApplications.forEach(visaApplication ->
        System.out.println(visaApplication + " verdict: " + visaApplication.getValidation())
    );


  }

  private static PassportVisaApplicationUnit getRuleUnit(int step, List<Passport> passports, List<VisaApplication> visaApplications) {
    PassportVisaApplicationUnit passportVisaApplicationUnit = null;
    if (step == 4) {
      passportVisaApplicationUnit = new io.github.aasaru.drools.section05.step4.PassportVisaApplicationUnit(passports, visaApplications);
    }

    return passportVisaApplicationUnit;
  }

}
