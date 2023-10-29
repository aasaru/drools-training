/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Passport {
  private String passportNumber;
  private String name;
  private LocalDate expiresOn;
  private int unusedVisaPages;
  private int age;

  private Validation validation = Validation.UNKNOWN;

  private String cause = "";

  private Passport() {
  }

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

  public String getName() {
    return name;
  }

  public int getUnusedVisaPages() {
    return unusedVisaPages;
  }

  public Validation getValidation() {
    return validation;
  }

  public void setValidation(Validation validation) {
    this.validation = validation;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getCause() {
    return cause;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }

  @Override
  public String toString() {
    return String.format("Passport[no:%s, name:%s]", passportNumber, name);
  }

  public static PassportBuilder newBuilder() {
    return new PassportBuilder();
  }

   public static final class PassportBuilder {
    private String passportNumber;
    private String name;
    private LocalDate expiresOn;
    private int unusedVisaPages;
    private int age;

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

    public PassportBuilder withAge(int age) {
      this.age = age;
      return this;
    }

    public Passport build() {
      Passport passport = new Passport();
      passport.passportNumber = passportNumber;
      passport.name = name;
      passport.expiresOn = expiresOn;
      passport.unusedVisaPages = unusedVisaPages;
      passport.age = age;
      return passport;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Passport passport = (Passport) o;
    return Objects.equals(passportNumber, passport.passportNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(passportNumber);
  }

}
