package net.ai1.neural;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class NeuralNetworkTest {

    private NeuralNetwork neuralNetwork;

    @Before
    public void setUp() {
        neuralNetwork = new NeuralNetwork("Name");
    }

    @Test
    public void shouldHaveName() {
        //when
        String networkName = neuralNetwork.getName();
        //then
        assertThat(networkName).isEqualTo("Name");
    }

    @Test
    public void firstAddedLayerShouldBeInputAndOutputLayer() {
        //given
        Layer firstLayer = mock(Layer.class);
        //when
        neuralNetwork.addLayer(firstLayer);
        //then
        assertThat(neuralNetwork.getLayers()).containsOnly(firstLayer);
        assertThat(neuralNetwork.getInputLayer()).isEqualTo(firstLayer);
        assertThat(neuralNetwork.getOutputLayer()).isEqualTo(firstLayer);
    }

    @Test
    public void gettingOutputShouldCallFeedForwardOnAllLayersButFirst() {
        //given
        Layer firstLayer = mock(Layer.class);
        Layer secondLayer = mock(Layer.class);
        //when
        neuralNetwork.addLayer(firstLayer);
        neuralNetwork.addLayer(secondLayer);
        neuralNetwork.getOutput();
        //then
        verify(firstLayer, never()).feedForward();
        verify(secondLayer).feedForward();
    }
}