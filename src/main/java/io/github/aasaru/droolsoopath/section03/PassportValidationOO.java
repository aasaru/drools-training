/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.section03;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.droolsoopath.ruledata.PassportRuleUnit;
import io.github.aasaru.droolsoopath.ruledata.StepRuleUnitUtil;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import java.util.List;

public class PassportValidationOO {
    public static void main(final String[] args) {
        execute(Common.promptForStepOo(3, args, 1, 6));
    }

    static void execute(int step) {
        List<Passport> passports = ApplicationRepository.getPassports();

        System.out.println("Running step " + step);

        PassportRuleUnit ruleUnit = new StepRuleUnitUtil<PassportRuleUnit>().getRuleUnit(PassportRuleUnit.class, 3, step);

        try (RuleUnitInstance<PassportRuleUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(ruleUnit)) {

            passports.forEach(ruleUnit.getPassports()::append);

            System.out.println("==== DROOLS START ==== ");
            instance.fire();

            /* An idea to try:
            ruleUnit.getPassports().append(Passport.newBuilder()
              .withPassportNumber("ABC123")
                .withExpiresOn(LocalDate.of(2200,12,12))
                .withUnusedVisaPages(3)
              .build());
            instance.fire();*/

            System.out.println("==== DROOLS END ==== ");
        }

      if (step >= 4) {
            System.out.println("==== PASSPORTS AFTER RULES WERE FIRED ==== ");

            passports.forEach(passport -> System.out.println(passport + " validation " + passport.getValidation()));
        }

    }

}
