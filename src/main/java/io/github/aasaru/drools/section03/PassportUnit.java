package io.github.aasaru.drools.section03;

import io.github.aasaru.drools.domain.Passport;
import org.drools.ruleunit.DataSource;
import org.drools.ruleunit.RuleUnit;

import java.util.List;

public class PassportUnit implements RuleUnit {

    public PassportUnit(List<Passport> passports) {
        this.passports = DataSource.create(passports.toArray(new Passport[0]));
    }

    private DataSource<Passport> passports;

    public DataSource<Passport> getPassports() {
        return passports;
    }


    @Override
    public void onStart() {
        System.out.println("==== PassportUnit STARTED ====");
    }

    @Override
    public void onEnd() {
        System.out.println("==== PassportUnit ENDED ====");
    }
}
