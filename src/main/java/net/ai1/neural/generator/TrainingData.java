package net.ai1.neural.generator;

/**
 * The Training data.
 */
public class TrainingData {

    private final double[][] inputs;
    private final double[][] outputs;

    /**
     * Instantiates a new Training data.
     *
     * @param inputs the inputs
     * @param outputs the outputs
     */
    public TrainingData(double[][] inputs, double[][] outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    /**
     * Get inputs.
     *
     * @return the double [ ] [ ]
     */
    public double[][] getInputs() {
        return inputs;
    }

    /**
     * Get outputs.
     *
     * @return the double [ ] [ ]
     */
    public double[][] getOutputs() {
        return outputs;
    }
}
