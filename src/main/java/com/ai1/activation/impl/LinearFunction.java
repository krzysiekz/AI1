package com.ai1.activation.impl;

import com.ai1.activation.ActivationFunction;


/**
 * The Linear activation function.
 */
public class LinearFunction implements ActivationFunction {
    @Override
    public Double calculate(double inputValue) {
        return inputValue;
    }
}
