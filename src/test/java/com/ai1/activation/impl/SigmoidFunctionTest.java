package com.ai1.activation.impl;

import com.ai1.activation.ActivationFunction;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class SigmoidFunctionTest {
    @Test
    public void shouldCalculateProperOutput() {
        //given
        ActivationFunction activationFunction = new SigmoidFunction();
        //when
        Double output = activationFunction.calculate(0.0);
        //then
        assertThat(output).isEqualTo(0.5, offset(0.0001));
    }

    @Test
    public void shouldCalculateDerivative() {
        //given
        ActivationFunction activationFunction = new SigmoidFunction();
        //when
        Double derivative = activationFunction.calculateDerivative(0.0);
        //then
        assertThat(derivative).isEqualTo(0.25, offset(0.0001));
    }
}
