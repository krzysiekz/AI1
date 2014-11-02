package net.ai1.neural.activation.impl;

import net.ai1.neural.activation.ActivationFunction;

import java.io.Serializable;

public class ThresholdActivationFunction implements ActivationFunction, Serializable {

    private final double threshold;

    public ThresholdActivationFunction(double threshold) {
        this.threshold = threshold;
    }

    public double activate(double weightedSum) {
        return weightedSum > threshold ? 1 : 0;
    }

    public double derivative(double weightedSum) {
        return 0;
    }
}
