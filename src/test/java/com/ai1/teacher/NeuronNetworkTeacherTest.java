package com.ai1.teacher;

import com.ai1.*;
import com.ai1.activation.ActivationFunction;
import com.ai1.activation.impl.BinaryStepFunction;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class NeuronNetworkTeacherTest {

    public static final double EPSILON = 0.001;
    public static final double LEARNING_RATE = 0.001;
    public static final boolean BIAS = false;
    public static final int ITERATIONS_LIMIT = 10000;

    @Test
    public void shouldHaveNeuronNetworkSet() {
        //given
        NeuronNetwork neuronNetwork = mock(NeuronNetwork.class);
        //when
        NeuronNetworkTeacher neuronNetworkTeacher = new NeuronNetworkTeacher(neuronNetwork);
        //then
        assertEquals(neuronNetwork, neuronNetworkTeacher.getNeuronNetwork());
    }

    @Ignore
    @Test
    public void shouldTeachNeuronNetwork() {
        //given
        LearningOptions learningOptions = prepareLearningOptions();
        NeuronNetwork neuronNetwork = prepareNeronNetwork();
        NeuronNetworkTeacher neuronNetworkTeacher = new NeuronNetworkTeacher(neuronNetwork);
        //when
        neuronNetworkTeacher.teach(learningOptions);
        //then
        for(LearningEntry learningEntry : learningOptions.getLearningData()) {
            assertThat(learningEntry.getExpectedOutput()).
                    isEqualTo(neuronNetwork.getNetworkOutput(learningEntry.getInputValues()).get(0), offset(EPSILON));
        }
    }

    private NeuronNetwork prepareNeronNetwork() {
        ActivationFunction activationFunction = new BinaryStepFunction();
        NeuronNetwork neuronNetwork = new NeuronNetwork();
        neuronNetwork.addLayer(new NeuronLayer(BIAS, activationFunction, 2));
        neuronNetwork.addLayer(new NeuronLayer(BIAS, activationFunction, 1));
        return neuronNetwork;
    }

    private LearningOptions prepareLearningOptions() {
        final LearningEntryCreator learningEntryCreator = new LearningEntryCreator();
        List<LearningEntry> learningEntries = new ArrayList<LearningEntry>();

        learningEntries.add(learningEntryCreator.getLearningEntry(1.0, 1.0, 0.0));
        learningEntries.add(learningEntryCreator.getLearningEntry(1.0, 0.0, 1.0));

        learningEntries.add(learningEntryCreator.getLearningEntry(0.0, 1.0, 1.0));
        learningEntries.add(learningEntryCreator.getLearningEntry(0.0, 0.0, 0.0));
        return new LearningOptions(learningEntries, LEARNING_RATE, EPSILON, ITERATIONS_LIMIT);
    }
}
