package io.github.aasaru.drools.domain;

import java.time.LocalDate;

public interface GeneralVisaApplication {
    int getApplicationId();

    LocalDate getVisitStartDate();

    LocalDate getVisitEndDate();

    boolean isUrgent();
}
