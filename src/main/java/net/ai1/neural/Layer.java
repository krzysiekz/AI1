package net.ai1.neural;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Layer.
 */
public class Layer implements Serializable {

    private final List<Neuron> neurons;
    private Layer previousLayer;
    private Layer nextLayer;
    private Neuron bias;

    private Layer() {
        neurons = new ArrayList<>();
        previousLayer = null;
    }

    /**
     * Instantiates a new Layer.
     *
     * @param previousLayer the previous layer
     */
    public Layer(Layer previousLayer) {
        this();
        this.previousLayer = previousLayer;
    }

    /**
     * Instantiates a new Layer.
     *
     * @param previousLayer the previous layer
     * @param bias the bias
     */
    public Layer(Layer previousLayer, Neuron bias) {
        this(previousLayer);
        this.bias = bias;
        neurons.add(bias);
    }

    /**
     * Gets neurons.
     *
     * @return the neurons
     */
    public List<Neuron> getNeurons() {
        return this.neurons;
    }

    /**
     * Add neuron.
     *
     * @param neuron the neuron
     */
    public void addNeuron(Neuron neuron) {
        neurons.add(neuron);

        if(previousLayer != null) {
            for(Neuron previousLayerNeuron : previousLayer.getNeurons()) {
                neuron.addInput(new Connection(previousLayerNeuron, (Math.random() * 1) - 0.5));
            }
        }
    }

    /**
     * Feed forward.
     */
    public void feedForward() {
        int biasCount = hasBias() ? 1 : 0;
        for(int i = biasCount; i < neurons.size(); i++) {
            neurons.get(i).activate();
        }
    }

    /**
     * Gets next layer.
     *
     * @return the next layer
     */
    public Layer getNextLayer() {
        return nextLayer;
    }

    void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

    /**
     * Is output layer.
     *
     * @return the boolean
     */
    public boolean isOutputLayer() {
        return nextLayer == null;
    }

    /**
     * Has bias.
     *
     * @return the boolean
     */
    public boolean hasBias() {
        return bias != null;
    }
}
