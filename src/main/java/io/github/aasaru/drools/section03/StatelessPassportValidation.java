/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.section03;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.repository.ApplicationRepository;
import org.drools.ruleunit.RuleUnitExecutor;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.List;

public class StatelessPassportValidation {
  public static void main(final String[] args) {
    execute(Common.promptForStep(3, args, 1, 8));
  }

  static void execute(int step) {
    System.out.println("Running step " + step);

    List<Passport> passports = ApplicationRepository.getPassports();
    KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();

    if (step < 7) {
      StatelessKieSession kieSession = kieContainer.newStatelessKieSession("StatelessPassportValidationStep" + step);
      System.out.println("==== DROOLS SESSION START ==== ");
      kieSession.execute(passports);
      System.out.println("==== DROOLS SESSION END ==== ");
    }
    else {
      KieBase kbase = kieContainer.getKieBase("section03.step"+step);
      RuleUnitExecutor executor = RuleUnitExecutor.create().bind(kbase);
      PassportUnit passportUnit = getRuleUnit(step, passports);
      executor.run(passportUnit);
    }

    if (step >= 4) {
      System.out.println("==== PASSPORTS AFTER DROOLS SESSION ==== ");

      passports.forEach(passport -> System.out.println(passport + " validation " + passport.getValidation()));

    }

  }

  private static PassportUnit getRuleUnit(int step, List<Passport> passports) {
    PassportUnit passportUnit = null;
    if (step == 7) {
      passportUnit = new io.github.aasaru.drools.section03.step7.PassportUnit(passports);
    }
    else if (step == 8) {
      passportUnit = new io.github.aasaru.drools.section03.step8.PassportUnit(passports);
    }
    return passportUnit;
  }

}
