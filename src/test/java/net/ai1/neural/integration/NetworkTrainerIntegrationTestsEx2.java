package net.ai1.neural.integration;

import net.ai1.neural.*;
import net.ai1.neural.activation.ActivationFunction;
import net.ai1.neural.activation.impl.SigmoidActivationFunction;
import net.ai1.neural.generator.TrainingDataGenerator;
import net.ai1.neural.generator.impl.DigitsDataGenerator;
import net.ai1.neural.output.OutputFileGenerator;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class NetworkTrainerIntegrationTestsEx2 {
    @Ignore
    @Test
    public void shouldTeachNeuronNetworkToRecognizeDigits() {
        //given
        TrainingDataGenerator trainingDataGenerator = new DigitsDataGenerator();
        double[][] inputs = trainingDataGenerator.getTrainingData().getInputs();
        NeuralNetwork neuronNetwork = createNeuronNetwork(inputs[0].length,
                trainingDataGenerator.getTrainingData().getOutputs()[0].length);
        NetworkTrainer networkTrainer = new NetworkTrainer(neuronNetwork);
        //when
        networkTrainer.trainNetwork(trainingDataGenerator, new LearningOptions(0.0001, 0.001, 0.8, 0, 10000000)
                , new OutputFileGenerator());
        //then
        double[][] outputs = trainingDataGenerator.getTrainingData().getOutputs();
        for (int i = 0; i < inputs.length; i++) {
            neuronNetwork.setInputs(inputs[i]);
            double[] processOutput = neuronNetwork.getOutput();
            for (int j = 0; j < processOutput.length; j++) {
                assertThat(processOutput[j]).isEqualTo(
                        Double.valueOf(String.format("%.2f", outputs[i][j]).replace(",", ".")), offset(0.01));
            }
        }
    }



    private static NeuralNetwork createNeuronNetwork(int numberOfInputs, int numberOfOutputs) {
        NeuralNetwork neuralNetwork = new NeuralNetwork("Digits Recognition");
        ActivationFunction activationFunction = new SigmoidActivationFunction();
        Layer inputLayer = new Layer(null);
        for (int i = 0; i < numberOfInputs; i++) {
            Neuron a = new Neuron(activationFunction);
            a.setOutput(0);
            inputLayer.addNeuron(a);
        }

        Layer hiddenLayer = new Layer(inputLayer);

        for (int i = 0; i < 15; i++) {
            Neuron neuron = new Neuron(activationFunction);
            hiddenLayer.addNeuron(neuron);
        }

        Layer outputLayer = new Layer(hiddenLayer);

        for (int i = 0; i < numberOfOutputs; i++) {
            Neuron neuron = new Neuron(activationFunction);
            outputLayer.addNeuron(neuron);
        }

        neuralNetwork.addLayer(inputLayer);
        neuralNetwork.addLayer(hiddenLayer);
        neuralNetwork.addLayer(outputLayer);

        return neuralNetwork;
    }


}
