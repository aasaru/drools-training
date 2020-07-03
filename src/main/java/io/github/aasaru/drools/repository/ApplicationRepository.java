/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.repository;

import io.github.aasaru.drools.domain.VisaApplicationFolder;
import io.github.aasaru.drools.domain.VisaType;
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

  public static final Passport SARAH_PASSPORT = Passport.newBuilder()
       .withPassportNumber(SARAH_PASSPORT_NUMBER)
       .withName("Sarah Murphy")
       .withUnusedVisaPages(1)
       .withExpiresOn(LocalDate.of(2017, Month.DECEMBER, 17))
       .withAge(50)
       .build();
  public static final Passport SIMON_PASSPORT = Passport.newBuilder()
       .withPassportNumber(SIMON_PASSPORT_NUMBER)
       .withName("Simon Murphy")
       .withUnusedVisaPages(0)
       .withExpiresOn(LocalDate.of(2045, Month.MAY, 11))
       .withAge(12)
       .build();
  public static final Passport EMILY_PASSPORT = Passport.newBuilder()
       .withPassportNumber(EMILY_PASSPORT_NUMBER)
       .withName("Emily Brown")
       .withUnusedVisaPages(20)
       .withExpiresOn(LocalDate.of(2047, Month.NOVEMBER, 25))
       .withAge(16)
       .build();
  public static final Passport JAMES_PASSPORT = Passport.newBuilder()
       .withPassportNumber(JAMES_PASSPORT_NUMBER)
       .withName("James Brown")
       .withUnusedVisaPages(10)
       .withExpiresOn(LocalDate.of(2045, Month.APRIL, 10))
       .withAge(17)
       .build();

  public static final VisaApplication SARAH_VISA_APPLICATION = VisaApplication.newBuilder()
       .withApplicationId(1)
       .withPassportNumber(SARAH_PASSPORT_NUMBER)
       .withVisitStartDate(LocalDate.of(2039, Month.DECEMBER, 27))
       .withVisitEndDate(LocalDate.of(2040, Month.JANUARY, 4))
       .withVisaType(VisaType.TOURIST)
       .withIsUrgent(true)
       .build();
  public static final VisaApplication SIMON_VISA_APPLICATION = VisaApplication.newBuilder()
       .withApplicationId(2)
       .withPassportNumber(SIMON_PASSPORT_NUMBER)
       .withVisitStartDate(LocalDate.of(2039, Month.DECEMBER, 27))
       .withVisitEndDate(LocalDate.of(2039, Month.JANUARY, 4))
       .withVisaType(VisaType.TOURIST)
       .build();
  public static final VisaApplication EMILY_VISA_APPLICATION = VisaApplication.newBuilder()
       .withApplicationId(3)
       .withPassportNumber(EMILY_PASSPORT_NUMBER)
       .withVisitStartDate(LocalDate.of(2044, Month.JANUARY, 1))
       .withVisitEndDate(LocalDate.of(2044, Month.MARCH, 31))
       .withVisaType(VisaType.WORK)
       .build();
  public static final VisaApplication JAMES_VISA_APPLICATION = VisaApplication.newBuilder()
       .withApplicationId(4)
       .withPassportNumber(JAMES_PASSPORT_NUMBER)
       .withVisitStartDate(LocalDate.of(2045, Month.JANUARY, 1))
       .withVisitEndDate(LocalDate.of(2045, Month.MARCH, 10))
       .withVisaType(VisaType.STUDENT)
       .withIsUrgent(true)
       .build();

  public static List<Passport> getPassports() {
    return asList(SARAH_PASSPORT, SIMON_PASSPORT, EMILY_PASSPORT, JAMES_PASSPORT);
  }

  public static List<VisaApplication> getVisaApplications() {
    return asList(SARAH_VISA_APPLICATION, SIMON_VISA_APPLICATION, EMILY_VISA_APPLICATION, JAMES_VISA_APPLICATION);
  }

  public static List<VisaApplicationFolder> getVisaApplicationFolders() {

    return asList(
      new VisaApplicationFolder(SARAH_PASSPORT, SARAH_VISA_APPLICATION),
      new VisaApplicationFolder(SIMON_PASSPORT, SIMON_VISA_APPLICATION),
      new VisaApplicationFolder(EMILY_PASSPORT, EMILY_VISA_APPLICATION),
      new VisaApplicationFolder(JAMES_PASSPORT, JAMES_VISA_APPLICATION)
    );

  }


  public static List<FamilyVisaApplication> getFamilyVisaApplications() {
    List<FamilyVisaApplication> familyVisaApplications = new ArrayList<>();

    familyVisaApplications.add(FamilyVisaApplication.newBuilder()
      .withApplicationId(10)
      .withPassportNumbers(asList(SARAH_PASSPORT_NUMBER, SIMON_PASSPORT_NUMBER))
      .withVisitStartDate(LocalDate.of(2039, Month.DECEMBER, 27))
      .withVisitEndDate(LocalDate.of(2040, Month.JANUARY, 4))
      .withEntryType(VisaType.WORK)
      .withIsUrgent(true)
      .build());

    familyVisaApplications.add(FamilyVisaApplication.newBuilder()
        .withApplicationId(11)
      .withPassportNumbers(asList(EMILY_PASSPORT_NUMBER, JAMES_PASSPORT_NUMBER))
      .withVisitStartDate(LocalDate.of(2044, Month.JANUARY, 1))
      .withVisitEndDate(LocalDate.of(2044, Month.MAY, 31))
      .withEntryType(VisaType.TOURIST)
      .build());

    return familyVisaApplications;
  }

}
