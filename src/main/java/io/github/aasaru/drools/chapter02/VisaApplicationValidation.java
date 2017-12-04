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

package io.github.aasaru.drools.chapter02;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import java.time.LocalDate;
import java.time.Month;

public class VisaApplicationValidation {
  public static void main(final String[] args) {
    execute("4");
  }


  private static void execute(String step) {
    System.out.println("Running step " + step);
    KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("VisaApplicationStep" + step);

    // Insert facts to the session
    final Passport canadianPassport = new Passport();
    canadianPassport.setExpiresOn(LocalDate.of(2047, Month.NOVEMBER, 25));
    canadianPassport.setPassportNumber("C111");
    canadianPassport.setUnusedVisaPages(1);
    ksession.insert(canadianPassport);

    final Passport kiwiPassport = new Passport();
    kiwiPassport.setExpiresOn(LocalDate.of(2016, Month.FEBRUARY, 11));
    kiwiPassport.setPassportNumber("K222");
    kiwiPassport.setUnusedVisaPages(0);
    ksession.insert(kiwiPassport);

    final Passport aussiePassport = new Passport();
    aussiePassport.setExpiresOn(LocalDate.of(2048, Month.MARCH, 11));
    aussiePassport.setPassportNumber("A333");
    aussiePassport.setUnusedVisaPages(0);
    ksession.insert(aussiePassport);

    final Passport britonPassport = new Passport();
    britonPassport.setExpiresOn(LocalDate.of(2045, Month.APRIL, 20));
    britonPassport.setPassportNumber("B333");
    britonPassport.setUnusedVisaPages(10);
    ksession.insert(britonPassport);


    final VisaApplication canadianApplication = new VisaApplication();
    canadianApplication.setPassportNumber(canadianPassport.getPassportNumber());
    canadianApplication.setVisitStartDate(LocalDate.of(2025, Month.MAY, 10));
    canadianApplication.setVisitEndDate(LocalDate.of(2025, Month.MAY, 14));
    ksession.insert(canadianApplication);

    final VisaApplication kiwiApplication = new VisaApplication();
    kiwiApplication.setPassportNumber(kiwiPassport.getPassportNumber());
    kiwiApplication.setVisitStartDate(LocalDate.of(2029, Month.MAY, 1));
    kiwiApplication.setVisitEndDate(LocalDate.of(2029, Month.JANUARY, 14));
    ksession.insert(kiwiApplication);

    final VisaApplication aussieApplication = new VisaApplication();
    aussieApplication.setPassportNumber(aussiePassport.getPassportNumber());
    aussieApplication.setVisitStartDate(LocalDate.of(2035, Month.JANUARY, 1));
    aussieApplication.setVisitEndDate(LocalDate.of(2035, Month.MAY, 31));
    ksession.insert(aussieApplication);

    final VisaApplication britonApplication = new VisaApplication();
    britonApplication.setPassportNumber(britonPassport.getPassportNumber());
    britonApplication.setVisitStartDate(LocalDate.of(2017, Month.SEPTEMBER, 1));
    britonApplication.setVisitEndDate(LocalDate.of(2025, Month.JANUARY, 14));
    ksession.insert(britonApplication);

    ksession.fireAllRules();
    ksession.dispose();

    if (!step.equals("1") && !step.equals("Solution")) {
      System.out.println("==== APPLICATIONS STATE AFTER DOOLS SESSION === ");
      System.out.println("Passport " + canadianApplication.getPassportNumber() + " passed validation: " + canadianApplication.validationPassed);
      System.out.println("Passport " + kiwiApplication.getPassportNumber() + " passed validation: " + kiwiApplication.validationPassed);
      System.out.println("Passport " + aussieApplication.getPassportNumber() + " passed validation: " + aussieApplication.validationPassed);
      System.out.println("Passport " + britonApplication.getPassportNumber() + " passed validation: " + britonApplication.validationPassed);
    }


  }

  public static class VisaApplication {
    private String passportNumber;
    private LocalDate visitStartDate;
    private LocalDate visitEndDate;

    private Boolean validationPassed = null;


    public String getPassportNumber() {
      return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
      this.passportNumber = passportNumber;
    }

    public LocalDate getVisitStartDate() {
      return visitStartDate;
    }

    public void setVisitStartDate(LocalDate visitStartDate) {
      this.visitStartDate = visitStartDate;
    }

    public LocalDate getVisitEndDate() {
      return visitEndDate;
    }

    public void setVisitEndDate(LocalDate visitEndDate) {
      this.visitEndDate = visitEndDate;
    }

    public Boolean getValidationPassed() {
      return validationPassed;
    }

    public void setValidationPassed(Boolean validationPassed) {
      this.validationPassed = validationPassed;
    }
  }


  public static class Passport {
    private String passportNumber;
    private LocalDate expiresOn;
    private int unusedVisaPages;

    private Boolean validationPassed = null;

    private Passport() { }

    public LocalDate getExpiresOn() {
      return expiresOn;
    }

    public void setExpiresOn(LocalDate expiresOn) {
      this.expiresOn = expiresOn;
    }

    public boolean isExpired() {
      return expiresOn.isBefore(LocalDate.now());
    }

    public String getPassportNumber() {
      return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
      this.passportNumber = passportNumber;
    }

    public int getUnusedVisaPages() {
      return unusedVisaPages;
    }

    public void setUnusedVisaPages(int unusedVisaPages) {
      this.unusedVisaPages = unusedVisaPages;
    }

    public Boolean getValidationPassed() {
      return validationPassed;
    }

    public void setValidationPassed(Boolean validationPassed) {
      this.validationPassed = validationPassed;
    }

  }


}
