package io.github.aasaru.drools8.ruledata;

import io.github.aasaru.drools.domain.Passport;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

public class RuleData implements RuleUnitData {

    private final DataStore<Passport> passports;

    public RuleData() {
        this(DataSource.createStore());
    }

    public RuleData(DataStore<Passport> passports) {
        this.passports = passports;
    }

    public DataStore<Passport> getPassports() {
        return passports;
    }

}