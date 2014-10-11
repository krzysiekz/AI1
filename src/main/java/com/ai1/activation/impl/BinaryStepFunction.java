package com.ai1.activation.impl;

import com.ai1.activation.ActivationFunction;

/**
 * The type Binary step function.
 */
public class BinaryStepFunction implements ActivationFunction {

    @Override
    public Double calculate(double inputValue) {
        if(inputValue < 0) {
            return (double) 0;
        } else {
            return (double) 1;
        }
    }
}
