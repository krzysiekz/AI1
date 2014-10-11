package com.ai1.activation.impl;

import com.ai1.activation.ActivationFunction;

/**
 * Created by krzysiek on 11.10.14, 11.
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
