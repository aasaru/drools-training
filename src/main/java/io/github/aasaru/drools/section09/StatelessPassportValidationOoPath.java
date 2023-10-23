/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.section09;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.drools.section09.step1.MyRuleUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import java.util.List;

public class StatelessPassportValidationOoPath {
  public static void main(final String[] args) {
    execute(Common.promptForStep(9, args, 1, 6));
  }

  static void execute(int step) {

    List<Passport> passports = ApplicationRepository.getPassports();

    System.out.println("Running step " + step);

    MyRuleUnit measurementUnit = new MyRuleUnit();
    RuleUnitInstance<MyRuleUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(measurementUnit);

    System.out.println("==== DROOLS SESSION START ==== ");
    try {
      passports.forEach(measurementUnit.getPassports()::add);

      instance.fire();
    } finally {
      //instance.dispose();
    }
    System.out.println("==== DROOLS SESSION END ==== ");

    if (step >= 4) {
      System.out.println("==== PASSPORTS AFTER DROOLS SESSION ==== ");

      passports.forEach(passport -> System.out.println(passport + " validation " + passport.getValidation()));

    }

  }

}
