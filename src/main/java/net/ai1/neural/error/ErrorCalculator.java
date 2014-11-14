package net.ai1.neural.error;

/**
 * The interface Error calculator.
 */
public interface ErrorCalculator {
    /**
     * Calculates error.
     *
     * @param actual the actual
     * @param expected the expected
     * @return the double
     */
    Double calculate(double[] actual, double[] expected);
}
