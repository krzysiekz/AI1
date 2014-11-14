package net.ai1;

import net.ai1.neural.*;
import net.ai1.neural.activation.impl.SigmoidActivationFunction;
import net.ai1.neural.error.impl.SquareErrorCalculator;
import net.ai1.neural.generator.TrainingDataGenerator;
import net.ai1.neural.output.OutputFileGenerator;
import net.ai1.neural.generator.impl.XorTrainingDataGenerator;

public class XORExample {

    public static void main(String[] args) {

        NeuralNetwork untrained = createUntrainedXorNeuralNetwork();
        TrainingDataGenerator xorTrainingDataGenerator = new XorTrainingDataGenerator();
        LearningOptions options = new LearningOptions(0.01, 0.001, 0.9, 0, 10000000);
        NetworkTrainer trainer = new NetworkTrainer(untrained, new SquareErrorCalculator());
        OutputFileGenerator outputFileGenerator = new OutputFileGenerator();
        trainer.trainNetwork(xorTrainingDataGenerator, options, outputFileGenerator);
    }

    private static NeuralNetwork createUntrainedXorNeuralNetwork() {
        NeuralNetwork xorNeuralNetwork = new NeuralNetwork("XOR Network");
        Neuron inputBias = new Neuron(new SigmoidActivationFunction());
        inputBias.setOutput(1);
        Layer inputLayer = new Layer(null, inputBias);

        Neuron a = new Neuron(new SigmoidActivationFunction());
        a.setOutput(0);
        Neuron b = new Neuron(new SigmoidActivationFunction());
        b.setOutput(0);

        inputLayer.addNeuron(a);
        inputLayer.addNeuron(b);

        Neuron bias = new Neuron(new SigmoidActivationFunction());
        bias.setOutput(1);
        Layer hiddenLayer = new Layer(inputLayer, bias);

        Neuron hiddenA = new Neuron(new SigmoidActivationFunction());
        Neuron hiddenB = new Neuron(new SigmoidActivationFunction());

        hiddenLayer.addNeuron(hiddenA);
        hiddenLayer.addNeuron(hiddenB);

        Layer outputLayer = new Layer(hiddenLayer);
        Neuron xorNeuron = new Neuron(new SigmoidActivationFunction());
        outputLayer.addNeuron(xorNeuron);

        xorNeuralNetwork.addLayer(inputLayer);
        xorNeuralNetwork.addLayer(hiddenLayer);
        xorNeuralNetwork.addLayer(outputLayer);

        return xorNeuralNetwork;
    }
}
