/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */
package io.github.aasaru.droolsoopath.section08;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.FamilyVisaApplication;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.SessionData;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.droolsoopath.query.SessionQueryUtil;
import io.github.aasaru.droolsoopath.ruledata.StepRuleUnitUtil;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import java.util.List;

public class FamilyVisaApplicationValidationOO {
  public static SessionQueryUtil<RuleUnitSection08> sessionQueryUtil = new SessionQueryUtil<>();

  public static void main(final String[] args) {
    execute(Common.promptForStepOo(8, args, 1, 5));
  }

  static SessionData execute(int step) {
    System.out.println("Running step " + step);
    SessionData sessionData = new SessionData();

    RuleUnitSection08 ruleUnit = new StepRuleUnitUtil<RuleUnitSection08>().getRuleUnit(RuleUnitSection08.class, 8, step);

    List<Passport> passports = ApplicationRepository.getPassports();
    if (step == 3) {
      boolean isYounger = Common.promptForYesNoQuestion("Do you want to make everyone 3 years younger?");
      if (isYounger) {
        System.out.println("Making everyone 3 years younger");
        passports.forEach(passport -> passport.setAge(passport.getAge()-3));
        passports.forEach(passport -> System.out.println(passport + " is now " + passport.getAge()));
      }
    }

    List<FamilyVisaApplication> familyVisaApplications = ApplicationRepository.getFamilyVisaApplications();

    try (RuleUnitInstance<RuleUnitSection08> instance = RuleUnitProvider.get().createRuleUnitInstance(ruleUnit)) {
      passports.forEach(ruleUnit.getPassports()::append);
      familyVisaApplications.forEach(ruleUnit.getFamilyVisaApplications()::append);

      System.out.println("==== DROOLS SESSION START ==== ");
      instance.fire();
      System.out.println("==== DROOLS SESSION END ==== ");

      System.out.println("==== INVALID FAMILY VISA APPLICATIONS FROM RULE UNIT === ");
      sessionData.invalidFamilyVisaApplications = sessionQueryUtil.getAllInvalidFamilyVisaApplicationsUsingQuery(instance);
      sessionData.invalidFamilyVisaApplications.forEach(System.out::println);

      System.out.println("== Visas from rule unit == ");
      sessionData.visas = sessionQueryUtil.getAllVisasUsingQuery(instance);
      sessionData.visas.forEach(System.out::println);

      System.out.println("== Group leaders from rule unit == ");
      sessionData.groupLeaders = sessionQueryUtil.getAllGroupLeadersUsingQuery(instance);
      sessionData.groupLeaders.forEach(System.out::println);
    }

    return sessionData;
  }

}
