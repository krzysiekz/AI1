package com.ai1;

import com.ai1.activation.ActivationFunction;
import com.ai1.activation.impl.LinearFunction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NeuronTeacherTest {
    public static final double EPSILON = 0.1;
    public static final double LEARNING_RATE = 0.1;
    public static final double EXPECTED_OUTPUT = 17.0;

    @Test
    public void shouldTeachNeuron() {
        //given
        List<Double> inputValues = prepareInputValues();
        ActivationFunction activationFunction = new LinearFunction();
        Neuron neuron = new Neuron(inputValues.size(), activationFunction);
        LearningOptions options = new LearningOptions(inputValues, EXPECTED_OUTPUT, LEARNING_RATE, EPSILON);
        NeuronTeacher neuronTeacher = new NeuronTeacher(neuron);
        //when
        neuronTeacher.teach(options);
        //then
        assertEquals(EXPECTED_OUTPUT, neuron.activate(inputValues), EPSILON);
    }

    /**
     * Prepare input values.
     *
     * @return the list
     */
    private List<Double> prepareInputValues() {
        List<Double> inputValues = new ArrayList<Double>();
        inputValues.add(1.0);
        inputValues.add(2.0);
        inputValues.add(3.0);
        return inputValues;
    }
}
