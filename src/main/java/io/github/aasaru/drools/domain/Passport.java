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

package io.github.aasaru.drools.domain;

import java.time.LocalDate;

public class Passport {
  private String passportNumber;
  private String name;
  private LocalDate expiresOn;
  private int unusedVisaPages;

  private Boolean validationPassed = null;

  private Passport() {
  }

  public boolean isExpired() {
    return expiresOn.isBefore(LocalDate.now());
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  public String getName() {
    return name;
  }

  public int getUnusedVisaPages() {
    return unusedVisaPages;
  }

  public Boolean getValidationPassed() {
    return validationPassed;
  }

  public void setValidationPassed(Boolean validationPassed) {
    this.validationPassed = validationPassed;
  }

  @Override
  public String toString() {
    return String.format("Passport[%s, %s]", passportNumber, name);
  }

  public static PassportBuilder newBuilder() {
    return new PassportBuilder();
  }

  public static final class PassportBuilder {
    private String passportNumber;
    private String name;
    private LocalDate expiresOn;
    private int unusedVisaPages;

    private PassportBuilder() {
    }

    public PassportBuilder withPassportNumber(String passportNumber) {
      this.passportNumber = passportNumber;
      return this;
    }

    public PassportBuilder withName(String name) {
      this.name = name;
      return this;
    }

    public PassportBuilder withExpiresOn(LocalDate expiresOn) {
      this.expiresOn = expiresOn;
      return this;
    }

    public PassportBuilder withUnusedVisaPages(int unusedVisaPages) {
      this.unusedVisaPages = unusedVisaPages;
      return this;
    }

    public Passport build() {
      Passport passport = new Passport();
      passport.passportNumber = passportNumber;
      passport.name = name;
      passport.expiresOn = expiresOn;
      passport.unusedVisaPages = unusedVisaPages;
      return passport;
    }
  }
}
