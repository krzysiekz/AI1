package com.ai1;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The type represent class that teaches neuron.
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
        int iterations = 0;
        while(iterations < options.getIterationsLimit()) {
            iterations++;
            int errorCount = teachBasedOnEachEntry(options);

            if(errorCount == 0) {
                break;
            }
        }
        displayResults(iterations);
    }

    /**
     * Teach based on each entry.
     *
     * @param options the options
     * @return the int
     */
    private int teachBasedOnEachEntry(LearningOptions options) {
        int errorCount = 0;
        for (LearningEntry learningEntry : options.getLearningData()) {
            double value = neuronToTeach.activate(learningEntry.getInputValues());

            if(Math.abs(value - learningEntry.getExpectedOutput()) > options.getEpsilon()) {
                errorCount++;
            }
            neuronToTeach.adjustWeights(learningEntry, options.getLearningRate());
        }
        return errorCount;
    }

    /**
     * Display results.
     *
     * @param iterations the iterations
     */
    private void displayResults(int iterations) {
        Logger.getAnonymousLogger().log(Level.INFO, "Iterations: " + String.valueOf(iterations));
        Logger.getAnonymousLogger().log(Level.INFO, String.valueOf(neuronToTeach.getWeights()));
    }
}
