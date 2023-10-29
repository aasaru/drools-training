/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools8.section07;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.*;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.drools8.ruledata.Section07RuleUnit;
import io.github.aasaru.drools8.ruledata.StepRuleUnitUtil;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.drools.ruleunits.api.conf.RuleConfig;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VisaAddLogicalD8 {
  public static void main(final String[] args) {
    execute(Common.promptForStepD8(7, args, 1, 4));
  }

  static SessionData execute(int step) {
    System.out.println("Running step " + step);
    SessionData sessionData = new SessionData();

    Section07RuleUnit ruleUnit = new StepRuleUnitUtil<Section07RuleUnit>().getRuleUnit(Section07RuleUnit.class, 7, step);


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

    try (RuleUnitInstance<Section07RuleUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(ruleUnit, ruleConfig)) {
      passports.forEach(ruleUnit.getPassports()::append);
      visaApplications.forEach(ruleUnit.getVisaApplications()::append);

      System.out.println("==== DROOLS START ==== ");
      instance.fire();
      System.out.println("==== DROOLS END ==== ");


      if (step > 1 && step < 4) {
        System.out.println("==== VALID PASSPORTS FROM DROOLS SESSION === ");
        sessionData.validPassports = getAllValidPassportsUsingQuery(instance);
        sessionData.validPassports.forEach(System.out::println);

        System.out.println("==== INVALID PASSPORTS FROM DROOLS SESSION === ");
        sessionData.invalidPassports = getAllInvalidPassportsUsingQuery(instance);
        sessionData.invalidPassports.forEach(System.out::println);
      }

      if (step == 3) {
        System.out.println("==== VALID APPLICATIONS FROM DROOLS SESSION === ");
        sessionData.validVisaApplications = getAllValidVisaApplicationsUsingQuery(instance);
        sessionData.validVisaApplications.forEach(System.out::println);

        System.out.println("==== INVALID APPLICATIONS FROM DROOLS SESSION === ");
        sessionData.invalidVisaApplications = getAllInvalidVisaApplicationsUsingQuery(instance);
        sessionData.invalidVisaApplications.forEach(System.out::println);
      }

      if (step != 2) {
        sessionData.visas = getAllVisasUsingQuery(instance);
        System.out.println("== Visas from session == ");
        sessionData.visas.forEach(System.out::println);
      }

    }
    return sessionData;
  }

  public static List<Visa> getAllVisasUsingQuery(RuleUnitInstance<Section07RuleUnit> instance) {
    return instance.executeQuery("GetAllVisas")
      .toList().stream()
      .map(tuple -> (Visa) tuple.get("$allVisas"))
      .collect(Collectors.toList());
  }

  public static List<ValidPassport> getAllValidPassportsUsingQuery(RuleUnitInstance<Section07RuleUnit> instance) {
    return instance.executeQuery("GetAllValidPassports")
      .toList().stream()
      .map(tuple -> (ValidPassport) tuple.get("$allValidPassports"))
      .collect(Collectors.toList());
  }

  public static List<InvalidPassport> getAllInvalidPassportsUsingQuery(RuleUnitInstance<Section07RuleUnit> instance) {
    return instance.executeQuery("GetAllInvalidPassports")
      .toList().stream()
      .map(tuple -> (InvalidPassport) tuple.get("$allInvalidPassports"))
      .collect(Collectors.toList());
  }

  public static List<ValidVisaApplication> getAllValidVisaApplicationsUsingQuery(RuleUnitInstance<Section07RuleUnit> instance) {
    return instance.executeQuery("GetAllValidVisaApplications")
      .toList().stream()
      .map(tuple -> (ValidVisaApplication) tuple.get("$allValidVisaApplications"))
      .collect(Collectors.toList());
  }

  public static List<InvalidVisaApplication> getAllInvalidVisaApplicationsUsingQuery(RuleUnitInstance<Section07RuleUnit> instance) {
    return instance.executeQuery("GetAllValidVisaApplications")
      .toList().stream()
      .map(tuple -> (InvalidVisaApplication) tuple.get("$allInvalidVisaApplications"))
      .collect(Collectors.toList());
  }

  static class SessionData {
    Collection<Visa> visas;
    Collection<ValidPassport> validPassports;
    Collection<InvalidPassport> invalidPassports;
    Collection<ValidVisaApplication> validVisaApplications;
    Collection<InvalidVisaApplication> invalidVisaApplications;
  }

}
