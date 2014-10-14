package com.ai1;

import com.ai1.activation.ActivationFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Neuron.
 */
public class Neuron {
    private static final double BIAS_INPUT = -1.0;
    private List<Double> weights;
    private ActivationFunction activationFunction;
    private boolean bias;

    public Neuron(ActivationFunction activationFunction, boolean bias) {
        this.activationFunction = activationFunction;
        this.bias = bias;
    }

    /**
     * Fills initial weights.
     *
     * @param numberOfInputs the number of inputs
     * @return the list
     */
    private void fillInitialWeights(int numberOfInputs) {
        weights = new ArrayList<Double>();
        Random random = new Random();
        if(bias) {
            numberOfInputs++;
        }
        for (int i = 0; i < numberOfInputs; i++) {
            weights.add(random.nextFloat() - 0.5);
        }
        Logger.getAnonymousLogger().log(Level.INFO, String.valueOf(weights));
    }

    public List<Double> getWeights() {
        return weights;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    /**
     * Activates neuron.
     *
     * @param inputValues the input values
     * @return the double
     */
    public double activate(List<Double> inputValues) {
        setWeightsIfNeeded(inputValues);
        double sum = calculateSum(inputValues);
        return activationFunction.calculate(sum);
    }

    /**
     * Sets weights if needed.
     *
     * @param inputValues the input values
     */
    private void setWeightsIfNeeded(List<Double> inputValues) {
        if(weights == null) {
            fillInitialWeights(inputValues.size());
        }
    }

    /**
     * Calculates sum.
     *
     * @param inputValues the input values
     * @return the double
     */
    private double calculateSum(List<Double> inputValues) {
        double sum = 0;
        List<Double> modifiedInputValues = addBiasInputIfNeeded(inputValues);
        for (Double value : modifiedInputValues) {
            sum = sum + (value * weights.get(modifiedInputValues.indexOf(value)));
        }
        return sum;
    }

    /**
     * Adjust weights.
     *
     * @param learningEntry the learning entry
     * @param learningRate the learning rate
     */
    public void adjustWeights(LearningEntry learningEntry, Double learningRate) {
        Double currentValue = activate(learningEntry.getInputValues());
        List<Double> inputValues = addBiasInputIfNeeded(learningEntry.getInputValues());
        for (Double weight : weights) {
            int index = weights.indexOf(weight);
            double newWeightValue = weight + (learningRate *
                    (learningEntry.getExpectedOutput() - currentValue) * inputValues.get(index));
            weights.set(index, newWeightValue);
        }
    }

    private List<Double> addBiasInputIfNeeded(List<Double> inputValues) {
        if(bias) {
            List<Double> modifiedInputValues = new ArrayList<Double>(inputValues);
            modifiedInputValues.add(0, BIAS_INPUT);
            return modifiedInputValues;
        }
        return inputValues;
    }
}
