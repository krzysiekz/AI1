package com.ai1;

import com.ai1.activation.ActivationFunction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class NeuronTest {

    public static final int NUMBER_OF_INPUTS = 5;
    Neuron neuron;
    ActivationFunction activationFunction;

    @Before
    public void setUp() {
        //given
        activationFunction = mock(ActivationFunction.class);
        neuron = new Neuron(NUMBER_OF_INPUTS, activationFunction);
    }

    @Test
    public void shouldSetNumberOfInputs() {
        //then
        assertEquals(NUMBER_OF_INPUTS, neuron.getNumberOfInputs());
    }

    @Test
    public void shouldReturnWeightsListWhenNumberOfInputsSet() {
        //then
        assertTrue(neuron.getWeights() != null);
    }

    @Test
    public void shouldReturnWeighsListOfProperSize() {
        //then
        assertEquals(NUMBER_OF_INPUTS, neuron.getWeights().size());
    }

    @Test
    public void shouldSetActivationFunction() {
        //then
        assertTrue(neuron.getActivationFunction() != null);
    }
}
