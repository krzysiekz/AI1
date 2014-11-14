package net.ai1.neural.activation;

/**
 * The interface represents Activation function.
 */
public interface ActivationFunction {
    /**
     * Activates.
     *
     * @param weightedSum the weighted sum
     * @return the double
     */
    double activate(double weightedSum);

    /**
     * Gets Derivative value.
     *
     * @param weightedSum the weighted sum
     * @return the double
     */
    double derivative(double weightedSum);
}
