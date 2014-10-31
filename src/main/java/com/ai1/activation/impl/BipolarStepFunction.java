package com.ai1.activation.impl;

/**
 * The type Bipolar step function.
 */
public class BipolarStepFunction implements com.ai1.activation.ActivationFunction {

    @Override
    public Double calculate(double inputValue) {
        if(inputValue < 0) {
            return (double) -1;
        } else {
            return (double) 1;
        }
    }

    @Override
    public Double calculateDerivative(Double inputValue) {
        return 1.0;
    }
}
