/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */
package io.github.aasaru.drools.section09;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import io.github.aasaru.drools.domain.VisaFee;
import org.drools.compiler.compiler.DecisionTableFactory;
import org.drools.core.builder.conf.impl.DecisionTableConfigurationImpl;
import org.drools.core.io.impl.FileSystemResource;
import org.junit.jupiter.api.Test;
import org.kie.internal.builder.DecisionTableConfiguration;

public class VisaFeeCalculationTest {
/*
  @Test
  void executeStep2_shouldReturnCorrectAmounts() {
    Collection<VisaFee> fees = VisaFeeCalculation.executeInSingleSession(2);

    List<Double> feeAmounts = fees.stream().map(VisaFee::getFeeAmount).collect(Collectors.toList());

    assertThat(feeAmounts, containsInAnyOrder(104.0, 106.0, 100.0, 88.0, 202.0, 100.0));
    assertThat(feeAmounts, hasSize(6));
  }
*/

  @Test
  void getRules() {

    DecisionTableConfiguration decisionTableConfiguration = new DecisionTableConfigurationImpl();

    FileSystemResource
         spreadsheetFile =
         new FileSystemResource("/Users/juhan/spaces/github/drools-training/src/main/resources/io/github/aasaru/drools/section09/step2/step1.xls");
    String generatedDrl = DecisionTableFactory.loadFromResource(spreadsheetFile, decisionTableConfiguration);

    System.out.println(generatedDrl);

  }
}
