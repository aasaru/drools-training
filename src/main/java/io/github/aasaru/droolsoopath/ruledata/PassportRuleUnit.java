package io.github.aasaru.droolsoopath.ruledata;

import io.github.aasaru.drools.domain.Passport;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStream;
import org.drools.ruleunits.api.RuleUnitData;

public class PassportRuleUnit implements RuleUnitData {

    private final DataStream<Passport> passports = DataSource.createStream();

    public DataStream<Passport> getPassports() {
        return passports;
    }

}