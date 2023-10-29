package io.github.aasaru.drools.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import static org.assertj.core.api.Assertions.assertThat;

class ValidPassportTest {

  @Test
  void testEquals() {

    Passport passport = Passport.newBuilder()
      .withPassportNumber("ABC123")
      .withName("Abc 123")
      .withUnusedVisaPages(1)
      .withExpiresOn(LocalDate.of(2017, Month.DECEMBER, 17))
      .withAge(50)
      .build();

    Passport passport2 = Passport.newBuilder()
      .withPassportNumber("CDE456")
      .withName("Abc 123")
      .withUnusedVisaPages(1)
      .withExpiresOn(LocalDate.of(2017, Month.DECEMBER, 17))
      .withAge(50)
      .build();

    ValidPassport validPassport1 = new ValidPassport(passport);
    ValidPassport validPassport2 = new ValidPassport(passport2);

    assertThat(validPassport1).isNotEqualTo(validPassport2);

  }
}