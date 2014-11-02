package net.ai1.neural;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork implements Serializable {

    private final String name;
    private final List<Layer> layers;
    private Layer inputLayer;
    private Layer outputLayer;

    public NeuralNetwork(String name) {
        this.name = name;
        layers = new ArrayList<Layer>();
    }

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

    public List<Layer> getLayers() {
        return layers;
    }

    public String getName() {
        return name;
    }

    public Layer getInputLayer() {
        return inputLayer;
    }

    public Layer getOutputLayer() {
        return outputLayer;
    }
}
