/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.section07;

import org.junit.jupiter.api.Test;

public class VisaInsertLogicalTest {

  @Test
  public void shouldExecuteAllSteps() {
    VisaInsertLogical.execute(1);
    VisaInsertLogical.execute(2);
    VisaInsertLogical.execute(3);
    VisaInsertLogical.execute(4);
    VisaInsertLogical.execute(5);
  }

}
