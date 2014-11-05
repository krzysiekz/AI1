package net.ai1.neural;

import net.ai1.neural.activation.ActivationFunction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Neuron implements Serializable {

    private final List<Connection> inputs;
    private final ActivationFunction activationFunction;
    private double output;
    private double derivative;
    private double weightedSum;
    private double error;

    public Neuron(ActivationFunction activationFunction) {
        inputs = new ArrayList<>();
        this.activationFunction = activationFunction;
        error = 0;
    }

    public void addInput(Connection input) {
        inputs.add(input);
    }

    public List<Connection> getInputs() {
        return this.inputs;
    }

    private void calculateWeightedSum() {
        weightedSum = 0;
        for(Connection connection : inputs) {
            weightedSum += connection.getWeight() * connection.getSourceNeuron().getOutput();
        }
    }

    public void activate() {
        calculateWeightedSum();
        output = activationFunction.activate(weightedSum);
        derivative = activationFunction.derivative(weightedSum);
    }

    public double getOutput() {
        return this.output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double getDerivative() {
        return this.derivative;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
