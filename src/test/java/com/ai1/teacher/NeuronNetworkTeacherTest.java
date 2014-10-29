package com.ai1.teacher;

import com.ai1.NeuronNetwork;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class NeuronNetworkTeacherTest {

    @Test
    public void shouldHaveNeuronNetworkSet() {
        //given
        NeuronNetwork neuronNetwork = mock(NeuronNetwork.class);
        //when
        NeuronNetworkTeacher neuronNetworkTeacher = new NeuronNetworkTeacher(neuronNetwork);
        //then
        assertEquals(neuronNetwork, neuronNetworkTeacher.getNeuronNetwork());
    }

    @Test
    public void shouldTeachNetwork() {
        //given

        //when
        //then
    }
}
