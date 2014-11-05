package net.ai1.neural.output;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OutputFileGeneratorTest {

    @Test
    public void shouldUseConverterToGetLineToBeSaved() throws FileNotFoundException {
        //given
        OutputInformation outputInformation = mock(OutputInformation.class);
        OutputInformationToLatexStringConverter converter = mock(OutputInformationToLatexStringConverter.class);
        OutputFileGenerator outputFileGenerator = new OutputFileGenerator();
        //when
        outputFileGenerator.generateOutput(outputInformation, converter);
        //then
        verify(converter).convert(outputInformation);
    }
}