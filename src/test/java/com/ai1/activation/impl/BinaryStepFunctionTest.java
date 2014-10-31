package com.ai1.activation.impl;

import com.ai1.activation.ActivationFunction;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

/**
 * The type Binary step function test.
 */
public class BinaryStepFunctionTest {

    private static final double DOUBLE_DELTA = 0.0001;
    private ActivationFunction activationFunction;

    @Before
    public void setUp() {
        activationFunction = new  BinaryStepFunction();
    }

    @Test
    public void shouldReturnZeroWhenValueLessThanZero() {
        //when
        Double returnValue = activationFunction.calculate(-5);
        //then
        assertThat(returnValue).isEqualTo(0, offset(DOUBLE_DELTA));
    }

    @Test
    public void shouldReturnOneWhenValueHigherThanZero() {
        //when
        Double returnValue = activationFunction.calculate(5);
        //then
        assertThat(returnValue).isEqualTo(1, offset(DOUBLE_DELTA));
    }

    @Test
    public void shouldReturnOneWhenValueZero() {
        //when
        Double returnValue = activationFunction.calculate(0);
        //then
        assertThat(returnValue).isEqualTo(1, offset(DOUBLE_DELTA));
    }
}
