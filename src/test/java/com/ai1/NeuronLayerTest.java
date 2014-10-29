package com.ai1;

import com.ai1.activation.ActivationFunction;
import com.ai1.activation.impl.LinearFunction;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class NeuronLayerTest {

    public static final int NUMBER_OF_NEURONS = 4;

    @Test
    public void shouldCreateXNeurons() {
        //given
        ActivationFunction activationFunction = mock(LinearFunction.class);
        NeuronLayer neuronLayer = new NeuronLayer(false, activationFunction, NUMBER_OF_NEURONS);
        //when
        List<Neuron> neurons =  neuronLayer.getNeurons();
        //then
        assertEquals(NUMBER_OF_NEURONS, neurons.size());
        for(Neuron neuron : neurons) {
            assertTrue(neuron.getActivationFunction() instanceof LinearFunction);
        }
    }
}
