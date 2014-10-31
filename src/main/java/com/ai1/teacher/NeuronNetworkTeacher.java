package com.ai1.teacher;

import com.ai1.LearningEntry;
import com.ai1.LearningOptions;
import com.ai1.NeuronNetwork;

public class NeuronNetworkTeacher {
    private final NeuronNetwork neuronNetwork;

    public NeuronNetworkTeacher(NeuronNetwork neuronNetwork) {
        this.neuronNetwork = neuronNetwork;
    }

    public NeuronNetwork getNeuronNetwork() {
        return neuronNetwork;
    }

    public void teach(LearningOptions learningOptions) {
        int iterations = 0;
        while(iterations < learningOptions.getIterationsLimit()) {
            iterations++;
            int errorCount = teachBasedOnEachEntry(learningOptions);

            if(errorCount == 0) {
                break;
            }
        }
    }

    private int teachBasedOnEachEntry(LearningOptions options) {
        int errorCount = 0;
        for (LearningEntry learningEntry : options.getLearningData()) {
            double value = neuronNetwork.getNetworkOutput(learningEntry.getInputValues()).get(0);

            if(Math.abs(value - learningEntry.getExpectedOutput()) > options.getEpsilon()) {
                errorCount++;
            }
        }
        return errorCount;
    }

}
