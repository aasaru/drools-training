package io.github.aasaru.drools.section09;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import io.github.aasaru.drools.Common;
import io.github.aasaru.drools.domain.FamilyVisaApplication;
import io.github.aasaru.drools.domain.Passport;
import io.github.aasaru.drools.domain.VisaApplication;
import io.github.aasaru.drools.domain.VisaApplicationFolder;
import io.github.aasaru.drools.domain.VisaFee;
import io.github.aasaru.drools.repository.ApplicationRepository;
import org.drools.compiler.compiler.DecisionTableFactory;
import org.drools.core.builder.conf.impl.DecisionTableConfigurationImpl;
import org.drools.core.io.impl.FileSystemResource;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.DecisionTableConfiguration;

public class VisaFeeCalculation {

    public static void main(final String[] args) {

        int step = Common.promptForStep(9, args, 1, 12);

        if (step < 0) {

            step = Math.abs(step);

            // TODO - remove absolute path and make work when running from JAR

            DecisionTableConfiguration decisionTableConfiguration = new DecisionTableConfigurationImpl();

            FileSystemResource
                 spreadsheetFile =
                 new FileSystemResource("/Users/juhan/spaces/github/drools-training/src/main/resources/io/github/aasaru/drools/section09/step"+step+"/step"+step+".xls");
            String generatedDrl = DecisionTableFactory.loadFromResource(spreadsheetFile, decisionTableConfiguration);

            System.out.println(generatedDrl);

        }
        else {
            ex(step);
        }

    }

    static Collection<VisaFee> ex(int step) {
        System.out.println("Running step " + step);

        if (step <= 8) {
            List<Object> factsToInsert = new ArrayList<>();

            factsToInsert.addAll(ApplicationRepository.getFamilyVisaApplications());
            factsToInsert.addAll(ApplicationRepository.getVisaApplications());
            factsToInsert.addAll(ApplicationRepository.getPassports());


            return executeInSingleSession(step, factsToInsert);
        }
        else if (step == 9 || step == 10) {
            return executeInSeparateSessions(step);
        }
        else {
            return executeFoldersInSeparateSessions(step);
        }
    }


    // TODO R2 => give me rules of step2...

    static Collection<VisaFee> executeInSingleSession(int step, Collection<Object> factsToInsert) {

        KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("VisaFeeCalculationStep" + step);

        factsToInsert.forEach(ksession::insert);

        System.out.println("==== DROOLS SESSION START ==== ");
        ksession.fireAllRules();
        ksession.dispose();
        System.out.println("==== DROOLS SESSION END ==== ");

        Collection<VisaFee> fees = getVisaFeesFromKieSession(ksession);


        System.out.println("== Fees from session == ");
        fees.forEach(System.out::println);

        return fees;
    }

    static Collection<VisaFee> executeInSeparateSessions(int step) {
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();

        List<FamilyVisaApplication> familyVisaApplications = ApplicationRepository.getFamilyVisaApplications();

        List<VisaApplicationFolder> visaApplicationFolders = ApplicationRepository.getVisaApplicationFolders();

        Collection<VisaFee> fees = new ArrayList<>();
        System.out.println("==== DROOLS SESSIONS START ==== ");


        for (FamilyVisaApplication familyVisaApplication : familyVisaApplications) {
            fees.addAll(executeInSingleSession(step, kieContainer, familyVisaApplication));
        }

        for (VisaApplicationFolder visaApplicationFolder : visaApplicationFolders) {
            Passport passport = visaApplicationFolder.getPassport();
            VisaApplication visaApplication = visaApplicationFolder.getVisaApplication();

            Collection<VisaFee> visaFees = executeInSingleSession(step, kieContainer, passport, visaApplication);
            fees.addAll(visaFees);
        }

        System.out.println("==== DROOLS SESSIONS END ==== ");


        System.out.println("== Fees from all sessions == ");
        fees.forEach(System.out::println);

        return fees;
    }

    static Collection<VisaFee> executeFoldersInSeparateSessions(int step) {
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();

        List<VisaApplicationFolder> visaApplicationFolders = ApplicationRepository.getVisaApplicationFolders();

        Collection<VisaFee> fees = new ArrayList<>();

        System.out.println("==== DROOLS SESSIONS START ==== ");
        // TODO NB! Family visa applications have been removed!

        for (VisaApplicationFolder visaApplicationFolder : visaApplicationFolders) {
            Collection<VisaFee> visaFees = executeInSingleSession(step, kieContainer, visaApplicationFolder);
            fees.addAll(visaFees);
        }
        System.out.println("==== DROOLS SESSIONS END ==== ");


        System.out.println("== Fees from all sessions == ");
        fees.forEach(System.out::println);

        return fees;
    }

    static Collection<VisaFee> executeInSingleSession(int step, KieContainer kieContainer, Object... objects) {

        KieSession ksession = kieContainer.newKieSession("VisaFeeCalculationStep" + step);

        List<Object> objects1 = asList(objects);
        System.out.println("Sending to session: " + objects1);
        objects1.forEach(ksession::insert);

        ksession.fireAllRules();
        ksession.dispose();

        Collection<VisaFee> fees = getVisaFeesFromKieSession(ksession);

        return fees;
    }

    private static Collection<VisaFee> getVisaFeesFromKieSession(KieSession ksession) {
        Collection<VisaFee> fees = ksession.getObjects(o -> o.getClass() == VisaFee.class)
             .stream()
             .map(fee -> (VisaFee) fee)
             .collect(Collectors.toList());
        return fees;
    }

}
