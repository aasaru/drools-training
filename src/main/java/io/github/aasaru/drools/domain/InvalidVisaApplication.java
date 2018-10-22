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

public class InvalidVisaApplication {
  private VisaApplication visaApplication;

  public InvalidVisaApplication(VisaApplication visaApplication) {
    this.visaApplication = visaApplication;
  }

  public VisaApplication getVisaApplication() {
    return visaApplication;
  }

  @Override
  public String toString() {
    return "Invalid" + visaApplication;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof InvalidVisaApplication
        && ((InvalidVisaApplication) obj).getVisaApplication().equals(visaApplication);
  }

  @Override
  public int hashCode() {
    return visaApplication.hashCode();
  }

}
