package io.github.aasaru.drools.section09.step1;

import io.github.aasaru.drools.domain.Passport;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

public class MyRuleUnit implements RuleUnitData {

    private final DataStore<Passport> passports;

    public MyRuleUnit() {
        this(DataSource.createStore());
    }

    public MyRuleUnit(DataStore<Passport> passports) {
        this.passports = passports;
    }

    public DataStore<Passport> getPassports() {
        return passports;
    }

}