package com.ai1;

import java.util.List;

/**
 * The type represent one set on input values and expected output value.
 */
public class LearningEntry {
    private final List<Double> inputValues;
    private final Double expectedOutput;

    public LearningEntry(List<Double> inputValues, Double expectedOutput) {
        this.inputValues = inputValues;
        this.expectedOutput = expectedOutput;
    }

    public List<Double> getInputValues() {
        return inputValues;
    }

    public Double getExpectedOutput() {
        return expectedOutput;
    }
}
