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
    execute(5);
  }

  private static void execute(int step) {
    System.out.println("Running step " + step);
    KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("PassportValidationStep"+step);

    // Insert facts to the session
    final Passport canadianPassport = new Passport();
    canadianPassport.setExpiresOn(LocalDate.of(2047, Month.NOVEMBER, 25));
    canadianPassport.setPassportNumber("P111");
    canadianPassport.setCountryCode("CAN");
    canadianPassport.setUnusedVisaPages(1);
    ksession.insert(canadianPassport);

    final Passport kiwiPassport = new Passport();
    kiwiPassport.setExpiresOn(LocalDate.of(2016, Month.FEBRUARY, 11));
    kiwiPassport.setPassportNumber("P222");
    kiwiPassport.setCountryCode("NZL");
    kiwiPassport.setUnusedVisaPages(0);
    ksession.insert(kiwiPassport);

    final Passport aussiePassport = new Passport();
    aussiePassport.setExpiresOn(LocalDate.of(2048, Month.MARCH, 11));
    aussiePassport.setPassportNumber("P333");
    aussiePassport.setCountryCode("AUS");
    aussiePassport.setUnusedVisaPages(0);
    ksession.insert(aussiePassport);

    ksession.fireAllRules();
    ksession.dispose();

    if (step >= 4) {
      System.out.println("==== PASSPORT STATE AFTER DOOLS SESSION === ");
      System.out.println("Passport " + canadianPassport.getPassportNumber() + " passed validation: " + canadianPassport.validationPassed);
      System.out.println("Passport " + kiwiPassport.getPassportNumber() + " passed validation: " + kiwiPassport.validationPassed);
      System.out.println("Passport " + aussiePassport.getPassportNumber() + " passed validation: " + aussiePassport.validationPassed);
    }

  }

  public static class Passport {
    private String countryCode;
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

    public Boolean getValidationPassed() {
      return validationPassed;
    }

    public void setValidationPassed(Boolean validationPassed) {
      this.validationPassed = validationPassed;
    }

  }

}
