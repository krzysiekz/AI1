package net.ai1.neural;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The Neural network.
 */
public class NeuralNetwork implements Serializable {

    private final String name;
    private final List<Layer> layers;
    private Layer inputLayer;
    private Layer outputLayer;

    /**
     * Instantiates a new Neural network.
     *
     * @param name the name
     */
    public NeuralNetwork(String name) {
        this.name = name;
        layers = new ArrayList<>();
    }

    /**
     * Adds layer.
     *
     * @param layer the layer
     */
    public void addLayer(Layer layer) {
        layers.add(layer);

        if(layers.size() == 1) {
            inputLayer = layer;
        }

        if(layers.size() > 1) {
            //clear the outputLayer flag on the previous outputLayer layer, but only if we have more than 1 layer
            Layer previousLayer = layers.get(layers.size() - 2);
            previousLayer.setNextLayer(layer);
        }

        outputLayer = layers.get(layers.size() - 1);
    }

    /**
     * Sets inputs.
     *
     * @param inputs the inputs
     */
    public void setInputs(double[] inputs) {
        if(inputLayer != null) {
            int biasCount = inputLayer.hasBias() ? 1 : 0;
            if(inputLayer.getNeurons().size() - biasCount != inputs.length) {
                throw new IllegalArgumentException("The number of inputs must equal the number of neurons in the inputLayer layer");
            } else {
                List<Neuron> neurons = inputLayer.getNeurons();
                for(int i = biasCount; i < neurons.size(); i++) {
                    neurons.get(i).setOutput(inputs[i - biasCount]);
                }
            }
        }
    }

    /**
     * Get output.
     *
     * @return the double [ ]
     */
    public double[] getOutput() {

        double[] outputs = new double[outputLayer.getNeurons().size()];

        for(int i = 1; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            layer.feedForward();
        }

        int i = 0;
        for(Neuron neuron : outputLayer.getNeurons()) {
            outputs[i] = neuron.getOutput();
            i++;
        }

        return outputs;
    }

    /**
     * Gets layers.
     *
     * @return the layers
     */
    public List<Layer> getLayers() {
        return layers;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets input layer.
     *
     * @return the input layer
     */
    public Layer getInputLayer() {
        return inputLayer;
    }

    /**
     * Gets output layer.
     *
     * @return the output layer
     */
    public Layer getOutputLayer() {
        return outputLayer;
    }

    /**
     * Gets weights.
     *
     * @return the weights
     */
    public List<Double> getWeights() {
        List<Double> weights = new LinkedList<>();
        for(Layer layer : layers) {
            for(Neuron neuron : layer.getNeurons()) {
                for(Connection connection: neuron.getInputs()) {
                    weights.add(connection.getWeight());
                }
            }
        }
        return weights;
    }
}
