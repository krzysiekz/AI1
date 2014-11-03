package net.ai1.neural.activation.impl;

import net.ai1.neural.activation.ActivationFunction;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

public class ThresholdActivationFunctionTest {

    private static final double DOUBLE_DELTA = 0.0001;

    @Test
    public void shouldReturnProperValuesWhenThresholdSet() {
        //when
        ActivationFunction activationFunction = new ThresholdActivationFunction(0.0);
        //then
        assertThat(activationFunction.activate(1)).isEqualTo(1, offset(DOUBLE_DELTA));
        assertThat(activationFunction.activate(2)).isEqualTo(1, offset(DOUBLE_DELTA));
        assertThat(activationFunction.activate(0)).isEqualTo(0, offset(DOUBLE_DELTA));
        assertThat(activationFunction.activate(-1)).isEqualTo(0, offset(DOUBLE_DELTA));
    }

    @Test
    public void derivativeShouldReturnOne() {
        //when
        ActivationFunction activationFunction = new ThresholdActivationFunction(0.0);
        //then
        assertThat(activationFunction.derivative(1)).isEqualTo(1, offset(DOUBLE_DELTA));
        assertThat(activationFunction.derivative(2)).isEqualTo(1, offset(DOUBLE_DELTA));
        assertThat(activationFunction.derivative(0)).isEqualTo(1, offset(DOUBLE_DELTA));
        assertThat(activationFunction.derivative(-1)).isEqualTo(1, offset(DOUBLE_DELTA));
    }
}