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
    KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("VisaApplicationKS");

    // Insert facts to session
    final Passport passport = new Passport();
    passport.setExpiresOn(LocalDate.of(2027, Month.NOVEMBER, 25));
    passport.setPassportNumber("C311958551");
    passport.setCountryCode("USA");
    ksession.insert(passport);

    final VisaApplication application1 = new VisaApplication();
    application1.setPassportNumber(passport.getPassportNumber());
    application1.setVisitStartDate(LocalDate.of(2025, Month.MAY, 10));
    application1.setVisitEndDate(LocalDate.of(2025, Month.MAY, 14));
    ksession.insert(application1);

    final Passport passport2 = new Passport();
    passport2.setExpiresOn(LocalDate.of(2012, Month.FEBRUARY, 11));
    passport2.setPassportNumber("C445112334");
    passport2.setCountryCode("USA");
    ksession.insert(passport2);

    ksession.fireAllRules();

    ksession.dispose();
  }

  public static class VisaApplication {
    private String passportNumber;
    private LocalDate visitStartDate;
    private LocalDate visitEndDate;


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
  }

  public static class Passport {
    private String passportNumber;
    private LocalDate expiresOn;
    private String countryCode;

    private Passport() { }

    public LocalDate getExpiresOn() {
      return expiresOn;
    }

    public void setExpiresOn(LocalDate expiresOn) {
      this.expiresOn = expiresOn;
    }

    public String getPassportNumber() {
      return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
      this.passportNumber = passportNumber;
    }

    public String getCountryCode() {
      return countryCode;
    }

    public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
    }

    public boolean isValid() {
      return this.expiresOn.isAfter(LocalDate.now());
    }
  }

}
