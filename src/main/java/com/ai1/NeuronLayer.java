package com.ai1;

import com.ai1.activation.ActivationFunction;

import java.util.ArrayList;
import java.util.List;

public class NeuronLayer {

    List<Neuron> neurons;

    public NeuronLayer(boolean bias, ActivationFunction activationFunction, int numberOfNeurons) {
        neurons = new ArrayList<Neuron>();
        for (int i = 0; i < numberOfNeurons; i++) {
            neurons.add(createNeuron(bias, activationFunction));
        }
    }

    Neuron createNeuron(boolean bias, ActivationFunction activationFunction) {
        return new Neuron(activationFunction, bias);
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public List<Double> getOutput(List<Double> input) {
        List<Double> output = new ArrayList<Double>();
        for (Neuron neuron : neurons) {
            output.add(neuron.activate(input));
        }
        return output;
    }
}
