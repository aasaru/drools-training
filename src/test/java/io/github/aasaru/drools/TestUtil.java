/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this work.
 *  If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

import java.util.Collection;
import java.util.Optional;

public class TestUtil {

    public static KieSession getKieSession(String baseName, int step) {
        return getKieSession(baseName + step);
    }

    public static KieSession getKieSession(String sessionName) {
        Collection<String> kieBaseNames = KieServices.Factory.get().getKieClasspathContainer().getKieBaseNames();

        KieSession ksession = null;
        for (String kieBaseName : kieBaseNames) {

            Collection<String> kieSessionNamesInKieBase = KieServices.Factory.get().getKieClasspathContainer().getKieSessionNamesInKieBase(kieBaseName);

            for (String kieSessionName : kieSessionNamesInKieBase) {
                if (sessionName.equals(kieSessionName)) {
                    for (KieSession kieSession : KieServices.Factory.get().getKieClasspathContainer().getKieBase(kieBaseName).getKieSessions()) {

                        ksession = kieSession;

                    }
                }
            }

        }
        return ksession;
    }

    public static void disposeKieSessionIfExists(String kieSessionName) {
        Optional.ofNullable(TestUtil.getKieSession(kieSessionName)).ifPresent(KieSession::dispose);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void addObjectsOfType(KieSession ksession, Collection collection, Class type) {

        for (Object object : ksession.getObjects()) {
            if (object.getClass() == type) {
                collection.add(type.cast(object));
            }
        }

    }
}
