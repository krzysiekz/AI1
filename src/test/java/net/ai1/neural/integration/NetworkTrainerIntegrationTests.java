package net.ai1.neural.integration;

import net.ai1.neural.*;
import net.ai1.neural.activation.impl.ThresholdActivationFunction;
import net.ai1.neural.generator.TrainingData;
import net.ai1.neural.generator.TrainingDataGenerator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NetworkTrainerIntegrationTests {

    @Test
    public void shouldTeachNeuronForExercise1() {
        //given
        NeuralNetwork singleNeuron = createSingleNeuron();
        NetworkTrainer networkTrainer = new NetworkTrainer(singleNeuron);
        TrainingDataGenerator trainingDataGenerator = mock(TrainingDataGenerator.class);
        TrainingData trainingData = mock(TrainingData.class);
        double[][] inputs = {{0,2}, {1,1}, {1,3}, {2,2}, {3,1}};
        double[][] outputs = {{1}, {1}, {0}, {0}, {0}};
        //when
        when(trainingDataGenerator.getTrainingData()).thenReturn(trainingData);
        when(trainingData.getInputs()).thenReturn(inputs);
        when(trainingData.getOutputs()).thenReturn(outputs);
        networkTrainer.trainNetwork(trainingDataGenerator, new LearningOptions(0.01, 0.1, 0.8, 0));
        //then
        for (int i = 0; i < inputs.length; i++) {
            singleNeuron.setInputs(inputs[i]);
            assertThat(singleNeuron.getOutput()[0]).isEqualTo(outputs[i][0], offset(0.001));
        }
    }

    private static NeuralNetwork createSingleNeuron() {
        NeuralNetwork neuralNetwork = new NeuralNetwork("Single Neuron");
        Neuron inputBias = new Neuron(new ThresholdActivationFunction(0));
        inputBias.setOutput(1);
        Layer inputLayer = new Layer(null, inputBias);
        Neuron a = new Neuron(new ThresholdActivationFunction(0));
        a.setOutput(0);
        Neuron b = new Neuron(new ThresholdActivationFunction(0));
        b.setOutput(0);

        inputLayer.addNeuron(a);
        inputLayer.addNeuron(b);

        Layer outputLayer = new Layer(inputLayer);
        Neuron orNeuron = new Neuron(new ThresholdActivationFunction(0));
        outputLayer.addNeuron(orNeuron);

        neuralNetwork.addLayer(inputLayer);
        neuralNetwork.addLayer(outputLayer);

        return neuralNetwork;
    }
}
