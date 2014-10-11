package com.ai1;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The type Neuron teacher.
 */
public class NeuronTeacher {

    private final Neuron neuronToTeach;

    public NeuronTeacher(Neuron neuron) {
        this.neuronToTeach = neuron;
    }

    /**
     * Teaches neuron.
     *
     * @param options the options
     */
    public void teach(LearningOptions options) {
        double value = neuronToTeach.activate(options.getInputValues());
        int iterations = 0;
        while(Math.abs(value - options.getExpectedOutput()) > options.getEpsilon()) {
            iterations++;
            neuronToTeach.adjustWeights(options);
            value = neuronToTeach.activate(options.getInputValues());
        }

        displayResults(value, iterations);
    }

    /**
     * Display results.
     *
     * @param value the value
     * @param iterations the iterations
     */
    private void displayResults(double value, int iterations) {
        Logger.getAnonymousLogger().log(Level.INFO, "Iterations: " + String.valueOf(iterations));
        for(Double weight : neuronToTeach.getWeights()) {
            Logger.getAnonymousLogger().log(Level.INFO, String.valueOf(weight));
        }
        Logger.getAnonymousLogger().log(Level.INFO, "Result: " + String.valueOf(value));
    }
}
