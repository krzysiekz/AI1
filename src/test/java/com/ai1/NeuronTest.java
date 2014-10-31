package com.ai1;

import com.ai1.activation.ActivationFunction;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

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
        assertThat(neuron.getWeights()).isNull();
    }

    @Test
    public void shouldSetActivationFunction() {
        //then
        assertThat(neuron.getActivationFunction()).isNotNull();
    }

    @Test
    public void shouldSetError() {
        //when
        neuron.serError(5.55);
        //then
        assertThat(neuron.getError()).isEqualTo(5.55);
    }
}
