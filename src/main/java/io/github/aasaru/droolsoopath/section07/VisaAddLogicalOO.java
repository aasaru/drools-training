/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.section07;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.SessionData;
import io.github.aasaru.drools.domain.VisaApplication;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.droolsoopath.query.SessionQueryUtil;
import io.github.aasaru.droolsoopath.ruledata.StepRuleUnitUtil;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.drools.ruleunits.api.conf.RuleConfig;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

import java.util.List;

public class VisaAddLogicalOO {
  public static void main(final String[] args) {
    execute(Common.promptForStepOo(7, args, 1, 4));
  }

  public static SessionQueryUtil<RuleUnitSection07> sessionQueryUtil = new SessionQueryUtil<>();

  static SessionData execute(int step) {
    System.out.println("Running step " + step);
    SessionData sessionData = new SessionData();



    RuleUnitSection07 ruleUnit = new StepRuleUnitUtil<RuleUnitSection07>().getRuleUnit(RuleUnitSection07.class, 7, step);


    RuleConfig ruleConfig = RuleUnitProvider.get().newRuleConfig();
    if (step < 5) {
      ruleConfig.getRuleRuntimeListeners().add(new RuleRuntimeEventListener() {
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

    try (RuleUnitInstance<RuleUnitSection07> instance = RuleUnitProvider.get().createRuleUnitInstance(ruleUnit, ruleConfig)) {
      passports.forEach(ruleUnit.getPassports()::append);
      visaApplications.forEach(ruleUnit.getVisaApplications()::append);

      System.out.println("==== DROOLS START ==== ");
      instance.fire();
      System.out.println("==== DROOLS END ==== ");


      if (step > 1 && step < 4) {
        System.out.println("==== VALID PASSPORTS FROM DROOLS SESSION === ");
        sessionData.validPassports = sessionQueryUtil.getAllValidPassportsUsingQuery(instance);
        sessionData.validPassports.forEach(System.out::println);

        System.out.println("==== INVALID PASSPORTS FROM DROOLS SESSION === ");
        sessionData.invalidPassports = sessionQueryUtil.getAllInvalidPassportsUsingQuery(instance);
        sessionData.invalidPassports.forEach(System.out::println);
      }

      if (step == 3) {
        System.out.println("==== VALID APPLICATIONS FROM DROOLS SESSION === ");
        sessionData.validVisaApplications = sessionQueryUtil.getAllValidVisaApplicationsUsingQuery(instance);
        sessionData.validVisaApplications.forEach(System.out::println);

        System.out.println("==== INVALID APPLICATIONS FROM DROOLS SESSION === ");
        sessionData.invalidVisaApplications = sessionQueryUtil.getAllInvalidVisaApplicationsUsingQuery(instance);
        sessionData.invalidVisaApplications.forEach(System.out::println);
      }

      if (step != 2) {
        sessionData.visas = sessionQueryUtil.getAllVisasUsingQuery(instance);
        System.out.println("== Visas from session == ");
        sessionData.visas.forEach(System.out::println);
      }

    }
    return sessionData;
  }



}
