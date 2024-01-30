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
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.List;

public class VisaApplicationValidation {
  public static void main(final String[] args) {
    execute(Common.promptForStep(5, args, 1, 3));
  }


  static void execute(int step) {
    System.out.println("Running step " + step);
    KieContainer kieClasspathContainer = KieServices.Factory.get().getKieClasspathContainer();
    KieSession ksession = kieClasspathContainer.newKieSession("VisaApplicationStep" + step);

    List<Passport> passports = ApplicationRepository.getPassports();
    passports.forEach(ksession::insert);

    List<VisaApplication> visaApplications = ApplicationRepository.getVisaApplications();
    visaApplications.forEach(ksession::insert);

    System.out.println("==== DROOLS SESSION START ==== ");
    ksession.fireAllRules();
    System.out.println("==== DROOLS SESSION END ==== ");

    System.out.println("==== PASSPORTS AFTER DROOLS SESSION === ");
    passports.forEach(passport -> System.out.println(passport + " verdict: " + passport.getValidation()));

    System.out.println("==== APPLICATIONS STATE AFTER DROOLS SESSION === ");
    visaApplications.forEach(visaApplication ->
        System.out.println(visaApplication + " verdict: " + visaApplication.getValidation())
    );

    if (Common.disposeSession) {
      ksession.dispose();
    }

  }

}
