package net.ai1.neural.integration;

import net.ai1.neural.*;
import net.ai1.neural.activation.ActivationFunction;
import net.ai1.neural.activation.impl.SigmoidActivationFunction;
import net.ai1.neural.activation.impl.ThresholdActivationFunction;
import net.ai1.neural.generator.TrainingData;
import net.ai1.neural.generator.TrainingDataGenerator;
import net.ai1.neural.output.OutputFileGenerator;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NetworkTrainerIntegrationTestsEx2 {
    @Ignore
    @Test
    public void shouldTeachNeuronNetworkToRecognizeDigits() {
        //given
        double[][] inputs = {{0,1,1,0,1,0,0,1,1,0,0,1,1,0,0,1,0,1,1,0}
                , {0,0,0,1,0,0,1,1,0,1,0,1,0,0,0,1,0,0,0,1}
                , {0,1,1,0,1,0,0,1,0,0,1,0,0,1,0,0,1,1,1,1}
                , {0,1,1,0,1,0,0,1,0,0,1,0,1,0,0,1,0,1,1,0}
                , {1,0,0,1,1,0,0,1,1,1,1,1,0,0,0,1,0,0,0,1}
                , {1,1,1,1,1,0,0,0,1,1,1,0,0,0,0,1,0,1,1,0}
                , {0,1,1,0,1,0,0,0,1,1,1,0,1,0,0,1,0,1,1,0}
                , {1,1,1,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0}
                , {0,1,1,0,1,0,0,1,0,1,1,0,1,0,0,1,0,1,1,0}
                , {0,1,1,0,1,0,0,1,0,1,1,1,0,0,0,1,0,1,1,0}
        };

        double[][] outputs = {{1,0,0,0,0,0,0,0,0,0}
                , {0,1,0,0,0,0,0,0,0,0}
                , {0,0,1,0,0,0,0,0,0,0}
                , {0,0,0,1,0,0,0,0,0,0}
                , {0,0,0,0,1,0,0,0,0,0}
                , {0,0,0,0,0,1,0,0,0,0}
                , {0,0,0,0,0,0,1,0,0,0}
                , {0,0,0,0,0,0,0,1,0,0}
                , {0,0,0,0,0,0,0,0,1,0}
                , {0,0,0,0,0,0,0,0,0,1}
        };

        NeuralNetwork neuronNetwork = createNeuronNetwork(inputs[0].length, outputs[0].length);
        NetworkTrainer networkTrainer = new NetworkTrainer(neuronNetwork);
        TrainingDataGenerator trainingDataGenerator = mock(TrainingDataGenerator.class);
        TrainingData trainingData = mock(TrainingData.class);
        //when
        when(trainingDataGenerator.getTrainingData()).thenReturn(trainingData);
        when(trainingData.getInputs()).thenReturn(inputs);
        when(trainingData.getOutputs()).thenReturn(outputs);
        networkTrainer.trainNetwork(trainingDataGenerator, new LearningOptions(0.001, 0.0001, 0.8, 0, 1000000), new OutputFileGenerator());
        //then
        for (int i = 0; i < inputs.length; i++) {
            neuronNetwork.setInputs(inputs[i]);
            assertThat(neuronNetwork.getOutput()).isEqualTo(outputs[i]);
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
