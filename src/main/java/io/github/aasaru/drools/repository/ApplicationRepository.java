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
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ApplicationRepository {

  public static final String SARAH_PASSPORT_NUMBER = "CA-SARAH-1";
  public static final String SIMON_PASSPORT_NUMBER = "CA-SIMON-2";
  public static final String EMILY_PASSPORT_NUMBER = "AU-EMILY-3";
  public static final String JAMES_PASSPORT_NUMBER = "AU-JAMES-4";

  public static List<Passport> getPassports() {
    List<Passport> passports = new ArrayList<>();

    passports.add(Passport.newBuilder()
      .withPassportNumber(SARAH_PASSPORT_NUMBER)
      .withName("Sarah Murphy")
      .withUnusedVisaPages(1)
      .withExpiresOn(LocalDate.of(2017, Month.DECEMBER, 17))
      .withAge(50)
      .build());

    passports.add(Passport.newBuilder()
      .withPassportNumber(SIMON_PASSPORT_NUMBER)
      .withName("Simon Murphy")
      .withUnusedVisaPages(0)
      .withExpiresOn(LocalDate.of(2045, Month.MAY, 11))
      .withAge(12)
      .build());

    passports.add(Passport.newBuilder()
      .withPassportNumber(EMILY_PASSPORT_NUMBER)
      .withName("Emily Brown")
      .withUnusedVisaPages(20)
      .withExpiresOn(LocalDate.of(2047, Month.NOVEMBER, 25))
      .withAge(16)
      .build());

    passports.add(Passport.newBuilder()
      .withPassportNumber(JAMES_PASSPORT_NUMBER)
      .withName("James Brown")
      .withUnusedVisaPages(10)
      .withExpiresOn(LocalDate.of(2045, Month.APRIL, 10))
      .withAge(17)
      .build());

    return passports;
  }

  public static List<VisaApplication> getVisaApplications() {
    List<VisaApplication> visaApplications = new ArrayList<>();

    visaApplications.add(VisaApplication.newBuilder()
      .withApplicationId(1)
      .withPassportNumber(SARAH_PASSPORT_NUMBER)
      .withVisitStartDate(LocalDate.of(2039, Month.DECEMBER, 27))
      .withVisitEndDate(LocalDate.of(2040, Month.JANUARY, 4))
      .build());

    visaApplications.add(VisaApplication.newBuilder()
      .withApplicationId(2)
      .withPassportNumber(SIMON_PASSPORT_NUMBER)
      .withVisitStartDate(LocalDate.of(2039, Month.DECEMBER, 27))
      .withVisitEndDate(LocalDate.of(2039, Month.JANUARY, 4))
      .build());

    visaApplications.add(VisaApplication.newBuilder()
      .withApplicationId(3)
      .withPassportNumber(EMILY_PASSPORT_NUMBER)
      .withVisitStartDate(LocalDate.of(2044, Month.JANUARY, 1))
      .withVisitEndDate(LocalDate.of(2044, Month.MARCH, 31))
      .build());

    visaApplications.add(VisaApplication.newBuilder()
      .withApplicationId(4)
      .withPassportNumber(JAMES_PASSPORT_NUMBER)
      .withVisitStartDate(LocalDate.of(2045, Month.JANUARY, 1))
      .withVisitEndDate(LocalDate.of(2045, Month.MARCH, 10))
      .build());

    return visaApplications;
  }

  public static List<FamilyVisaApplication> getFamilyVisaApplications() {
    List<FamilyVisaApplication> familyVisaApplications = new ArrayList<>();

    familyVisaApplications.add(FamilyVisaApplication.newBuilder()
      .withApplicationId(10)
      .withPassportNumbers(asList(SARAH_PASSPORT_NUMBER, SIMON_PASSPORT_NUMBER))
      .withVisitStartDate(LocalDate.of(2039, Month.DECEMBER, 27))
      .withVisitEndDate(LocalDate.of(2040, Month.JANUARY, 4))
      .build());

    familyVisaApplications.add(FamilyVisaApplication.newBuilder()
        .withApplicationId(11)
      .withPassportNumbers(asList(EMILY_PASSPORT_NUMBER, JAMES_PASSPORT_NUMBER))
      .withVisitStartDate(LocalDate.of(2044, Month.JANUARY, 1))
      .withVisitEndDate(LocalDate.of(2044, Month.MAY, 31))
      .build());

    return familyVisaApplications;
  }

}
