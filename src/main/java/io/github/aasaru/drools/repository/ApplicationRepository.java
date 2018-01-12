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

package io.github.aasaru.drools.repository;

import io.github.aasaru.drools.domain.FamilyVisaApplication;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.VisaApplication;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Arrays.asList;

public class ApplicationRepository {

  public static final String CANADIAN_PASSPORT_NUMBER = "CA-111";
  public static final String NEW_ZEALAND_PASSPORT_NUMBER = "NZ-222";
  public static final String AUSTRALIAN_PASSPORT_NUMBER = "AU-333";
  public static final String GREAT_BRITAIN_PASSPORT_NUMBER = "GB-333";

  public static List<Passport> getPassports() {

    final Passport canadianPassport = Passport.newBuilder()
      .withPassportNumber(CANADIAN_PASSPORT_NUMBER)
      .withName("Mary")
      .withUnusedVisaPages(1)
      .withExpiresOn(LocalDate.of(2047, Month.NOVEMBER, 25))
      .build();

    final Passport kiwiPassport = Passport.newBuilder()
      .withPassportNumber(NEW_ZEALAND_PASSPORT_NUMBER)
      .withName("John")
      .withUnusedVisaPages(0)
      .withExpiresOn(LocalDate.of(2016, Month.FEBRUARY, 11))
      .build();

    final Passport aussiePassport = Passport.newBuilder()
      .withPassportNumber(AUSTRALIAN_PASSPORT_NUMBER)
      .withName("Robert")
      .withUnusedVisaPages(0)
      .withExpiresOn(LocalDate.of(2048, Month.MARCH, 11))
      .build();

    final Passport britonPassport = Passport.newBuilder()
      .withPassportNumber(GREAT_BRITAIN_PASSPORT_NUMBER)
      .withName("Daniel")
      .withUnusedVisaPages(10)
      .withExpiresOn(LocalDate.of(2045, Month.APRIL, 20))
      .build();

    return asList(canadianPassport, kiwiPassport, aussiePassport, britonPassport);
  }

  public static List<VisaApplication> getVisaApplications() {

    final VisaApplication canadianApplication = VisaApplication.newBuilder()
      .withApplicationId(1)
      .withPassportNumber(CANADIAN_PASSPORT_NUMBER)
      .withVisitStartDate(LocalDate.of(2025, Month.MAY, 10))
      .withVisitEndDate(LocalDate.of(2025, Month.MAY, 14))
      .build();

    final VisaApplication kiwiApplication = VisaApplication.newBuilder()
      .withApplicationId(2)
      .withPassportNumber(NEW_ZEALAND_PASSPORT_NUMBER)
      .withVisitStartDate(LocalDate.of(2029, Month.MAY, 1))
      .withVisitEndDate(LocalDate.of(2029, Month.JANUARY, 14))
      .build();

    final VisaApplication aussieApplication = VisaApplication.newBuilder()
      .withApplicationId(3)
      .withPassportNumber(AUSTRALIAN_PASSPORT_NUMBER)
      .withVisitStartDate(LocalDate.of(2035, Month.JANUARY, 1))
      .withVisitEndDate(LocalDate.of(2035, Month.MAY, 31))
      .build();

    final VisaApplication britonApplication = VisaApplication.newBuilder()
      .withApplicationId(4)
      .withPassportNumber(GREAT_BRITAIN_PASSPORT_NUMBER)
      .withVisitStartDate(LocalDate.of(2017, Month.SEPTEMBER, 1))
      .withVisitEndDate(LocalDate.of(2025, Month.JANUARY, 14))
      .build();

    return asList(canadianApplication, kiwiApplication, aussieApplication, britonApplication);
  }


  public static List<FamilyVisaApplication> getFamilyVisaApplications() {

    final FamilyVisaApplication canadianApplication =
      FamilyVisaApplication.newBuilder()
        .withApplicationId(11)
        .withPassportNumbers(asList(CANADIAN_PASSPORT_NUMBER))
        .withVisitStartDate(LocalDate.of(2025, Month.MAY, 10))
        .withVisitEndDate(LocalDate.of(2025, Month.MAY, 14))
        .build();

    final FamilyVisaApplication kiwiApplication =
      FamilyVisaApplication.newBuilder()
        .withApplicationId(12)
        .withPassportNumbers(asList(NEW_ZEALAND_PASSPORT_NUMBER))
        .withVisitStartDate(LocalDate.of(2029, Month.MAY, 1))
        .withVisitEndDate(LocalDate.of(2029, Month.JANUARY, 14))
        .build();


    final FamilyVisaApplication aussieApplication = FamilyVisaApplication.newBuilder()
      .withApplicationId(13)
      .withPassportNumbers(asList(AUSTRALIAN_PASSPORT_NUMBER))
      .withVisitStartDate(LocalDate.of(2035, Month.JANUARY, 1))
      .withVisitEndDate(LocalDate.of(2035, Month.MAY, 31))
      .build();


    final FamilyVisaApplication britonApplication = FamilyVisaApplication.newBuilder()
      .withApplicationId(14)
      .withPassportNumbers(asList(GREAT_BRITAIN_PASSPORT_NUMBER))
      .withVisitStartDate(LocalDate.of(2017, Month.SEPTEMBER, 1))
      .withVisitEndDate(LocalDate.of(2025, Month.JANUARY, 14))
      .build();

    return asList(canadianApplication, kiwiApplication, aussieApplication, britonApplication);

  }

}
