package io.github.aasaru.drools.section06;

import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.Visa;
import io.github.aasaru.drools.domain.VisaApplication;
import org.drools.ruleunit.DataSource;
import org.drools.ruleunit.RuleUnit;

import java.util.List;

public class Section06RuleUnit implements RuleUnit {

    public Section06RuleUnit(List<Passport> passports, List<VisaApplication> visaApplications) {
        this.passports = DataSource.create(passports.toArray(new Passport[0]));
        this.visaApplications = DataSource.create(visaApplications.toArray(new VisaApplication[0]));
        this.visas = DataSource.create();
    }

    private DataSource<Passport> passports;

    private DataSource<VisaApplication> visaApplications;
    private DataSource<Visa> visas;


    public DataSource<Passport> getPassports() {
        return passports;
    }

    public DataSource<VisaApplication> getVisaApplications() {
        return visaApplications;
    }

    public DataSource<Visa> getVisas() {
        return visas;
    }

    @Override
    public void onStart() {
        System.out.println("==== Section06RuleUnit STARTED ====");
    }

    @Override
    public void onEnd() {
        System.out.println("==== Section06RuleUnit ENDED ====");
    }

}
