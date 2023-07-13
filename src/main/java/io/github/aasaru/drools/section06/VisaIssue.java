/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.section06;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.Visa;
import io.github.aasaru.drools.domain.VisaApplication;
import io.github.aasaru.drools.repository.ApplicationRepository;
import io.github.aasaru.drools.section03.PassportRuleUnit;
import io.github.aasaru.drools.section05.PassportVisaApplicationRuleUnit;
import org.drools.ruleunit.RuleUnitExecutor;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class VisaIssue {
  public static void main(final String[] args) {
    execute(Common.promptForStep(6, args, 1, 6));
  }

  static void execute(int step) {
    System.out.println("Running step " + step);
    Section06RuleUnit section06RuleUnit = null;

    KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
    KieSession ksession = kieContainer.newKieSession("VisaIssueStep" + step);

    ksession.addEventListener(new AgendaGroupEventListener(System.out));

    List<Passport> passports = ApplicationRepository.getPassports();

    if (step == 5) {
      if (Common.promptForYesNoQuestion("Do you want to set all passports as expired?")) {
        System.out.println("Setting all passports as expired before Drools session starts");
        passports.forEach(passport -> passport.setExpiresOn(LocalDate.MIN));
      }
    }
    List<VisaApplication> visaApplications = ApplicationRepository.getVisaApplications();


    if (step < 7) {
      passports.forEach(ksession::insert);
      visaApplications.forEach(ksession::insert);
    }
    else {


      KieBase kbase = kieContainer.getKieBase("section06.step"+step);


      RuleUnitExecutor executor = RuleUnitExecutor.create(ksession).bind(kbase);
      Agenda agenda = ksession.getAgenda();
      agenda.clear();
      agenda.getAgendaGroup("validate-passport").setFocus();





      section06RuleUnit = getRuleUnit(step, passports, visaApplications);
      // TODO insert passports like this? passportUnit.getPassports().insert((new Measurement("color", "green"));
      executor.run(section06RuleUnit);
    }



    if (step == 3) {
      Agenda agenda = ksession.getAgenda();
      agenda.getAgendaGroup("issue-visa").setFocus();
      agenda.getAgendaGroup("valid-application").setFocus();
      agenda.getAgendaGroup("invalid-application").setFocus();
      agenda.getAgendaGroup("valid-passport").setFocus();
      agenda.getAgendaGroup("invalid-passport").setFocus();
    }


    if (step == 4 || step == 5) {
      Agenda agenda = ksession.getAgenda();
      agenda.getAgendaGroup("issue-visa").setFocus();
      agenda.getAgendaGroup("validate-application").setFocus();
      agenda.getAgendaGroup("validate-passport").setFocus();
    }


    /** BONUS STEP: set focus to first agenda group only */
    if (step == 6) {
      Agenda agenda = ksession.getAgenda();
      agenda.getAgendaGroup("validate-passport").setFocus();
    }

    if (step < 7) {
      System.out.println("==== DROOLS SESSION START ==== ");
      ksession.fireAllRules();
      if (Common.disposeSession) {
        ksession.dispose();
      }
      System.out.println("==== DROOLS SESSION END ==== ");
      Collection<?> visaObjects = ksession.getObjects(o -> o.getClass() == Visa.class);
      System.out.println("== Visas from session == ");
      visaObjects.forEach(System.out::println);
    }
    else {
      System.out.println("== Visas from rule unit == ");
      section06RuleUnit.getVisas().forEach(System.out::println);
    }

  }

  private static Section06RuleUnit getRuleUnit(int step, List<Passport> passports, List<VisaApplication> visaApplications) {
    Section06RuleUnit section06RuleUnit = null;
    if (step == 7) {
      section06RuleUnit = new io.github.aasaru.drools.section06.step7.Section06RuleUnit(passports, visaApplications);
    }

    return section06RuleUnit;
  }


}
