package io.github.aasaru.drools8.ruledata;

import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.VisaApplication;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

public class PassportVisaApplicationRuleUnit implements RuleUnitData {

    private final DataStore<Passport> passports;
    private final DataStore<VisaApplication> visaApplications;

    public PassportVisaApplicationRuleUnit() {
        this(DataSource.createStore(), DataSource.createStore());
    }

    public PassportVisaApplicationRuleUnit(DataStore<Passport> passports, DataStore<VisaApplication> visaApplications) {
        this.passports = passports;
        this.visaApplications = visaApplications;
    }

    public DataStore<Passport> getPassports() {
        return passports;
    }

    public DataStore<VisaApplication> getVisaApplications() {
        return visaApplications;
    }

}