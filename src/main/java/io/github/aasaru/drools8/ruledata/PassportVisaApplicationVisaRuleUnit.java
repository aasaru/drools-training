package io.github.aasaru.drools8.ruledata;

import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.Visa;
import io.github.aasaru.drools.domain.VisaApplication;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

public class PassportVisaApplicationVisaRuleUnit implements RuleUnitData {

    private final DataStore<Passport> passports;
    private final DataStore<VisaApplication> visaApplications;
    private final DataStore<Visa> visas;

    public PassportVisaApplicationVisaRuleUnit() {
        this(DataSource.createStore(), DataSource.createStore(), DataSource.createStore());
    }

    public PassportVisaApplicationVisaRuleUnit(DataStore<Passport> passports,
                                               DataStore<VisaApplication> visaApplications,
                                               DataStore<Visa> visas) {
        this.passports = passports;
        this.visaApplications = visaApplications;
        this.visas = visas;
    }

    public DataStore<Passport> getPassports() {
        return passports;
    }

    public DataStore<VisaApplication> getVisaApplications() {
        return visaApplications;
    }

    public DataStore<Visa> getVisas() {
        return visas;
    }

}