package net.ai1.neural.activation.impl;

import net.ai1.neural.activation.ActivationFunction;

import java.io.Serializable;

public class SigmoidActivationFunction implements ActivationFunction, Serializable {
    public double activate(double weightedSum) {
        return 1.0 / (1 + Math.exp(-1.0 * weightedSum));
    }

    public double derivative(double weightedSum) {
        return activate(weightedSum) * (1.0 - activate((weightedSum)));
    }

}
