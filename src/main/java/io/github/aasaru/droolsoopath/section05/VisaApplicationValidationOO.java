/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.section05;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.SessionData;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.droolsoopath.ruledata.StepRuleUnitUtil;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

public class VisaApplicationValidationOO {
  public static void main(final String[] args) {
    execute(Common.promptForStepOo(5, args, 1, 3));
  }


  static SessionData execute(int step) {
    System.out.println("Running step " + step);

    RuleUnitSection05 ruleUnit = new StepRuleUnitUtil<RuleUnitSection05>()
      .getRuleUnit(RuleUnitSection05.class, 5, step);

    SessionData sessionData = new SessionData();
    sessionData.passports = ApplicationRepository.getPassports();
    sessionData.visaApplications =  ApplicationRepository.getVisaApplications();

    try (RuleUnitInstance<RuleUnitSection05> instance = RuleUnitProvider.get().createRuleUnitInstance(ruleUnit)) {
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

}
