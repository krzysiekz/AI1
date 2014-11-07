package net.ai1.neural.error;

public interface ErrorCalculator {
    Double calculate(double[] actual, double[] expected);
}
