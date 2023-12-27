/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this work.
 *  If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.droolsoopath.ruledata;

import org.drools.ruleunits.api.RuleUnitData;

public class StepRuleUnitUtil<T extends RuleUnitData> {

    @SuppressWarnings("unchecked")
    public T getRuleUnit(Class<T> clazzIn, int section, int step) {
        String stepDataClassTemplate = "io.github.aasaru.droolsoopath.section0%d.step%d." + clazzIn.getSimpleName();
        String className = String.format(stepDataClassTemplate, section, step);
        try {
            Class<?> clazz = Class.forName(className);
            return (T) clazz.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            throw new RuntimeException("Could not initiate object from class " + className, e);
        }
    }

}
