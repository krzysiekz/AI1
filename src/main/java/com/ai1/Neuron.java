package com.ai1;

import com.ai1.activation.ActivationFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Neuron.
 */
public class Neuron {
    private int numberOfInputs;
    private List<Double> weights;
    private ActivationFunction activationFunction;

    public Neuron(int numberOfInputs, ActivationFunction activationFunction) {
        this.numberOfInputs = numberOfInputs;
        this.weights = fillInitialWeights(numberOfInputs);
        this.activationFunction = activationFunction;
    }

    /**
     * Fills initial weights.
     *
     * @param numberOfInputs the number of inputs
     * @return the list
     */
    private List<Double> fillInitialWeights(int numberOfInputs) {
        List<Double> initialWeights = new ArrayList<Double>();
        Random random = new Random();
        for (int i = 0; i < numberOfInputs; i++) {
            initialWeights.add(random.nextFloat() - 0.5);
        }
        return initialWeights;
    }

    public int getNumberOfInputs() {
        return numberOfInputs;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    /**
     * Activates double.
     *
     * @param inputValues the input values
     * @return the double
     */
    public double activate(List<Double> inputValues) {
        double sum = calculateSum(inputValues);
        return activationFunction.calculate(sum);
    }

    /**
     * Calculates sum.
     *
     * @param inputValues the input values
     * @return the double
     */
    private double calculateSum(List<Double> inputValues) {
        double sum = 0;
        for (Double value : inputValues) {
            sum = sum + (value * weights.get(inputValues.indexOf(value)));
        }
        return sum;
    }

    /**
     * Adjust weights.
     *
     * @param learningOptions the learning options
     */
    public void adjustWeights(LearningOptions learningOptions) {
        Double currentValue = activate(learningOptions.getInputValues());
        for (Double weight : weights) {
            int index = weights.indexOf(weight);
            double newWeightValue = weight + (learningOptions.getLearningRate() *
                    (learningOptions.getExpectedOutput() - currentValue) * learningOptions.getInputValues().get(index));
            weights.set(index, newWeightValue);
        }
    }
}
