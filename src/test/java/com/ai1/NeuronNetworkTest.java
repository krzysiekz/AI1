package com.ai1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NeuronNetworkTest {

    NeuronNetwork neuronNetwork;
    NeuronLayer firstLayer;
    NeuronLayer secondLayer;

    @Before
    public void setUp() {
        neuronNetwork = new NeuronNetwork();
        firstLayer = mock(NeuronLayer.class, "firstLayer");
        secondLayer = mock(NeuronLayer.class, "secondLayer");
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
        //when
        neuronNetwork.addLayer(firstLayer);
        //then
        assertEquals(1, neuronNetwork.getLayers().size());
        assertTrue(neuronNetwork.getLayers().get(0).equals(firstLayer));
    }

    @Test
    public void shouldPreserveOrderOfAddedLayers() {
        //when
        neuronNetwork.addLayer(firstLayer);
        neuronNetwork.addLayer(secondLayer);
        //then
        assertEquals(2, neuronNetwork.getLayers().size());
        assertTrue(neuronNetwork.getLayers().get(0).equals(firstLayer));
        assertTrue(neuronNetwork.getLayers().get(1).equals(secondLayer));
    }

    @Test
    public void shouldCalculateNetworkOutput() {
        //given
        List<Double> inputForFirstLayer = prepareDoubleList(1.0, 2.0);
        List<Double> outputFromFirstLayer = prepareDoubleList(3.0, 4.0);
        //when
        when(firstLayer.getOutput(inputForFirstLayer)).thenReturn(outputFromFirstLayer);
        when(secondLayer.getOutput(outputFromFirstLayer)).thenReturn(inputForFirstLayer);
        neuronNetwork.addLayer(firstLayer);
        neuronNetwork.addLayer(secondLayer);
        List<Double> output = neuronNetwork.getNetworkOutput(inputForFirstLayer);
        //then
        verify(firstLayer).getOutput(inputForFirstLayer);
        verify(secondLayer).getOutput(outputFromFirstLayer);
        assertEquals(inputForFirstLayer, output);
    }

    private List<Double> prepareDoubleList(Double...values) {
        List<Double> inputForFirstLayer = new ArrayList<Double>();
        Collections.addAll(inputForFirstLayer, values);
        return inputForFirstLayer;
    }
}
