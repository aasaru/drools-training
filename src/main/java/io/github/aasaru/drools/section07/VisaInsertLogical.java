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
import io.github.aasaru.drools.section07.step5.Section07RuleUnit;
import org.drools.ruleunit.RuleUnitExecutor;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Collection;
import java.util.List;

public class VisaInsertLogical {
  public static void main(final String[] args) {
    execute(Common.promptForStep(7, args, 1, 5));
  }


  static void execute(int step) {
    System.out.println("Running step " + step);
    KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
    KieSession ksession = kieContainer.newKieSession("VisaInsertLogicalStep" + step);

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
    List<VisaApplication> visaApplications = ApplicationRepository.getVisaApplications();

    if (step <= 4) {
      passports.forEach(ksession::insert);
      visaApplications.forEach(ksession::insert);
    }
    else {
      Section07RuleUnit section07RuleUnit = new Section07RuleUnit(passports, visaApplications);

      KieBase kbase = kieContainer.getKieBase("section07.step"+step);
      RuleUnitExecutor executor = RuleUnitExecutor.create().bind(kbase);
      executor.run(section07RuleUnit);

    }



    System.out.println("==== DROOLS SESSION START ==== ");
    ksession.fireAllRules();
    if (Common.disposeSession) {
      ksession.dispose();
    }
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
