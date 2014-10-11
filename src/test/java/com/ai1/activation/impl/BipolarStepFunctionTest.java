package com.ai1.activation.impl;

import com.ai1.activation.ActivationFunction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by krzysiek on 11.10.14.
 */
public class BipolarStepFunctionTest {

    public static final double DOUBLE_DELTA = 0.0001;
    ActivationFunction activationFunction;

    @Before
    public void setUp() {
        activationFunction = new  BipolarStepFunction();
    }

    @Test
    public void shouldReturnMinusOneWhenValueLessThanZero() {
        //when
        Double returnValue = activationFunction.calculate(-5);
        //then
        assertEquals(-1, returnValue, DOUBLE_DELTA);
    }

    @Test
    public void shouldReturnOneWhenValueHigherThanZero() {
        //when
        Double returnValue = activationFunction.calculate(5);
        //then
        assertEquals(1, returnValue, DOUBLE_DELTA);
    }

    @Test
    public void shouldReturnOneWhenValueZero() {
        //when
        Double returnValue = activationFunction.calculate(0);
        //then
        assertEquals(1, returnValue, 0.0001);
    }
}
