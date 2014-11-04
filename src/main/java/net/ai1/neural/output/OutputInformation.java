package net.ai1.neural.output;

import java.util.List;

public class OutputInformation {
    private List<Double> initialWeights;
    private Double learningRate;
    private int numberOfEpochs;
    private List<Double> finalWeights;
    private Double epsilon;

    public List<Double> getInitialWeights() {
        return initialWeights;
    }

    public void setInitialWeights(List<Double> initialWeights) {
        this.initialWeights = initialWeights;
    }

    public Double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(Double learningRate) {
        this.learningRate = learningRate;
    }

    public int getNumberOfEpochs() {
        return numberOfEpochs;
    }

    public void setNumberOfEpochs(int numberOfEpochs) {
        this.numberOfEpochs = numberOfEpochs;
    }

    public List<Double> getFinalWeights() {
        return finalWeights;
    }

    public void setFinalWeights(List<Double> finalWeights) {
        this.finalWeights = finalWeights;
    }

    public Double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(Double epsilon) {
        this.epsilon = epsilon;
    }
}
