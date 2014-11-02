package net.ai1.neural;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class NetworkTrainerTest {
    @Test
    public void shouldHaveNeuronNetworkSet() {
        //given
        NeuralNetwork neuralNetwork = mock(NeuralNetwork.class);
        //when
        NetworkTrainer networkTrainer = new NetworkTrainer(neuralNetwork);
        //then
        assertThat(networkTrainer.getNeuralNetwork()).isEqualTo(neuralNetwork);
    }
}