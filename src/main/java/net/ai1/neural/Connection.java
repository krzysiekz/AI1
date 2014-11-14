package net.ai1.neural;

import java.io.Serializable;

/**
 * The Connection.
 */
public class Connection implements Serializable {

    private final Neuron sourceNeuron;
    private double weight;

    /**
     * Instantiates a new Connection.
     *
     * @param sourceNeuron the source neuron
     * @param weight the weight
     */
    public Connection(Neuron sourceNeuron, double weight) {
        this.sourceNeuron = sourceNeuron;
        this.weight = weight;
    }

    /**
     * Gets source neuron.
     *
     * @return the source neuron
     */
    public Neuron getSourceNeuron() {
        return sourceNeuron;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
