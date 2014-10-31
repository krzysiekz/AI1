package com.ai1.activation;


/**
 * The interface Activation function.
 */
public interface ActivationFunction {

    /**
     * Calculates double.
     *
     * @param inputValue the input value
     * @return the double
     */
    Double calculate(double inputValue);

    Double calculateDerivative(Double inputValue);
}
