package io.github.aasaru.drools.section05;

import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.VisaApplication;
import org.drools.ruleunit.DataSource;
import org.drools.ruleunit.RuleUnit;

import java.util.List;

public class PassportVisaApplicationUnit implements RuleUnit {

    public PassportVisaApplicationUnit(List<Passport> passports, List<VisaApplication> visaApplications) {
        this.passports = DataSource.create(passports.toArray(new Passport[0]));
        this.visaApplications = DataSource.create(visaApplications.toArray(new VisaApplication[0]));
    }

    private DataSource<Passport> passports;

    private DataSource<VisaApplication> visaApplications;

    public DataSource<Passport> getPassports() {
        return passports;
    }

    public DataSource<VisaApplication> getVisaApplications() {
        return visaApplications;
    }

    @Override
    public void onStart() {
        System.out.println("==== PassportVisaApplicationUnit STARTED ====");
    }

    @Override
    public void onEnd() {
        System.out.println("==== PassportVisaApplicationUnit ENDED ====");
    }

}
