/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools8.section05;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.VisaApplication;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.drools8.ruledata.PassportVisaApplicationRuleUnit;
import io.github.aasaru.drools8.ruledata.StepRuleUnitUtil;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import java.util.List;

public class VisaApplicationValidationD8 {
  public static void main(final String[] args) {
    execute(Common.promptForStep(5, args, 1, 3));
  }


  static SessionData execute(int step) {
    System.out.println("Running step " + step);

    PassportVisaApplicationRuleUnit ruleUnit = new StepRuleUnitUtil<PassportVisaApplicationRuleUnit>()
      .getRuleUnit(PassportVisaApplicationRuleUnit.class, 5, step);

    SessionData sessionData = new SessionData();
    sessionData.passports = ApplicationRepository.getPassports();
    sessionData.visaApplications =  ApplicationRepository.getVisaApplications();

    try (RuleUnitInstance<PassportVisaApplicationRuleUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(ruleUnit)) {
      sessionData.passports.forEach(ruleUnit.getPassports()::append);
      sessionData.visaApplications.forEach(ruleUnit.getVisaApplications()::append);

      System.out.println("==== DROOLS START ==== ");
      instance.fire();
      System.out.println("==== DROOLS END ==== ");
      System.out.println("==== PASSPORTS AFTER RUNNING RULES === ");
      sessionData.passports.forEach(passport -> System.out.println(passport + " verdict: " + passport.getValidation()));

      System.out.println("==== APPLICATIONS STATE AFTER DROOLS SESSION === ");
      sessionData.visaApplications.forEach(visaApplication ->
        System.out.println(visaApplication + " verdict: " + visaApplication.getValidation())
      );
    }

    return sessionData;
  }

  static class SessionData {
    List<Passport> passports;
    List<VisaApplication> visaApplications;
  }

}
