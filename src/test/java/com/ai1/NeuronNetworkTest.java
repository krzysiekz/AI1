package com.ai1;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class NeuronNetworkTest {

    NeuronNetwork neuronNetwork;

    @Before
    public void setUp() {
        neuronNetwork = new NeuronNetwork();
    }

    @Test
    public void shouldHaveEmptyLayerListAfterInitialization() {
        //when
        List<NeuronLayer> layers = neuronNetwork.getLayers();
        //then
        assertTrue(layers.isEmpty());
    }

    @Test
    public void shouldHaveOneLayerWhenAdded() {
        //given
        NeuronLayer firstLayer = mock(NeuronLayer.class, "firstLayer");
        //when
        neuronNetwork.addLayer(firstLayer);
        //then
        assertEquals(1, neuronNetwork.getLayers().size());
        assertTrue(neuronNetwork.getLayers().get(0).equals(firstLayer));
    }

    @Test
    public void shouldPreserveOrderOfAddedLayers() {
        //given
        NeuronLayer firstLayer = mock(NeuronLayer.class, "firstLayer");
        NeuronLayer secondLayer = mock(NeuronLayer.class, "secondLayer");
        //when
        neuronNetwork.addLayer(firstLayer);
        neuronNetwork.addLayer(secondLayer);
        //then
        assertEquals(2, neuronNetwork.getLayers().size());
        assertTrue(neuronNetwork.getLayers().get(0).equals(firstLayer));
        assertTrue(neuronNetwork.getLayers().get(1).equals(secondLayer));
    }
}
