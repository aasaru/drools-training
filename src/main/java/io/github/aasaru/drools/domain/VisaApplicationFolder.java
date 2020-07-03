package io.github.aasaru.drools.domain;

public class VisaApplicationFolder {

    public VisaApplicationFolder(Passport passport, VisaApplication visaApplication) {
        if (!passport.getPassportNumber().equals(visaApplication.getPassportNumber())) {
            throw new IllegalArgumentException("Passport number doesn't match with passport number in visa application");
        }

        this.visaApplication = visaApplication;
        this.passport = passport;
    }

    private Passport passport;
    private VisaApplication visaApplication;

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public VisaApplication getVisaApplication() {
        return visaApplication;
    }

    public void setVisaApplication(VisaApplication visaApplication) {
        this.visaApplication = visaApplication;
    }

    @Override
    public String toString() {
        return "VisaApplicationFolder(visaApplication:#" + visaApplication.getApplicationId() + ", passport:" + passport.getPassportNumber() + ")";
    }
}
