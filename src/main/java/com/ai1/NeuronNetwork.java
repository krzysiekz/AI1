package com.ai1;

import java.util.LinkedList;
import java.util.List;

public class NeuronNetwork {
    private List<NeuronLayer> layers;

    public NeuronNetwork() {
        this.layers = new LinkedList<NeuronLayer>();
    }

    public List<NeuronLayer> getLayers() {
        return layers;
    }

    public void addLayer(NeuronLayer layer) {
        layers.add(layer);
    }
}
