/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Common {

  public static int promptForStep(int chapter, String[] args, int minStep, int maxStep) {
    String stepStr = "";

    while (true) {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      try {
        if (args != null && args.length > 0) {
          stepStr = args[0];
          return Integer.parseInt(stepStr);
        }
        else {
          System.out.print(String.format("Chapter %d. Enter step (%d...%d): ", chapter, minStep, maxStep));
          stepStr = br.readLine();
        }

        int step = Integer.parseInt(stepStr);

        if (step < minStep || step > maxStep) {
          System.out.println("Step number out of range. Insert a number between " + minStep + " and " + maxStep);
        }
        else {
          return step;
        }
      }
      catch (NumberFormatException e) {
        System.out.println("Invalid number: " + stepStr);
      }
      catch (IOException e) {
        System.out.println("Invalid step input: " + stepStr);
      }
    }

  }

}
