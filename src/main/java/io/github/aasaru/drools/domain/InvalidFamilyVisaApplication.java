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

public class InvalidFamilyVisaApplication {
  private FamilyVisaApplication familyVisaApplication;

  public InvalidFamilyVisaApplication(FamilyVisaApplication familyVisaApplication) {
    this.familyVisaApplication = familyVisaApplication;
  }

  public FamilyVisaApplication getFamilyVisaApplication() {
    return familyVisaApplication;
  }

  @Override
  public String toString() {
    return "Invalid:" + familyVisaApplication;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof InvalidFamilyVisaApplication
        && ((InvalidFamilyVisaApplication) obj).getFamilyVisaApplication().equals(familyVisaApplication);
  }

  @Override
  public int hashCode() {
    return familyVisaApplication.hashCode();
  }

}
