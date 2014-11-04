package net.ai1.neural.integration;

import net.ai1.neural.*;
import net.ai1.neural.activation.impl.ThresholdActivationFunction;
import net.ai1.neural.generator.TrainingData;
import net.ai1.neural.generator.TrainingDataGenerator;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NetworkTrainerIntegrationTests {

    @Test
    public void shouldTeachNeuronForExercise1() {
        //given
        double[][] inputs = {{0,2}, {1,1}, {1,3}, {2,2}, {3,1}};
        double[][] outputs = {{1}, {1}, {0}, {0}, {0}};
        NeuralNetwork singleNeuron = createSingleNeuronWithBias(inputs[0].length);
        NetworkTrainer networkTrainer = new NetworkTrainer(singleNeuron);
        TrainingDataGenerator trainingDataGenerator = mock(TrainingDataGenerator.class);
        TrainingData trainingData = mock(TrainingData.class);
        //when
        when(trainingDataGenerator.getTrainingData()).thenReturn(trainingData);
        when(trainingData.getInputs()).thenReturn(inputs);
        when(trainingData.getOutputs()).thenReturn(outputs);
        networkTrainer.trainNetwork(trainingDataGenerator, new LearningOptions(0.01, 0.1, 0.8, 0, 100));
        //then
        for (int i = 0; i < inputs.length; i++) {
            singleNeuron.setInputs(inputs[i]);
            assertThat(singleNeuron.getOutput()[0]).isEqualTo(outputs[i][0], offset(0.001));
        }

        singleNeuron.setInputs(new double[]{-1, -1});
        assertThat(singleNeuron.getOutput()[0]).isEqualTo(1);
        singleNeuron.setInputs(new double[]{1, -1});
        assertThat(singleNeuron.getOutput()[0]).isEqualTo(1);
//        singleNeuron.setInputs(new double[]{2.5, 0});
//        assertThat(singleNeuron.getOutput()[0]).isEqualTo(1);
        singleNeuron.setInputs(new double[]{2, 3});
        assertThat(singleNeuron.getOutput()[0]).isEqualTo(0);
//        singleNeuron.setInputs(new double[]{2.5, -0.5});
//        assertThat(singleNeuron.getOutput()[0]).isEqualTo(0);
        singleNeuron.setInputs(new double[]{3, 0});
        assertThat(singleNeuron.getOutput()[0]).isEqualTo(0);
    }

    @Ignore
    @Test
    public void shouldTeachNeuronForExercise2() {
        //given
        double[][] inputs = {{0,2}, {1,1}, {1,3}, {2,2}, {0,0}};
        double[][] outputs = {{1}, {1}, {0}, {0}, {0}};
        NeuralNetwork singleNeuron = createSingleNeuronWithBias(inputs[0].length);
        NetworkTrainer networkTrainer = new NetworkTrainer(singleNeuron);
        TrainingDataGenerator trainingDataGenerator = mock(TrainingDataGenerator.class);
        TrainingData trainingData = mock(TrainingData.class);
        //when
        when(trainingDataGenerator.getTrainingData()).thenReturn(trainingData);
        when(trainingData.getInputs()).thenReturn(inputs);
        when(trainingData.getOutputs()).thenReturn(outputs);
        networkTrainer.trainNetwork(trainingDataGenerator, new LearningOptions(0.01, 0.1, 0.8, 0, 100));
        //then
        for (int i = 0; i < inputs.length; i++) {
            singleNeuron.setInputs(inputs[i]);
            assertThat(singleNeuron.getOutput()[0]).isEqualTo(outputs[i][0], offset(0.001));
        }
    }

    @Test
    public void shouldTeachNeuronForExercise3() {
        //given
        double[][] inputs = {{0,1,1}, {1,1,1}, {-1,3,-1}, {-1,2,0}, {0,0,-1}};
        double[][] outputs = {{1}, {1}, {0}, {0}, {0}};
        NeuralNetwork singleNeuron = createSingleNeuron(inputs[0].length);
        NetworkTrainer networkTrainer = new NetworkTrainer(singleNeuron);
        TrainingDataGenerator trainingDataGenerator = mock(TrainingDataGenerator.class);
        TrainingData trainingData = mock(TrainingData.class);
        //when
        when(trainingDataGenerator.getTrainingData()).thenReturn(trainingData);
        when(trainingData.getInputs()).thenReturn(inputs);
        when(trainingData.getOutputs()).thenReturn(outputs);
        networkTrainer.trainNetwork(trainingDataGenerator, new LearningOptions(0.01, 0.1, 0.8, 0, 100));
        //then
        for (int i = 0; i < inputs.length; i++) {
            singleNeuron.setInputs(inputs[i]);
            assertThat(singleNeuron.getOutput()[0]).isEqualTo(outputs[i][0], offset(0.001));
        }
    }

    private static NeuralNetwork createSingleNeuronWithBias(int numberOfInputs) {
        NeuralNetwork neuralNetwork = new NeuralNetwork("Single Neuron");
        Neuron inputBias = new Neuron(new ThresholdActivationFunction(0));
        inputBias.setOutput(1);
        Layer inputLayer = new Layer(null, inputBias);
        for (int i = 0; i < numberOfInputs; i++) {
            Neuron a = new Neuron(new ThresholdActivationFunction(0));
            a.setOutput(0);
            inputLayer.addNeuron(a);
        }
        Layer outputLayer = getOutputLayer(inputLayer);
        neuralNetwork.addLayer(inputLayer);
        neuralNetwork.addLayer(outputLayer);

        return neuralNetwork;
    }

    private static Layer getOutputLayer(Layer inputLayer) {
        Layer outputLayer = new Layer(inputLayer);
        Neuron neuron = new Neuron(new ThresholdActivationFunction(0));
        outputLayer.addNeuron(neuron);
        return outputLayer;
    }

    private static NeuralNetwork createSingleNeuron(int numberOfInputs) {
        NeuralNetwork neuralNetwork = new NeuralNetwork("Single Neuron");
        Layer inputLayer = new Layer(null);
        for (int i = 0; i < numberOfInputs; i++) {
            Neuron a = new Neuron(new ThresholdActivationFunction(0));
            a.setOutput(0);
            inputLayer.addNeuron(a);
        }
        Layer outputLayer = getOutputLayer(inputLayer);
        neuralNetwork.addLayer(inputLayer);
        neuralNetwork.addLayer(outputLayer);

        return neuralNetwork;
    }
}
