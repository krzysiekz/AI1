package net.ai1.neural;

import net.ai1.neural.generator.TrainingData;
import net.ai1.neural.generator.TrainingDataGenerator;
import net.ai1.neural.output.OutputFileGenerator;
import net.ai1.neural.output.OutputInformation;
import net.ai1.neural.output.OutputInformationToLatexStringConverter;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class NetworkTrainer {

    private final NeuralNetwork neuralNetwork;
    private double currentEpoch;

    public NetworkTrainer(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;

    }

    public void trainNetwork(TrainingDataGenerator generator, LearningOptions learningOptions, OutputFileGenerator outputFileGenerator) {
        double error;
        OutputInformation outputInformation = new OutputInformation();
        outputInformation.setInitialWeights(neuralNetwork.getWeights());
        outputInformation.setEpsilon(learningOptions.getErrorThreshold());
        outputInformation.setLearningRate(learningOptions.getLearningRate());

        int epoch = 1;
        do {
            TrainingData trainingData = generator.getTrainingData();
            error = train(trainingData, learningOptions);

            if(epoch % 1 == 0) {
                Logger.getAnonymousLogger().info(MessageFormat.format("Error for epoch {0}: {1}", epoch, error));
            }
            epoch++;
            currentEpoch = epoch;
        } while(error > learningOptions.getErrorThreshold() && epoch <= learningOptions.getMaxNumberOfEpochs());

        outputInformation.setNumberOfEpochs(epoch);
        outputInformation.setFinalWeights(neuralNetwork.getWeights());
        outputFileGenerator.generateOutput(outputInformation, new OutputInformationToLatexStringConverter());
    }

    private double train(TrainingData learningEntries, LearningOptions learningOptions) {
        double error = 0;
        Map<Connection, Double> synapseNeuronDeltaMap = new HashMap<>();

        double[][] inputs = learningEntries.getInputs();
        double[][] expectedOutputs = learningEntries.getOutputs();

        for (int i = 0; i < inputs.length; i++) {

            double[] input = inputs[i];
            double[] expectedOutput = expectedOutputs[i];

            neuralNetwork.setInputs(input);
            double[] output = neuralNetwork.getOutput();

            calculateErrors(expectedOutput, output);
            adjustWeights(learningOptions, synapseNeuronDeltaMap);

//            output = neuralNetwork.getOutput();
            error += error(output, expectedOutput);
        }

        return error;
    }

    private void adjustWeights(LearningOptions learningOptions, Map<Connection, Double> synapseNeuronDeltaMap) {
        List<Layer> layers = neuralNetwork.getLayers();
        for(int j = layers.size() - 1; j > 0; j--) {
            adjustWeightsOnLayer(learningOptions, synapseNeuronDeltaMap, layers.get(j));
        }
    }

    private void adjustWeightsOnLayer(LearningOptions learningOptions, Map<Connection, Double> synapseNeuronDeltaMap, Layer layer) {
        for(Neuron neuron : layer.getNeurons()) {
            adjustWeightsOnNeuron(learningOptions, synapseNeuronDeltaMap, neuron);
        }
    }

    private void adjustWeightsOnNeuron(LearningOptions learningOptions, Map<Connection, Double> synapseNeuronDeltaMap, Neuron neuron) {
        for(Connection connection : neuron.getInputs()) {
            double newLearningRate = getNewLearningRate(learningOptions);
            double delta = newLearningRate * neuron.getError() * connection.getSourceNeuron().getOutput();

            if(synapseNeuronDeltaMap.get(connection) != null) {
                double previousDelta = synapseNeuronDeltaMap.get(connection);
                delta += learningOptions.getMomentum() * previousDelta;
            }

            synapseNeuronDeltaMap.put(connection, delta);
            connection.setWeight(connection.getWeight() - delta);
        }
    }

    private double getNewLearningRate(LearningOptions learningOptions) {
        return learningOptions.getCharacteristicTime() > 0 ?
                learningOptions.getLearningRate() / (1 + (currentEpoch / learningOptions.getCharacteristicTime()))
                : learningOptions.getLearningRate();
    }

    private void calculateErrors(double[] expectedOutput, double[] output) {
        List<Layer> layers = neuralNetwork.getLayers();
        for (int j = layers.size() - 1; j > 0; j--) {
            calculateErrorsOnLayer(expectedOutput, output, layers.get(j));
        }
    }

    private void calculateErrorsOnLayer(double[] expectedOutput, double[] output, Layer layer) {
        for (int k = 0; k < layer.getNeurons().size(); k++) {
            Neuron neuron = layer.getNeurons().get(k);
            double neuronError;

            if (layer.isOutputLayer()) {
                neuronError = neuron.getDerivative() * (output[k] - expectedOutput[k]);
            } else {
                neuronError = neuron.getDerivative();
                double sum = calculateErrorOnHiddenLayer(layer, neuron);
                neuronError *= sum;
            }
            neuron.setError(neuronError);
        }
    }

    private double calculateErrorOnHiddenLayer(Layer layer, Neuron neuron) {
        double sum = 0;
        List<Neuron> downstreamNeurons = layer.getNextLayer().getNeurons();
        for (Neuron downstreamNeuron : downstreamNeurons) {
            sum = sum + getSumFromDownstreamNeuron(neuron, downstreamNeuron);
        }
        return sum;
    }

    private double getSumFromDownstreamNeuron(Neuron neuron, Neuron downstreamNeuron) {
        double sum = 0;
        int l = 0;
        boolean found = false;
        while (l < downstreamNeuron.getInputs().size() && !found) {
            Connection connection = downstreamNeuron.getInputs().get(l);

            if (connection.getSourceNeuron() == neuron) {
                sum += (connection.getWeight() * downstreamNeuron.getError());
                found = true;
            }
            l++;
        }
        return sum;
    }

    private double error(double[] actual, double[] expected) {
        if (actual.length != expected.length) {
            throw new IllegalArgumentException("The lengths of the actual and expected value arrays must be equal");
        }
        double sum = 0;
        for (int i = 0; i < expected.length; i++) {
            sum += Math.pow(expected[i] - actual[i], 2);
        }
        return sum / 2;
    }

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }
}