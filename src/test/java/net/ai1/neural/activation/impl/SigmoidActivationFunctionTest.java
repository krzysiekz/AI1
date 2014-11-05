package net.ai1.neural.activation.impl;

import net.ai1.neural.activation.ActivationFunction;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

public class SigmoidActivationFunctionTest {

    private ActivationFunction activationFunction;

    @Before
    public void setUp() {
        //given
        activationFunction = new SigmoidActivationFunction();
    }

    @Test
    public void shouldCalculateProperOutput() {
        //when
        Double output = activationFunction.activate(0.0);
        //then
        assertThat(output).isEqualTo(0.5, offset(0.0001));
    }

    @Test
    public void derivativeShouldCalculateProperOutput() {
        //when
        Double output = activationFunction.derivative(0.0);
        //then
        assertThat(output).isEqualTo(0.25, offset(0.0001));
    }
}