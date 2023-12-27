/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.section06;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.Visa;
import io.github.aasaru.drools.domain.VisaApplication;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.droolsoopath.ruledata.StepRuleUnitUtil;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VisaIssueOO {
  public static void main(final String[] args) {
    execute(Common.promptForStepOo(6, args, 1, 3));
  }

  static Collection<Visa> execute(int step) {
    System.out.println("Running step " + step);

    RuleUnitSection06 ruleUnit = new StepRuleUnitUtil<RuleUnitSection06>()
      .getRuleUnit(RuleUnitSection06.class, 6, step);

    List<Passport> passports = ApplicationRepository.getPassports();

    List<VisaApplication> visaApplications = ApplicationRepository.getVisaApplications();

    try (RuleUnitInstance<RuleUnitSection06> instance = RuleUnitProvider.get().createRuleUnitInstance(ruleUnit)) {
      passports.forEach(ruleUnit.getPassports()::append);
      visaApplications.forEach(ruleUnit.getVisaApplications()::append);

      System.out.println("==== DROOLS START ==== ");
      instance.fire();
      System.out.println("==== DROOLS END ==== ");

      Collection<Visa> visaObjects = getAllVisasUsingQuery(instance);
      System.out.println("== VISAS AFTER RULES WERE FIRED == ");
      visaObjects.forEach(System.out::println);

      return visaObjects;
    }

  }

  public static List<Visa> getAllVisasUsingQuery(RuleUnitInstance<RuleUnitSection06> instance) {
    return instance.executeQuery("GetAllVisas")
      .toList().stream()
      .map(tuple -> (Visa) tuple.get("$allVisas"))
      .collect(Collectors.toList());
  }

}
