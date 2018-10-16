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
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.Visa;
import io.github.aasaru.drools.domain.VisaApplication;
import io.github.aasaru.drools.repository.ApplicationRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;

import java.util.Collection;
import java.util.List;

public class VisaIssue {
  public static void main(final String[] args) {
    execute(Common.promptForStep(6, args, 1, 3));
  }


  static void execute(int step) {
    System.out.println("Running step " + step);
    KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("VisaIssueStep" + step);

    List<Passport> passports = ApplicationRepository.getPassports();
    passports.forEach(ksession::insert);

    List<VisaApplication> visaApplications = ApplicationRepository.getVisaApplications();
    visaApplications.forEach(ksession::insert);

    if (step == 3) {
      Agenda agenda = ksession.getAgenda();
      agenda.getAgendaGroup("issue-visa").setFocus();
      agenda.getAgendaGroup("valid-application").setFocus();
      agenda.getAgendaGroup("invalid-application").setFocus();
      agenda.getAgendaGroup("valid-passport").setFocus();
      agenda.getAgendaGroup("invalid-passport").setFocus();
    }

    System.out.println("==== DROOLS SESSION START ==== ");
    ksession.fireAllRules();
    ksession.dispose();
    System.out.println("==== DROOLS SESSION END ==== ");


    System.out.println("==== APPLICATIONS STATE AFTER DROOLS SESSION === ");
    visaApplications.forEach(visaApplication -> System.out.println(visaApplication + " verdict: " + visaApplication.getValidation()));



    Collection<?> visaObjects = ksession.getObjects(o -> o.getClass() == Visa.class);
    System.out.println("== Visas from session == ");
    visaObjects.forEach(System.out::println);


  }

}
