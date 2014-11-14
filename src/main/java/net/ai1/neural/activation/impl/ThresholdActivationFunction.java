package net.ai1.neural.activation.impl;

import net.ai1.neural.activation.ActivationFunction;

import java.io.Serializable;

/**
 * The Threshold activation function.
 */
public class ThresholdActivationFunction implements ActivationFunction, Serializable {

    private final double threshold;

    /**
     * Instantiates a new Threshold activation function.
     *
     * @param threshold the threshold
     */
    public ThresholdActivationFunction(double threshold) {
        this.threshold = threshold;
    }

    /**
     * Activates.
     *
     * @param weightedSum the weighted sum
     * @return the double
     */
    public double activate(double weightedSum) {
        return weightedSum > threshold ? 1 : 0;
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
