package com.ai1;

import com.ai1.activation.ActivationFunction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NeuronTest {

    public static final boolean BIAS = false;
    private Neuron neuron;
    private ActivationFunction activationFunction;

    @Before
    public void setUp() {
        //given
        activationFunction = mock(ActivationFunction.class);
        neuron = new Neuron(activationFunction, BIAS);
    }


    @Test
    public void shouldReturnNullWeightsBeforeActivation() {
        //then
        assertNull(neuron.getWeights());
    }

    @Test
    public void shouldSetActivationFunction() {
        //then
        assertNotNull(neuron.getActivationFunction());
    }
}
