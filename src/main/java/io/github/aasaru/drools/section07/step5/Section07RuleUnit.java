package io.github.aasaru.drools.section07.step5;

import io.github.aasaru.drools.domain.*;
import org.drools.ruleunit.DataSource;
import org.drools.ruleunit.RuleUnit;

import java.util.List;

public class Section07RuleUnit implements RuleUnit {

    public Section07RuleUnit(List<Passport> passports, List<VisaApplication> visaApplications) {
        this.passports = DataSource.create(passports.toArray(new Passport[0]));
        this.visaApplications = DataSource.create(visaApplications.toArray(new VisaApplication[0]));
        this.visas = DataSource.create();
        this.invalidPassports = DataSource.create();
        this.invalidVisaApplications = DataSource.create();
    }

    private DataSource<Passport> passports;
    private DataSource<InvalidPassport> invalidPassports;

    private DataSource<VisaApplication> visaApplications;

    private DataSource<InvalidVisaApplication> invalidVisaApplications;
    private DataSource<Visa> visas;


    public DataSource<Passport> getPassports() {
        return passports;
    }

    public DataSource<InvalidPassport> getInvalidPassports() {
        return invalidPassports;
    }

    public DataSource<VisaApplication> getVisaApplications() {
        return visaApplications;
    }

    public DataSource<InvalidVisaApplication> getInvalidVisaApplications() {
        return invalidVisaApplications;
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
