package net.ai1.neural;

import java.io.Serializable;

public class Connection implements Serializable {

    private final Neuron sourceNeuron;
    private double weight;

    public Connection(Neuron sourceNeuron, double weight) {
        this.sourceNeuron = sourceNeuron;
        this.weight = weight;
    }

    public Neuron getSourceNeuron() {
        return sourceNeuron;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
