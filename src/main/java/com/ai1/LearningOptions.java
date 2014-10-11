package com.ai1;

import java.util.List;

/**
 * The type Learning options.
 */
public class LearningOptions {

    private final List<Double> inputValues;
    private final double expectedOutput;
    private final double learningRate;
    private double epsilon;

    /**
     * Instantiates a new Learning options.
     *
     * @param inputValues the input values
     * @param expectedOutput the expected output
     * @param learningRate the learning rate
     * @param epsilon the epsilon
     */
    public LearningOptions(List<Double> inputValues, double expectedOutput, double learningRate, double epsilon) {
        this.inputValues = inputValues;
        this.expectedOutput = expectedOutput;
        this.learningRate = learningRate;
        this.epsilon = epsilon;
    }

    public List<Double> getInputValues() {
        return inputValues;
    }

    public double getExpectedOutput() {
        return expectedOutput;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public double getEpsilon() {
        return epsilon;
    }
}