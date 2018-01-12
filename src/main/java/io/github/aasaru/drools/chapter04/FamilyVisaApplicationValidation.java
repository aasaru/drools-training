/*
 * Copyright 2017 Juhan Aasaru.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.aasaru.drools.chapter04;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.FamilyVisaApplication;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.repository.ApplicationRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import java.util.List;

public class FamilyVisaApplicationValidation {

  public static final int MIN_STEP = 0;
  public static final int MAX_STEP = 4;

  public static void main(final String[] args) {
    execute(Common.promptForStep(args, MIN_STEP, MAX_STEP));
  }


  static void execute(int step) {
    System.out.println("Running step " + step);
    KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("FamilyVisaApplicationStep" + step);

    List<Passport> passports = ApplicationRepository.getPassports();
    passports.forEach(ksession::insert);

    List<FamilyVisaApplication> familyVisaApplications = ApplicationRepository.getFamilyVisaApplications();
    familyVisaApplications.forEach(ksession::insert);

    ksession.fireAllRules();
    ksession.dispose();

    // if (!step.equals("1") && !step.equals("Solution")) {
    System.out.println("==== FAMILY VISA APPLICATIONS STATE AFTER DOOLS SESSION === ");

    familyVisaApplications.forEach(visaApplication -> System.out.println(visaApplication + " passed validation: " + visaApplication.getValidationPassed()));

    // }


  }


}
