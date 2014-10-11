package com.ai1.activation.impl;

/**
 * Created by krzysiek on 11.10.14.
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
}
