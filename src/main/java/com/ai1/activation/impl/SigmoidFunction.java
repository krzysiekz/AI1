package com.ai1.activation.impl;

import com.ai1.activation.ActivationFunction;

public class SigmoidFunction implements ActivationFunction {
    @Override
    public Double calculate(double inputValue) {
        return 1 / (1 + Math.exp(-inputValue));
    }
}
