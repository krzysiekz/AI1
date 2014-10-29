package com.ai1;

import com.ai1.activation.ActivationFunction;

import java.util.ArrayList;
import java.util.List;

public class NeuronLayer {

    List<Neuron> neurons;

    public NeuronLayer(boolean bias, ActivationFunction activationFunction, int numberOfNeurons) {
        neurons = new ArrayList<Neuron>();
        for (int i = 0; i < numberOfNeurons; i++) {
            neurons.add(new Neuron(activationFunction, bias));
        }
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }
}
