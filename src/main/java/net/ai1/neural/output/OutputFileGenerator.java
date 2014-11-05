package net.ai1.neural.output;

import java.io.*;

public class OutputFileGenerator {

    public void generateOutput(OutputInformation outputInformation, OutputInformationToLatexStringConverter converter) {
        try (PrintWriter printWriter = new PrintWriter("output.txt")) {
            String line = converter.convert(outputInformation);
            printWriter.println(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
