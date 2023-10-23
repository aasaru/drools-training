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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyVisaApplication {
  private final int applicationId;
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

  @Override
  public boolean equals(Object obj) {
    return obj instanceof FamilyVisaApplication
        && ((FamilyVisaApplication) obj).getApplicationId() == applicationId;
  }

  @Override
  public int hashCode() {
    return Long.valueOf(applicationId).hashCode();
  }

}
