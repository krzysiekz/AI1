package net.ai1.neural;

public class LearningOptions {
    private final double errorThreshold;
    private final double learningRate;
    private final double momentum;
    private final double characteristicTime;

    public LearningOptions(double errorThreshold, double learningRate, double momentum, double characteristicTime) {
        this.errorThreshold = errorThreshold;
        this.learningRate = learningRate;
        this.momentum = momentum;
        this.characteristicTime = characteristicTime;
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
}
