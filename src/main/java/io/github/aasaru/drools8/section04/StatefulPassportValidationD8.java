package io.github.aasaru.drools8.section04;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.drools8.ruledata.PassportRuleUnit;
import io.github.aasaru.drools8.ruledata.StepRuleUnitUtil;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import java.util.List;

public class StatefulPassportValidationD8 {

  public static void main(final String[] args) {
    execute(Common.promptForStep(4, args, 1, 2));
  }

  static void execute(int step) {
    System.out.println("Running step " + step);
    List<Passport> passports = ApplicationRepository.getPassports();

    PassportRuleUnit ruleUnit = new StepRuleUnitUtil<PassportRuleUnit>().getRuleUnit(PassportRuleUnit.class, 4, step);

    try (RuleUnitInstance<PassportRuleUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(ruleUnit)) {
      // TODO move out of try?
      passports.forEach(ruleUnit.getPassports()::add);
      System.out.println("==== DROOLS SESSION START ==== ");
      instance.fire();
      System.out.println("==== DROOLS SESSION END ==== ");
    }

    System.out.println("==== PASSPORTS AFTER DROOLS SESSION === ");
    passports.forEach(passport -> {
      System.out.println(passport + " verdict: " + passport.getValidation() + ", cause: " + passport.getCause());
    });
  }

}
