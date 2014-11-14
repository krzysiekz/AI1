package net.ai1.neural.output;

import java.util.List;

/**
 * The Neural Network calculations Output information.
 */
public class OutputInformation {
    private List<Double> initialWeights;
    private Double learningRate;
    private int numberOfEpochs;
    private List<Double> finalWeights;
    private Double epsilon;

    /**
     * Gets initial weights.
     *
     * @return the initial weights
     */
    public List<Double> getInitialWeights() {
        return initialWeights;
    }

    /**
     * Sets initial weights.
     *
     * @param initialWeights the initial weights
     */
    public void setInitialWeights(List<Double> initialWeights) {
        this.initialWeights = initialWeights;
    }

    /**
     * Gets learning rate.
     *
     * @return the learning rate
     */
    public Double getLearningRate() {
        return learningRate;
    }

    /**
     * Sets learning rate.
     *
     * @param learningRate the learning rate
     */
    public void setLearningRate(Double learningRate) {
        this.learningRate = learningRate;
    }

    /**
     * Gets number of epochs.
     *
     * @return the number of epochs
     */
    public int getNumberOfEpochs() {
        return numberOfEpochs;
    }

    /**
     * Sets number of epochs.
     *
     * @param numberOfEpochs the number of epochs
     */
    public void setNumberOfEpochs(int numberOfEpochs) {
        this.numberOfEpochs = numberOfEpochs;
    }

    /**
     * Gets final weights.
     *
     * @return the final weights
     */
    public List<Double> getFinalWeights() {
        return finalWeights;
    }

    /**
     * Sets final weights.
     *
     * @param finalWeights the final weights
     */
    public void setFinalWeights(List<Double> finalWeights) {
        this.finalWeights = finalWeights;
    }

    /**
     * Gets epsilon.
     *
     * @return the epsilon
     */
    public Double getEpsilon() {
        return epsilon;
    }

    /**
     * Sets epsilon.
     *
     * @param epsilon the epsilon
     */
    public void setEpsilon(Double epsilon) {
        this.epsilon = epsilon;
    }
}
