package net.ai1.neural.error.impl;

import net.ai1.neural.error.ErrorCalculator;

/**
 * The Square error calculator.
 */
public class SquareErrorCalculator implements ErrorCalculator {
    /**
     * Calculates error.
     *
     * @param actual the actual
     * @param expected the expected
     * @return the error value.
     */
    @Override
    public Double calculate(double[] actual, double[] expected) {
        if (actual.length != expected.length) {
            throw new IllegalArgumentException("The lengths of the actual and expected value arrays must be equal");
        }
        double sum = 0;
        for (int i = 0; i < expected.length; i++) {
            sum += Math.pow(expected[i] - actual[i], 2);
        }
        return sum / 2;
    }
}
