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

public class VisaApplication {
  private int applicationId;
  private String passportNumber;
  private LocalDate visitStartDate;
  private LocalDate visitEndDate;

  private Validation validation = Validation.UNKNOWN;

  public String getPassportNumber() {
    return passportNumber;
  }

  public LocalDate getVisitStartDate() {
    return visitStartDate;
  }

  public LocalDate getVisitEndDate() {
    return visitEndDate;
  }

  public Validation getValidation() {
    return validation;
  }

  public void setValidation(Validation validation) {
    this.validation = validation;
  }

  @Override
  public String toString() {
    return "VisaApplication(id:" + applicationId + ", passport:" + passportNumber + ")";
  }

  public static VisaApplicationBuilder newBuilder() {
    return new VisaApplicationBuilder();
  }

  public static final class VisaApplicationBuilder {
    private int applicationId;
    private String passportNumber;
    private LocalDate visitStartDate;
    private LocalDate visitEndDate;

    private VisaApplicationBuilder() {
    }

    public VisaApplicationBuilder withApplicationId(int applicationId) {
      this.applicationId = applicationId;
      return this;
    }

    public VisaApplicationBuilder withPassportNumber(String passportNumber) {
      this.passportNumber = passportNumber;
      return this;
    }

    public VisaApplicationBuilder withVisitStartDate(LocalDate visitStartDate) {
      this.visitStartDate = visitStartDate;
      return this;
    }

    public VisaApplicationBuilder withVisitEndDate(LocalDate visitEndDate) {
      this.visitEndDate = visitEndDate;
      return this;
    }

    public VisaApplication build() {
      VisaApplication visaApplication = new VisaApplication();
      visaApplication.applicationId = applicationId;
      visaApplication.passportNumber = passportNumber;
      visaApplication.visitStartDate = visitStartDate;
      visaApplication.visitEndDate = visitEndDate;
      return visaApplication;
    }
  }
}
