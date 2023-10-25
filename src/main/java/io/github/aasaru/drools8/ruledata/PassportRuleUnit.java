package io.github.aasaru.drools8.ruledata;

import io.github.aasaru.drools.domain.Passport;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

public class PassportRuleUnit implements RuleUnitData {

    private final DataStore<Passport> passports;

    public PassportRuleUnit() {
        this(DataSource.createStore());
    }

    public PassportRuleUnit(DataStore<Passport> passports) {
        this.passports = passports;
    }

    public DataStore<Passport> getPassports() {
        return passports;
    }

}