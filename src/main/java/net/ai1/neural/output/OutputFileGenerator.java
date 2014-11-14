package net.ai1.neural.output;

import java.io.*;

/**
 * The Output file generator.
 */
public class OutputFileGenerator {

    public static final String FILE_NAME = "output.txt";

    /**
     * Generate output.
     *
     * @param outputInformation the output information
     * @param converter the output information to latex string converter
     */
    public void generateOutput(OutputInformation outputInformation, OutputInformationToLatexStringConverter converter) {
        try (PrintWriter printWriter = new PrintWriter(FILE_NAME)) {
            String line = converter.convert(outputInformation);
            printWriter.println(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
