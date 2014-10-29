package com.ai1.teacher;

import com.ai1.NeuronNetwork;

public class NeuronNetworkTeacher {
    private final NeuronNetwork neuronNetwork;

    public NeuronNetworkTeacher(NeuronNetwork neuronNetwork) {
        this.neuronNetwork = neuronNetwork;
    }

    public NeuronNetwork getNeuronNetwork() {
        return neuronNetwork;
    }
}
