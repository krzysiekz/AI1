package com.ai1.teacher;

import com.ai1.LearningEntry;
import com.ai1.LearningEntryCreator;
import com.ai1.LearningOptions;
import com.ai1.Neuron;
import com.ai1.activation.ActivationFunction;
import com.ai1.activation.impl.BinaryStepFunction;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

@RunWith(JUnitParamsRunner.class)
public class NeuronTeacherTest {
    public static final double EPSILON = 0.001;
    public static final double LEARNING_RATE = 0.001;
    public static final boolean BIAS = false;
    public static final int ITERATIONS_LIMIT = 10000;
    //given
    private LearningOptions learningOptions;
    private ActivationFunction activationFunction;
    private Neuron neuron;
    private NeuronTeacher neuronTeacher;

    @Before
    public void setUp() {
        learningOptions = prepareLearningOptions();
        activationFunction = new BinaryStepFunction();
        neuron = new Neuron(activationFunction, BIAS);
        neuronTeacher = new NeuronTeacher(neuron);
    }

    @Test
    public void shouldTeachNeuron() {
        //when
        neuronTeacher.teach(learningOptions);
        //then
        for(LearningEntry learningEntry : learningOptions.getLearningData()) {
            assertThat(learningEntry.getExpectedOutput()).isEqualTo(neuron.activate(learningEntry.getInputValues()), offset(EPSILON));
        }
    }

    @Ignore
    @Test
    @Parameters(method = "positiveClassificationData")
    public void shouldClassifyPointsToOneAfterLearning(Double x, Double y, Double expected) {
        //when
        List<Double> inputValues = new ArrayList<Double>();
        inputValues.add(x);
        inputValues.add(y);
        neuronTeacher.teach(learningOptions);
        assertThat(expected).isEqualTo(neuron.activate(inputValues), offset(EPSILON));
    }

    @Ignore
    @Test
    @Parameters(method = "zeroClassificationData")
    public void shouldClassifyPointsToZeroAfterLearning(Double x, Double y, Double expected) {
        //when
        List<Double> inputValues = new ArrayList<Double>();
        inputValues.add(x);
        inputValues.add(y);
        neuronTeacher.teach(learningOptions);
        //then
        assertThat(expected).isEqualTo(neuron.activate(inputValues), offset(EPSILON));
    }

    private LearningOptions prepareLearningOptions() {
        final LearningEntryCreator learningEntryCreator = new LearningEntryCreator();
        List<LearningEntry> learningEntries = new ArrayList<LearningEntry>();
//        learningEntries.add( getLearningEntry(1.0, 0.0, 2.0));
//        learningEntries.add( getLearningEntry(1.0, 1.0, 1.0));
//        learningEntries.add( getLearningEntry(0.0, 1.0, 3.0));
//        learningEntries.add( getLearningEntry(0.0, 2.0, 2.0));

        learningEntries.add(learningEntryCreator.getLearningEntry(1.0, 0.0, 1.0, 1.0));
        learningEntries.add(learningEntryCreator.getLearningEntry(1.0, 1.0, 1.0, 1.0));

        learningEntries.add(learningEntryCreator.getLearningEntry(0.0, -1.0, 3.0, -1.0));
        learningEntries.add(learningEntryCreator.getLearningEntry(0.0, -1.0, 2.0, 0.0));
        learningEntries.add(learningEntryCreator.getLearningEntry(0.0, 0.0, 0.0, -1.0));
        return new LearningOptions(learningEntries, LEARNING_RATE, EPSILON, ITERATIONS_LIMIT);
    }

    public Object[] positiveClassificationData() {
        return $(
                $(-1.0, -1.0, 1.0),
                $(1.0, -1.0, 1.0),
                $(2.5, 0.0, 1.0)
        );
    }

    public Object[] zeroClassificationData() {
        return $(
                $(2.0, 3.0, 0.0),
                $(2.5, -0.5, 0.0),
                $(3.0, 0.0, 0.0)
        );
    }
}
