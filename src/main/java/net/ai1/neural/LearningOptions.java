package net.ai1.neural;

public class LearningOptions {
    private final double errorThreshold;
    private final double learningRate;
    private final double momentum;
    private final double characteristicTime;
    private final double maxNumberOfEpochs;

    public LearningOptions(double errorThreshold, double learningRate, double momentum, double characteristicTime,
                           double maxNumberOfEpochs) {
        this.errorThreshold = errorThreshold;
        this.learningRate = learningRate;
        this.momentum = momentum;
        this.characteristicTime = characteristicTime;
        this.maxNumberOfEpochs = maxNumberOfEpochs;
    }

    public double getErrorThreshold() {
        return errorThreshold;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public double getMomentum() {
        return momentum;
    }

    public double getCharacteristicTime() {
        return characteristicTime;
    }

    public double getMaxNumberOfEpochs() {
        return maxNumberOfEpochs;
    }
}
