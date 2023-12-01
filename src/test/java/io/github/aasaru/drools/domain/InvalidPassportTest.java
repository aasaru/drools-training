/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this work.
 *  If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class InvalidPassportTest {

  @Test
  void testEquals() {

    Passport passport1 = Passport.newBuilder()
      .withPassportNumber("ABC123")
      .withName("Abc 123")
      .withUnusedVisaPages(1)
      .withExpiresOn(LocalDate.of(2017, Month.DECEMBER, 17))
      .withAge(50)
      .build();

    Passport passport1_sameNumber = Passport.newBuilder()
      .withPassportNumber("ABC123")
      .build();

    Passport passport2 = Passport.newBuilder()
      .withPassportNumber("CDE456")
      .withName("Abc 123")
      .withUnusedVisaPages(1)
      .withExpiresOn(LocalDate.of(2017, Month.DECEMBER, 17))
      .withAge(50)
      .build();

    InvalidPassport validPassport1 = new InvalidPassport(passport1);
    InvalidPassport validPassport1b = new InvalidPassport(passport1_sameNumber);
    InvalidPassport validPassport2 = new InvalidPassport(passport2);

    assertThat(validPassport1).isNotEqualTo(validPassport2);
    assertThat(validPassport1).isEqualTo(validPassport1b);

  }
}