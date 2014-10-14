package com.ai1;

import java.util.List;

/**
 * The type represents options used in learning process.
 */
public class LearningOptions {

    private final List<LearningEntry> learningData;
    private final double learningRate;
    private double epsilon;
    private int iterationsLimit;

    /**
     * Instantiates a new Learning options.
     *
     * @param learningData the learning data
     * @param learningRate the learning rate
     * @param epsilon the epsilon
     */
    public LearningOptions(List<LearningEntry> learningData, double learningRate, double epsilon, int iterationsLimit) {
        this.learningData = learningData;
        this.learningRate = learningRate;
        this.epsilon = epsilon;
        this.iterationsLimit = iterationsLimit;
    }

    public List<LearningEntry> getLearningData() {
        return learningData;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public int getIterationsLimit() {
        return iterationsLimit;
    }
}