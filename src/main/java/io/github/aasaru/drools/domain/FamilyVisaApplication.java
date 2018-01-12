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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyVisaApplication {
  private int applicationId;
  private List<String> passportNumbers = new ArrayList<>();
  private LocalDate visitStartDate;
  private LocalDate visitEndDate;

  private Boolean validationPassed = null;

  public FamilyVisaApplication(int applicationId) {
    this.applicationId = applicationId;
  }

  public int getApplicationId() {
    return applicationId;
  }

  public List<String> getPassportNumbers() {
    return passportNumbers;
  }

  public LocalDate getVisitStartDate() {
    return visitStartDate;
  }

  public LocalDate getVisitEndDate() {
    return visitEndDate;
  }

  public Boolean getValidationPassed() {
    return validationPassed;
  }

  public void setValidationPassed(Boolean validationPassed) {
    this.validationPassed = validationPassed;
  }

  public static String join(Collection<String> collection) {
    return collection.stream()
      .map(Object::toString)
      .collect(Collectors.joining(","));
  }

  @Override
  public String toString() {
    return String.format("FamilyVisaApplication[#%d, %s]", applicationId, join(passportNumbers));
  }

  public static FamilyVisaApplicationBuilder newBuilder() {
    return new FamilyVisaApplicationBuilder();
  }


  public static final class FamilyVisaApplicationBuilder {
    private int applicationId;
    private List<String> passportNumbers = new ArrayList<>();
    private LocalDate visitStartDate;
    private LocalDate visitEndDate;

    private FamilyVisaApplicationBuilder() {
    }


    public FamilyVisaApplicationBuilder withApplicationId(int applicationId) {
      this.applicationId = applicationId;
      return this;
    }

    public FamilyVisaApplicationBuilder withPassportNumbers(List<String> passportNumbers) {
      this.passportNumbers = passportNumbers;
      return this;
    }

    public FamilyVisaApplicationBuilder withVisitStartDate(LocalDate visitStartDate) {
      this.visitStartDate = visitStartDate;
      return this;
    }

    public FamilyVisaApplicationBuilder withVisitEndDate(LocalDate visitEndDate) {
      this.visitEndDate = visitEndDate;
      return this;
    }

    public FamilyVisaApplication build() {
      FamilyVisaApplication familyVisaApplication = new FamilyVisaApplication(applicationId);
      familyVisaApplication.visitEndDate = this.visitEndDate;
      familyVisaApplication.passportNumbers = this.passportNumbers;
      familyVisaApplication.visitStartDate = this.visitStartDate;
      return familyVisaApplication;
    }
  }
}
