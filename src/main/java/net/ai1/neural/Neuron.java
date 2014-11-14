package net.ai1.neural;

import net.ai1.neural.activation.ActivationFunction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Neuron.
 */
public class Neuron implements Serializable {

    private final List<Connection> inputs;
    private final ActivationFunction activationFunction;
    private double output;
    private double derivative;
    private double weightedSum;
    private double error;

    /**
     * Instantiates a new Neuron.
     *
     * @param activationFunction the activation function
     */
    public Neuron(ActivationFunction activationFunction) {
        inputs = new ArrayList<>();
        this.activationFunction = activationFunction;
        error = 0;
    }

    /**
     * Adds input.
     *
     * @param input the input
     */
    public void addInput(Connection input) {
        inputs.add(input);
    }

    /**
     * Gets inputs.
     *
     * @return the inputs
     */
    public List<Connection> getInputs() {
        return this.inputs;
    }

    private void calculateWeightedSum() {
        weightedSum = 0;
        for(Connection connection : inputs) {
            weightedSum += connection.getWeight() * connection.getSourceNeuron().getOutput();
        }
    }

    /**
     * Activates.
     */
    public void activate() {
        calculateWeightedSum();
        output = activationFunction.activate(weightedSum);
        derivative = activationFunction.derivative(weightedSum);
    }

    /**
     * Gets output.
     *
     * @return the output
     */
    public double getOutput() {
        return this.output;
    }

    /**
     * Sets output.
     *
     * @param output the output
     */
    public void setOutput(double output) {
        this.output = output;
    }

    /**
     * Gets derivative.
     *
     * @return the derivative
     */
    public double getDerivative() {
        return this.derivative;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public double getError() {
        return error;
    }

    /**
     * Sets error.
     *
     * @param error the error
     */
    public void setError(double error) {
        this.error = error;
    }
}
