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

public class ValidPassport {
  private Passport passport;

  public ValidPassport(Passport passport) {
    this.passport = passport;
  }

  public Passport getPassport() {
    return passport;
  }

  @Override
  public String toString() {
    return "Valid" + passport;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof ValidPassport
        && ((ValidPassport) obj).getPassport().equals(passport);
  }

  @Override
  public int hashCode() {
    return passport.hashCode();
  }
}
