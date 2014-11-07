package net.ai1.neural.error.impl;

import net.ai1.neural.error.ErrorCalculator;

public class SquareErrorCalculator implements ErrorCalculator {
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
