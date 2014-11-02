package net.ai1.neural.activation;

public interface ActivationFunction {
    double activate(double weightedSum);
    double derivative(double weightedSum);
}
