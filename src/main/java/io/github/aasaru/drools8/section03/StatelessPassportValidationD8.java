/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools8.section03;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.drools8.ruledata.PassportRuleUnit;
import io.github.aasaru.drools8.ruledata.StepRuleUnitUtil;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import java.util.List;

public class StatelessPassportValidationD8 {
    public static void main(final String[] args) {
        execute(Common.promptForStep(3, args, 1, 6));
    }

    static void execute(int step) {
        List<Passport> passports = ApplicationRepository.getPassports();

        System.out.println("Running step " + step);

        PassportRuleUnit ruleUnit = new StepRuleUnitUtil<PassportRuleUnit>().getRuleUnit(PassportRuleUnit.class, 3, step);

        try (RuleUnitInstance<PassportRuleUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(ruleUnit)) {

            passports.forEach(ruleUnit.getPassports()::add);

            System.out.println("==== DROOLS SESSION START ==== ");
            instance.fire();
            System.out.println("==== DROOLS SESSION END ==== ");
        }

        if (step >= 4) {
            System.out.println("==== PASSPORTS AFTER DROOLS SESSION ==== ");

            passports.forEach(passport -> System.out.println(passport + " validation " + passport.getValidation()));
        }

    }

}
