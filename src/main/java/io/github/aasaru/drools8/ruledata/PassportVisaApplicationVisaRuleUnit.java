package io.github.aasaru.drools8.ruledata;

import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.Visa;
import io.github.aasaru.drools.domain.VisaApplication;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStream;
import org.drools.ruleunits.api.RuleUnitData;

public class PassportVisaApplicationVisaRuleUnit implements RuleUnitData {

    private final DataStream<Passport> passports = DataSource.createStream();
    private final DataStream<VisaApplication> visaApplications = DataSource.createStream();
    private final DataStream<Visa> visas = DataSource.createStream();


    public DataStream<Passport> getPassports() {
        return passports;
    }

    public DataStream<VisaApplication> getVisaApplications() {
        return visaApplications;
    }

    public DataStream<Visa> getVisas() {
        return visas;
    }

}