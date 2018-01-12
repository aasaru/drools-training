/*
 * Copyright 2017 Juhan Aasaru.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.aasaru.drools.chapter03;

import org.junit.jupiter.api.Test;

public class VisaApplicationValidationTest {

  @Test
  public void shouldExecuteAllSteps() {
    VisaApplicationValidation.execute(1);
    VisaApplicationValidation.execute(2);
    VisaApplicationValidation.execute(3);
    VisaApplicationValidation.execute(4);
  }
}
