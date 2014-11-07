package net.ai1.neural;

import net.ai1.neural.error.ErrorCalculator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class NetworkTrainerTest {
    @Test
    public void shouldHaveNeuronNetworkSet() {
        //given
        NeuralNetwork neuralNetwork = mock(NeuralNetwork.class);
        ErrorCalculator errorCalculator = mock(ErrorCalculator.class);
        //when
        NetworkTrainer networkTrainer = new NetworkTrainer(neuralNetwork, errorCalculator);
        //then
        assertThat(networkTrainer.getNeuralNetwork()).isEqualTo(neuralNetwork);
    }
}