package com.ai1.activation.impl;

import com.ai1.activation.ActivationFunction;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

/**
 * The type Linear function test.
 */
public class LinearFunctionTest {
    @Test
    public void shouldReturnTheSameValue() {
        //given
        ActivationFunction linear = new LinearFunction();
        //then
        assertThat(linear.calculate(5)).isEqualTo(5, offset(0.1));
        assertThat(linear.calculate(10)).isEqualTo(10, offset(0.1));
    }

    @Test
    public void shouldCalculateDerivative() {
        //given
        ActivationFunction linear = new LinearFunction();
        //then
        assertThat(linear.calculateDerivative(5.0)).isEqualTo(1, offset(0.0001));
        assertThat(linear.calculateDerivative(1.0)).isEqualTo(1, offset(0.0001));
        assertThat(linear.calculateDerivative(3.0)).isEqualTo(1, offset(0.0001));
    }
}
