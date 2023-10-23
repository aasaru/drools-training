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
import io.github.aasaru.drools.domain.*;
import io.github.aasaru.drools.repository.ApplicationRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import java.util.Collection;
import java.util.List;

public class FamilyVisaApplicationValidation {
  public static void main(final String[] args) {
    execute(Common.promptForStep(8, args, 1, 5));
  }

  static void execute(int step) {
    System.out.println("Running step " + step);

    List<Passport> passports = ApplicationRepository.getPassports();
    String sessionName = "FamilyVisaApplicationStep" + step;
    if (step == 3) {
      boolean isYounger = Common.promptForYesNoQuestion("Do you want to make everyone 3 years younger?");
      sessionName += isYounger;
      if (isYounger) {
        System.out.println("Making everyone 3 years younger");
        passports.forEach(passport -> passport.setAge(passport.getAge()-3));
        passports.forEach(passport -> System.out.println(passport + " is now " + passport.getAge()));
      }
    }
    KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession(sessionName);
    passports.forEach(ksession::insert);

    List<FamilyVisaApplication> familyVisaApplications = ApplicationRepository.getFamilyVisaApplications();
    familyVisaApplications.forEach(ksession::insert);

    System.out.println("==== DROOLS SESSION START ==== ");
    ksession.fireAllRules();
    System.out.println("==== DROOLS SESSION END ==== ");

    System.out.println("==== INVALID FAMILY VISA APPLICATIONS FROM DROOLS SESSION === ");
    Collection<?> invalidApplications = ksession.getObjects(o -> o.getClass() == InvalidFamilyVisaApplication.class);
    invalidApplications.forEach(System.out::println);

    Collection<?> visas = ksession.getObjects(o -> o.getClass() == Visa.class);
    System.out.println("== Visas from session == ");
    visas.forEach(System.out::println);

    Collection<?> groupLeaders = ksession.getObjects(o -> o.getClass() == GroupLeader.class);
    System.out.println("== Group leaders from session == ");
    groupLeaders.forEach(System.out::println);

    if (Common.disposeSession) {
      ksession.dispose();
    }

  }

}
