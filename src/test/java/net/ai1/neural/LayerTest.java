package net.ai1.neural;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LayerTest {

    private Layer previousLayer;
    private Layer layer;


    @Before
    public void setUp() {
        previousLayer = mock(Layer.class);
        layer = new Layer(previousLayer);

    }

    @Test
    public void shouldBeLastLayerWhenNextLayerIsNull() {
        //when
        boolean isOutputLayer = layer.isOutputLayer();
        //then
        assertThat(isOutputLayer).isTrue();
    }

    @Test
    public void shouldHaveBiasNeuronWhenSet() {
        Neuron bias = mock(Neuron.class);
        //when
        Layer layer = new Layer(previousLayer, bias);
        //then
        assertThat(layer.hasBias()).isTrue();
        assertThat(layer.getNeurons()).containsOnly(bias);
    }

    @Test
    public void shouldCreateAdditionalInputWhenAddingNeuron() {
        //given
        Neuron neuron = mock(Neuron.class);
        List<Neuron> neurons = new ArrayList<>();
        neurons.add(neuron);
        //when
        when(previousLayer.getNeurons()).thenReturn(neurons);
        layer.addNeuron(neuron);
        //then
        verify(neuron).addInput(any(Connection.class));
    }

    @Test
    public void shouldPerformActivationOnAllNeuronsWhenFeedForward() {
        //given
        Neuron neuronA = mock(Neuron.class);
        Neuron neuronB = mock(Neuron.class);
        //when
        layer.addNeuron(neuronA);
        layer.addNeuron(neuronB);
        layer.feedForward();
        //then
        verify(neuronA).activate();
        verify(neuronB).activate();
    }
}