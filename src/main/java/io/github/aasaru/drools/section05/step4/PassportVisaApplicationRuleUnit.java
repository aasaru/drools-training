package io.github.aasaru.drools.section05.step4;

import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.VisaApplication;

import java.util.List;

public class PassportVisaApplicationRuleUnit extends io.github.aasaru.drools.section05.PassportVisaApplicationRuleUnit {
    public PassportVisaApplicationRuleUnit(List<Passport> passports, List<VisaApplication> visaApplications) {
        super(passports, visaApplications);
    }

}
