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

package io.github.aasaru.drools.chapter01;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import java.time.LocalDate;
import java.time.Month;

public class PassportValidation {

  public static void main(final String[] args) {
    KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("PassportValidationKS");

    // Insert facts to the session
    final Passport canadianPassport = new Passport();
    canadianPassport.setExpiresOn(LocalDate.of(2047, Month.NOVEMBER, 25));
    canadianPassport.setPassportNumber("P1111111");
    canadianPassport.setCountryCode("CAN");
    ksession.insert(canadianPassport);

    final Passport kiwiPassport = new Passport();
    kiwiPassport.setExpiresOn(LocalDate.of(2016, Month.FEBRUARY, 11));
    kiwiPassport.setPassportNumber("P2222");
    kiwiPassport.setCountryCode("NZL");
    ksession.insert(kiwiPassport);

    final Passport aussiePassport = new Passport();
    aussiePassport.setExpiresOn(LocalDate.of(2048, Month.MARCH, 11));
    aussiePassport.setPassportNumber("P33333");
    aussiePassport.setCountryCode("AUS");
    ksession.insert(aussiePassport);

    ksession.fireAllRules();

    ksession.dispose();
  }

  public static class Passport {
    private String countryCode;
    private String passportNumber;
    private LocalDate expiresOn;
    private int unusedVisaPages;

    private boolean validationPassed;

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

    public String getCountryCode() {
      return countryCode;
    }

    public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
    }

    public int getUnusedVisaPages() {
      return unusedVisaPages;
    }

    public void setUnusedVisaPages(int unusedVisaPages) {
      this.unusedVisaPages = unusedVisaPages;
    }

    public boolean isValidationPassed() {
      return validationPassed;
    }

    public void setValidationPassed(boolean validationPassed) {
      this.validationPassed = validationPassed;
    }

  }

}
