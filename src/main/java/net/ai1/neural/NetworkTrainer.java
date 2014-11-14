package net.ai1.neural;

import net.ai1.neural.error.ErrorCalculator;
import net.ai1.neural.generator.TrainingData;
import net.ai1.neural.generator.TrainingDataGenerator;
import net.ai1.neural.output.OutputFileGenerator;
import net.ai1.neural.output.OutputInformation;
import net.ai1.neural.output.OutputInformationToLatexStringConverter;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The Neural Network trainer.
 */
public class NetworkTrainer {

    private final NeuralNetwork neuralNetwork;
    private final Map<Connection, Double> synapseNeuronDeltaMap = new HashMap<>();
    private final ErrorCalculator errorCalculator;
    private int currentEpoch;

    /**
     * Instantiates a new Network trainer.
     *
     * @param neuralNetwork the neural network
     * @param errorCalculator the error calculator
     */
    public NetworkTrainer(NeuralNetwork neuralNetwork, ErrorCalculator errorCalculator) {
        this.neuralNetwork = neuralNetwork;
        this.errorCalculator = errorCalculator;
    }

    /**
     * Trains network.
     *
     * @param generator the training data generator
     * @param learningOptions the learning options
     * @param outputFileGenerator the output file generator
     */
    public void trainNetwork(TrainingDataGenerator generator, LearningOptions learningOptions, OutputFileGenerator outputFileGenerator) {
        double error;
        OutputInformation outputInformation = new OutputInformation();
        outputInformation.setInitialWeights(neuralNetwork.getWeights());
        outputInformation.setEpsilon(learningOptions.getErrorThreshold());
        outputInformation.setLearningRate(learningOptions.getLearningRate());

        TrainingData trainingData = generator.getTrainingData();
        currentEpoch = 1;
        do {
            error = train(trainingData, learningOptions);
            if(currentEpoch % 1000 == 0) {
                Logger.getAnonymousLogger().info(MessageFormat.format("Error for epoch {0}: {1,number,#.#######}", currentEpoch, error));
            }
            currentEpoch++;
        } while(error > learningOptions.getErrorThreshold() && currentEpoch <= learningOptions.getMaxNumberOfEpochs());

        outputInformation.setNumberOfEpochs(currentEpoch);
        outputInformation.setFinalWeights(neuralNetwork.getWeights());
        outputFileGenerator.generateOutput(outputInformation, new OutputInformationToLatexStringConverter());
        displayResults(generator);
    }

    void displayResults(TrainingDataGenerator generator) {
        Logger.getAnonymousLogger().info(MessageFormat.format("Results for network: {0}", neuralNetwork.getName()));
        Logger.getAnonymousLogger().info(neuralNetwork.getWeights().toString());
        double[][] inputs = generator.getTrainingData().getInputs();
        for (double[] input : inputs) {
            displaySingleResult(input);
        }
    }

    private void displaySingleResult(double[] input) {
        neuralNetwork.setInputs(input);
        double[] processOutput = neuralNetwork.getOutput();
        Logger.getAnonymousLogger().info(MessageFormat.format("Input: {0}, Output: {1}",
                Arrays.toString(input), Arrays.toString(processOutput)));
    }

    private double train(TrainingData learningEntries, LearningOptions learningOptions) {
        double error = 0, currentError;
        double[][] inputs = learningEntries.getInputs();
        double[][] expectedOutputs = learningEntries.getOutputs();

        for (int i = 0; i < inputs.length; i++) {
            neuralNetwork.setInputs(inputs[i]);
            double[] output = neuralNetwork.getOutput();

            currentError = errorCalculator.calculate(output, expectedOutputs[i]);
            if(currentError != 0) {
                calculateErrors(expectedOutputs[i], output);
                adjustWeights(learningOptions);
            }
            error += currentError;
        }
        return error;
    }

    private void adjustWeights(LearningOptions learningOptions) {
        List<Layer> layers = neuralNetwork.getLayers();
        for(int j = layers.size() - 1; j > 0; j--) {
            adjustWeightsOnLayer(learningOptions, layers.get(j));
        }
    }

    private void adjustWeightsOnLayer(LearningOptions learningOptions, Layer layer) {
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
                learningOptions.getLearningRate() / (1 + (double) currentEpoch / learningOptions.getCharacteristicTime())
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

    /**
     * Gets neural network.
     *
     * @return the neural network
     */
    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }
}