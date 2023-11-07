/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */
package io.github.aasaru.drools8.section08;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.*;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.drools8.ruledata.StepRuleUnitUtil;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import java.util.List;

public class FamilyVisaApplicationValidationD8 {
  public static void main(final String[] args) {
    execute(Common.promptForStep(8, args, 1, 5));
  }

  static void execute(int step) {
    System.out.println("Running step " + step);

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

      System.out.println("==== INVALID FAMILY VISA APPLICATIONS FROM DROOLS SESSION === ");
      /*
      Collection<?> invalidApplications = ksession.getObjects(o -> o.getClass() == InvalidFamilyVisaApplication.class);
      invalidApplications.forEach(System.out::println);

      Collection<?> visas = ksession.getObjects(o -> o.getClass() == Visa.class);
      System.out.println("== Visas from session == ");
      visas.forEach(System.out::println);

      Collection<?> groupLeaders = ksession.getObjects(o -> o.getClass() == GroupLeader.class);
      System.out.println("== Group leaders from session == ");
      groupLeaders.forEach(System.out::println);


       */
    }

  }

}
