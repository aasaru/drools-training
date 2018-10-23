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
import io.github.aasaru.drools.section06.AgendaGroupEventListener;
import org.kie.api.KieServices;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieSession;

import java.util.Collection;
import java.util.List;

public class VisaInsertLogical {
  public static void main(final String[] args) {
    execute(Common.promptForStep(7, args, 1, 4));
  }


  static void execute(int step) {
    System.out.println("Running step " + step);
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
    ksession.dispose();
    System.out.println("==== DROOLS SESSION END ==== ");

    if (step > 1 && step < 4) {
      System.out.println("==== VALID PASSPORTS FROM DROOLS SESSION === ");
      Collection<?> validPassport = ksession.getObjects(o -> o.getClass() == ValidPassport.class);
      validPassport.forEach(System.out::println);

      System.out.println("==== INVALID PASSPORTS FROM DROOLS SESSION === ");
      Collection<?> invalidPassport = ksession.getObjects(o -> o.getClass() == InvalidPassport.class);
      invalidPassport.forEach(System.out::println);
    }

    if (step == 3) {
      System.out.println("==== VALID APPLICATIONS FROM DROOLS SESSION === ");
      Collection<?> validApplications = ksession.getObjects(o -> o.getClass() == ValidVisaApplication.class);
      validApplications.forEach(System.out::println);

      System.out.println("==== INVALID APPLICATIONS FROM DROOLS SESSION === ");
      Collection<?> invalidApplications = ksession.getObjects(o -> o.getClass() == InvalidVisaApplication.class);
      invalidApplications.forEach(System.out::println);
    }

    if (step != 2) {
      Collection<?> visas = ksession.getObjects(o -> o.getClass() == Visa.class);
      System.out.println("== Visas from session == ");
      visas.forEach(System.out::println);
    }
  }

}
