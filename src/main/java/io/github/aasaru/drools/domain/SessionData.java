package io.github.aasaru.drools.domain;

import java.util.Collection;

public class SessionData {

  public Collection<Passport> passports;
  public Collection<VisaApplication> visaApplications;
  public Collection<Visa> visas;
  public Collection<ValidPassport> validPassports;
  public Collection<InvalidPassport> invalidPassports;
  public Collection<ValidVisaApplication> validVisaApplications;
  public Collection<InvalidVisaApplication> invalidVisaApplications;
  public Collection<InvalidFamilyVisaApplication> invalidFamilyVisaApplications;
  public Collection<GroupLeader> groupLeaders;

}
