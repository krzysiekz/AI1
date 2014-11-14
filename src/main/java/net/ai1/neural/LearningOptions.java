package net.ai1.neural;

/**
 * The Learning options for neural network.
 */
public class LearningOptions {
    private final double errorThreshold;
    private final double learningRate;
    private final double momentum;
    private final double characteristicTime;
    private final double maxNumberOfEpochs;

    /**
     * Instantiates a new Learning options.
     *
     * @param errorThreshold the error threshold
     * @param learningRate the learning rate
     * @param momentum the momentum
     * @param characteristicTime the characteristic time
     * @param maxNumberOfEpochs the max number of epochs
     */
    public LearningOptions(double errorThreshold, double learningRate, double momentum, double characteristicTime,
                           double maxNumberOfEpochs) {
        this.errorThreshold = errorThreshold;
        this.learningRate = learningRate;
        this.momentum = momentum;
        this.characteristicTime = characteristicTime;
        this.maxNumberOfEpochs = maxNumberOfEpochs;
    }

    /**
     * Gets error threshold.
     *
     * @return the error threshold
     */
    public double getErrorThreshold() {
        return errorThreshold;
    }

    /**
     * Gets learning rate.
     *
     * @return the learning rate
     */
    public double getLearningRate() {
        return learningRate;
    }

    /**
     * Gets momentum.
     *
     * @return the momentum
     */
    public double getMomentum() {
        return momentum;
    }

    /**
     * Gets characteristic time.
     *
     * @return the characteristic time
     */
    public double getCharacteristicTime() {
        return characteristicTime;
    }

    /**
     * Gets max number of epochs.
     *
     * @return the max number of epochs
     */
    public double getMaxNumberOfEpochs() {
        return maxNumberOfEpochs;
    }
}
