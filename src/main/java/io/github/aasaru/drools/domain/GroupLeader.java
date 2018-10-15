package io.github.aasaru.drools.domain;

public class GroupLeader {
  private Passport passport;
  private FamilyVisaApplication familyVisaApplication;

  public GroupLeader(Passport passport, FamilyVisaApplication familyVisaApplication) {
    this.passport = passport;
    this.familyVisaApplication = familyVisaApplication;
  }

  public Passport getPassport() {
    return passport;
  }

  @Override
  public String toString() {
    return "Visa Application #"+ familyVisaApplication.getApplicationId() + " group leader is " + passport.getName();
  }
}
