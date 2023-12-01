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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SystemOutTestUtil {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream outStream = new PrintStream(outContent);
    private static final PrintStream originalOut = System.out;

    public static void recordLinesWrittenToSystemOut() {
        System.setOut(outStream);
        outContent.reset();
    }

    public static String[] getLinesWrittenToSystemOut() {
        System.setOut(originalOut);
        return outContent.toString().split("\\n");
    }

}
