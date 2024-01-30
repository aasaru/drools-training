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
import io.github.aasaru.drools.domain.*;
import io.github.aasaru.drools.repository.ApplicationRepository;
import org.kie.api.KieServices;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieSession;

import java.util.List;
import java.util.stream.Collectors;

public class VisaInsertLogical {
  public static void main(final String[] args) {
    execute(Common.promptForStep(7, args, 1, 4));
  }


  static SessionData execute(int step) {
    System.out.println("Running step " + step);
    SessionData sessionData = new SessionData();

    KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("VisaInsertLogicalStep" + step);

    if (step < 3) {
      ksession.addEventListener(new RuleRuntimeEventListener() {
        @Override
        public void objectInserted(ObjectInsertedEvent event) {
          System.out.println("==> " + event.getObject() + " inserted");
        }

        @Override
        public void objectUpdated(ObjectUpdatedEvent event) {
          System.out.println("==> " + event.getObject() + " updated");
        }

        @Override
        public void objectDeleted(ObjectDeletedEvent event) {
          System.out.println("==> " + event.getOldObject() + " deleted");
        }
      });
    }

    List<Passport> passports = ApplicationRepository.getPassports();
    passports.forEach(ksession::insert);

    List<VisaApplication> visaApplications = ApplicationRepository.getVisaApplications();
    visaApplications.forEach(ksession::insert);

    System.out.println("==== DROOLS SESSION START ==== ");
    ksession.fireAllRules();
    System.out.println("==== DROOLS SESSION END ==== ");

    if (step > 1 && step < 4) {
      System.out.println("==== VALID PASSPORTS FROM DROOLS SESSION === ");
      sessionData.validPassports = ksession.getObjects(o -> o.getClass() == ValidPassport.class).stream()
        .map(o -> (ValidPassport) o)
        .collect(Collectors.toList());
      sessionData.validPassports.forEach(System.out::println);

      System.out.println("==== INVALID PASSPORTS FROM DROOLS SESSION === ");
      sessionData.invalidPassports = ksession.getObjects(o -> o.getClass() == InvalidPassport.class).stream()
          .map(o -> (InvalidPassport) o)
            .collect(Collectors.toList());
      sessionData.invalidPassports.forEach(System.out::println);
    }

    if (step == 3) {
      System.out.println("==== VALID APPLICATIONS FROM DROOLS SESSION === ");
      sessionData.validVisaApplications = ksession.getObjects(o -> o.getClass() == ValidVisaApplication.class).stream()
        .map(o -> (ValidVisaApplication) o)
        .collect(Collectors.toList());
      sessionData.validVisaApplications.forEach(System.out::println);

      System.out.println("==== INVALID APPLICATIONS FROM DROOLS SESSION === ");
      sessionData.invalidVisaApplications = ksession
        .getObjects(o -> o.getClass() == InvalidVisaApplication.class).stream()
        .map(o -> (InvalidVisaApplication) o)
        .collect(Collectors.toList());
      sessionData.invalidVisaApplications.forEach(System.out::println);
    }

    if (step != 2) {
      sessionData.visas = ksession
        .getObjects(o -> o.getClass() == Visa.class).stream()
        .map(o -> (Visa) o)
        .collect(Collectors.toList());
      System.out.println("== Visas from session == ");
      sessionData.visas.forEach(System.out::println);
    }

    if (Common.disposeSession) {
      ksession.dispose();
    }

    return sessionData;
  }

}
