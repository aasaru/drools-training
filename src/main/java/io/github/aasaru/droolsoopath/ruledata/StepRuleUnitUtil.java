package io.github.aasaru.droolsoopath.ruledata;

import org.drools.ruleunits.api.RuleUnitData;

public class StepRuleUnitUtil<T extends RuleUnitData> {

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
