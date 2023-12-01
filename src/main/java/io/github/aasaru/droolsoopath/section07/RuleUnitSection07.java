/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this work.
 *  If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.section07;

import io.github.aasaru.drools.domain.*;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.DataStream;
import org.drools.ruleunits.api.RuleUnitData;

public class RuleUnitSection07 implements RuleUnitData {

    private final DataStream<Passport> passports = DataSource.createStream();
    private final DataStream<VisaApplication> visaApplications = DataSource.createStream();
    private final DataStore<Visa> visas = DataSource.createStore();

    private final DataStore<ValidPassport> validPassports = DataSource.createStore();
    private final DataStore<InvalidPassport> invalidPassports = DataSource.createStore();

    private final DataStore<ValidVisaApplication> validVisaApplications = DataSource.createStore();
    private final DataStore<InvalidVisaApplication> invalidVisaApplications = DataSource.createStore();


    public DataStream<Passport> getPassports() {
        return passports;
    }

    public DataStream<VisaApplication> getVisaApplications() {
        return visaApplications;
    }

    public DataStore<Visa> getVisas() {
        return visas;
    }

    public DataStore<ValidPassport> getValidPassports() {
        return validPassports;
    }

    public DataStore<InvalidPassport> getInvalidPassports() {
        return invalidPassports;
    }

    public DataStore<ValidVisaApplication> getValidVisaApplications() {
        return validVisaApplications;
    }

    public DataStore<InvalidVisaApplication> getInvalidVisaApplications() {
        return invalidVisaApplications;
    }

}