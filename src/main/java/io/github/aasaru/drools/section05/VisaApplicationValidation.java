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

package io.github.aasaru.drools.section05;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.VisaApplication;
import io.github.aasaru.drools.repository.ApplicationRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.List;

public class VisaApplicationValidation {
  public static void main(final String[] args) {
    execute(Common.promptForStep(3, args, 1, 3));
  }


  static void execute(int step) {
    System.out.println("Running step " + step);
    KieContainer kieClasspathContainer = KieServices.Factory.get().getKieClasspathContainer();
    KieSession ksession = kieClasspathContainer.newKieSession("VisaApplicationStep" + step);

    List<Passport> passports = ApplicationRepository.getPassports();
    passports.forEach(ksession::insert);

    List<VisaApplication> visaApplications = ApplicationRepository.getVisaApplications();
    visaApplications.forEach(ksession::insert);

    System.out.println("==== DROOLS SESSION START ==== ");
    ksession.fireAllRules();
    ksession.dispose();
    System.out.println("==== DROOLS SESSION END ==== ");

    System.out.println("==== PASSPORTS AFTER DROOLS SESSION === ");
    passports.forEach(passport -> System.out.println(passport + " verdict: " + passport.getValidation()));

    System.out.println("==== APPLICATIONS STATE AFTER DROOLS SESSION === ");
    visaApplications.forEach(visaApplication ->
        System.out.println(visaApplication + " verdict: " + visaApplication.getValidation())
    );


  }

}
