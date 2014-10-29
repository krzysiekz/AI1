package com.ai1;

public class NeuronNetworkTeacher {
    private final NeuronNetwork neuronNetwork;

    public NeuronNetworkTeacher(NeuronNetwork neuronNetwork) {
        this.neuronNetwork = neuronNetwork;
    }

    public NeuronNetwork getNeuronNetwork() {
        return neuronNetwork;
    }
}
