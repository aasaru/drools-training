package io.github.aasaru.drools.repository;

import io.github.aasaru.drools.domain.Passport;

public class ApplicationRepositoryHelper {

    public static java.util.List<io.github.aasaru.drools.domain.VisaApplication> getLastReturnedVisaApplications() {
        return ApplicationRepository.lastReturnedVisaApplications;
    }

    public static java.util.List<Passport> getLastReturnedPassports() {
        return ApplicationRepository.lastReturnedPassports;
    }
}
