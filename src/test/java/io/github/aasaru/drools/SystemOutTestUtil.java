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
