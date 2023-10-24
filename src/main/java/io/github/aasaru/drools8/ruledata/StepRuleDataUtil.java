package io.github.aasaru.drools8.ruledata;

public class StepRuleDataUtil {

    public static RuleData getRuleData(int section, int step) {

        String stepDataClassTemplate = "io.github.aasaru.drools8.section0%d.step%d.Step%dRuleData";
        String className = String.format(stepDataClassTemplate, section, step, step);
        try {
            Class<?> clazz = Class.forName(className);
            return (RuleData) clazz.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            throw new RuntimeException("Could not initiate object from class " + className, e);
        }

    }

}
