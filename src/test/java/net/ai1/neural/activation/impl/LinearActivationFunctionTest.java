package net.ai1.neural.activation.impl;

import net.ai1.neural.activation.ActivationFunction;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

public class LinearActivationFunctionTest {

    ActivationFunction linear;

    @Before
    public void setUp() {
        linear = new LinearActivationFunction();
    }

    @Test
    public void shouldReturnTheSameValue() {
        //then
        assertThat(linear.activate(5)).isEqualTo(5, offset(0.1));
        assertThat(linear.activate(10)).isEqualTo(10, offset(0.1));
    }

    @Test
    public void derivativeShouldReturnOne() {
        //then
        assertThat(linear.derivative(5)).isEqualTo(1, offset(0.1));
        assertThat(linear.derivative(10)).isEqualTo(1, offset(0.1));
    }
}