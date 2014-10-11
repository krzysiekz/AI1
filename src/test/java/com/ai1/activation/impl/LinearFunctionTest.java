package com.ai1.activation.impl;

import com.ai1.activation.ActivationFunction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The type Linear function test.
 */
public class LinearFunctionTest {
    @Test
    public void shouldReturnTheSameValue() {
        //given
        ActivationFunction linear = new LinearFunction();
        //then
        assertEquals(5, linear.calculate(5), 0.1);
        assertEquals(10, linear.calculate(10), 0.1);
    }
}
