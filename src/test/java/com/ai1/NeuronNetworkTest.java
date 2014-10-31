package com.ai1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        assertThat(layers.isEmpty()).isTrue();
    }

    @Test
    public void shouldHaveOneLayerWhenAdded() {
        //when
        neuronNetwork.addLayer(firstLayer);
        //then
        assertThat(neuronNetwork.getLayers()).hasSize(1).contains(firstLayer);
    }

    @Test
    public void shouldPreserveOrderOfAddedLayers() {
        //when
        neuronNetwork.addLayer(firstLayer);
        neuronNetwork.addLayer(secondLayer);
        //then
        assertThat(neuronNetwork.getLayers()).hasSize(2).contains(firstLayer, secondLayer);
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
        assertThat(output).isEqualTo(inputForFirstLayer);
    }

    private List<Double> prepareDoubleList(Double...values) {
        List<Double> inputForFirstLayer = new ArrayList<Double>();
        Collections.addAll(inputForFirstLayer, values);
        return inputForFirstLayer;
    }
}
