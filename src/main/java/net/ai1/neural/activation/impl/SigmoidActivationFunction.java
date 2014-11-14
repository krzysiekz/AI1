package net.ai1.neural.activation.impl;

import net.ai1.neural.activation.ActivationFunction;

import java.io.Serializable;

/**
 * The Sigmoid activation function.
 */
public class SigmoidActivationFunction implements ActivationFunction, Serializable {
    /**
     * Activates.
     *
     * @param weightedSum the weighted sum
     * @return the double
     */
    public double activate(double weightedSum) {
        return 1.0 / (1 + Math.exp(-1.0 * weightedSum));
    }

    /**
     * Gets Derivative value.
     *
     * @param weightedSum the weighted sum
     * @return the double
     */
    public double derivative(double weightedSum) {
        return activate(weightedSum) * (1.0 - activate((weightedSum)));
    }

}
