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
    return "VisaApplication(#" + applicationId + ", pass:" + passportNumber + ")";
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof VisaApplication)) return false;
    VisaApplication that = (VisaApplication) o;
    return applicationId == that.applicationId &&
        Objects.equals(passportNumber, that.passportNumber) &&
        Objects.equals(visitStartDate, that.visitStartDate) &&
        Objects.equals(visitEndDate, that.visitEndDate) &&
        validation == that.validation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId, passportNumber, visitStartDate, visitEndDate, validation);
  }
}
