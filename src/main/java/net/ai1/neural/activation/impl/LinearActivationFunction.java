package net.ai1.neural.activation.impl;

import net.ai1.neural.activation.ActivationFunction;

import java.io.Serializable;

/**
 * The Linear activation function.
 */
public class LinearActivationFunction implements ActivationFunction, Serializable {
    /**
     * Activates.
     *
     * @param weightedSum the weighted sum
     * @return the double
     */
    public double activate(double weightedSum) {
        return weightedSum;
    }

    /**
     * Gets derivative value.
     *
     * @param weightedSum the weighted sum
     * @return the double
     */
    public double derivative(double weightedSum) {
        return 1;
    }

}
