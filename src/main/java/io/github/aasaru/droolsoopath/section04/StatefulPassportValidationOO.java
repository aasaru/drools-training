/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this work.
 *  If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.section04;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.droolsoopath.ruledata.PassportRuleUnit;
import io.github.aasaru.droolsoopath.ruledata.StepRuleUnitUtil;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import java.util.List;

public class StatefulPassportValidationOO {

  public static void main(final String[] args) {
    execute(Common.promptForStepOo(4, args, 1, 2));
  }

  static void execute(int step) {
    System.out.println("Running step " + step);
    List<Passport> passports = ApplicationRepository.getPassports();

    PassportRuleUnit ruleUnit = new StepRuleUnitUtil<PassportRuleUnit>().getRuleUnit(PassportRuleUnit.class, 4, step);

    try (RuleUnitInstance<PassportRuleUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(ruleUnit)) {
      for (Passport passport : passports) {
        ruleUnit.getPassports().append(passport);
      }
      System.out.println("==== DROOLS START ==== ");
      int firedRulesCount = instance.fire();
      System.out.printf("==== DROOLS FINISHED. %s RULES WERE FIRED ==== \n", firedRulesCount);
    }

    System.out.println("==== PASSPORTS AFTER RULES WERE FIRED === ");
    passports.forEach(passport -> System.out.println(passport + " verdict: " + passport.getValidation() + ", cause: " + passport.getCause()));
  }

}
