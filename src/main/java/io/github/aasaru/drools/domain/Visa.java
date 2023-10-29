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

import java.util.Objects;

public class Visa {
  private String passportNumber;

  public Visa(String passportNumber) {
    this.passportNumber = passportNumber;
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  @Override
  public String toString() {
    return "Visa[passport:" + passportNumber + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Visa visa = (Visa) o;
    return Objects.equals(passportNumber, visa.passportNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(passportNumber);
  }

}
