package net.ai1.neural.output;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OutputInformationToLatexStringConverterTest {

    @Test
    public void shouldReturnValueForGivenOutPutInformation() {
        //given
        List<Double> initialWeights = getList(1.00111, 2.02222);
        List<Double> finalWeights = getList(3.03333, 4.0);
        OutputInformation outputInformation = mock(OutputInformation.class);
        OutputInformationToLatexStringConverter converter = new OutputInformationToLatexStringConverter();
        //when
        when(outputInformation.getInitialWeights()).thenReturn(initialWeights);
        when(outputInformation.getFinalWeights()).thenReturn(finalWeights);
        when(outputInformation.getEpsilon()).thenReturn(0.001);
        when(outputInformation.getLearningRate()).thenReturn(0.01);
        when(outputInformation.getNumberOfEpochs()).thenReturn(99);
        String result = converter.convert(outputInformation);
        //then
        assertThat(result).isEqualTo("1.001,2.022&0.01&99&3.033,4.000&0.001\\\\");
    }

    private List<Double> getList(Double...values) {
        List<Double> doubleList = new LinkedList<>();
        Collections.addAll(doubleList, values);
        return doubleList;
    }
}
