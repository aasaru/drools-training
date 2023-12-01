/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this work.
 *  If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.query;

import io.github.aasaru.drools.domain.*;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.RuleUnitInstance;

import java.util.Collection;
import java.util.stream.Collectors;

public class SessionQueryUtil<T extends RuleUnitData> {

  public Collection<Visa> getAllVisasUsingQuery(RuleUnitInstance<T> instance) {
    return instance.executeQuery("GetAllVisas")
      .toList().stream()
      .map(tuple -> (Visa) tuple.get("$allVisas"))
      .collect(Collectors.toList());
  }

  public Collection<ValidPassport> getAllValidPassportsUsingQuery(RuleUnitInstance<T> instance) {
    return instance.executeQuery("GetAllValidPassports")
      .toList().stream()
      .map(tuple -> (ValidPassport) tuple.get("$allValidPassports"))
      .collect(Collectors.toList());
  }

  public Collection<InvalidPassport> getAllInvalidPassportsUsingQuery(RuleUnitInstance<T> instance) {
    return instance.executeQuery("GetAllInvalidPassports")
      .toList().stream()
      .map(tuple -> (InvalidPassport) tuple.get("$allInvalidPassports"))
      .collect(Collectors.toList());
  }

  public Collection<ValidVisaApplication> getAllValidVisaApplicationsUsingQuery(RuleUnitInstance<T> instance) {
    return instance.executeQuery("GetAllValidVisaApplications")
      .toList().stream()
      .map(tuple -> (ValidVisaApplication) tuple.get("$allValidVisaApplications"))
      .collect(Collectors.toList());
  }

  public Collection<InvalidVisaApplication> getAllInvalidVisaApplicationsUsingQuery(RuleUnitInstance<T> instance) {
    return instance.executeQuery("GetAllInvalidVisaApplications")
      .toList().stream()
      .map(tuple -> (InvalidVisaApplication) tuple.get("$allInvalidVisaApplications"))
      .collect(Collectors.toList());
  }

  public Collection<InvalidFamilyVisaApplication> getAllInvalidFamilyVisaApplicationsUsingQuery(RuleUnitInstance<T> instance) {
    return instance.executeQuery("GetAllInvalidFamilyVisaApplications")
      .toList().stream()
      .map(tuple -> (InvalidFamilyVisaApplication) tuple.get("$allInvalidFamilyVisaApplications"))
      .collect(Collectors.toList());
  }

  public Collection<GroupLeader> getAllGroupLeadersUsingQuery(RuleUnitInstance<T> instance) {
    return instance.executeQuery("GetAllGroupLeaders")
      .toList().stream()
      .map(tuple -> (GroupLeader) tuple.get("$allGroupLeaders"))
      .collect(Collectors.toList());
  }

}
