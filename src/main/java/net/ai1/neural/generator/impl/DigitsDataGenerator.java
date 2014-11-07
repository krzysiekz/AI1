package net.ai1.neural.generator.impl;

import net.ai1.neural.generator.TrainingData;
import net.ai1.neural.generator.TrainingDataGenerator;

public class DigitsDataGenerator implements TrainingDataGenerator {

    private final double[][] inputs = {{0,1,1,0,1,0,0,1,1,0,0,1,1,0,0,1,0,1,1,0}
            , {0,0,0,1,0,0,1,1,0,1,0,1,0,0,0,1,0,0,0,1}
            , {0,1,1,0,1,0,0,1,0,0,1,0,0,1,0,0,1,1,1,1}
            , {0,1,1,0,1,0,0,1,0,0,1,0,1,0,0,1,0,1,1,0}
            , {1,0,0,1,1,0,0,1,1,1,1,1,0,0,0,1,0,0,0,1}
            , {1,1,1,1,1,0,0,0,1,1,1,0,0,0,0,1,0,1,1,0}
            , {0,1,1,0,1,0,0,0,1,1,1,0,1,0,0,1,0,1,1,0}
            , {1,1,1,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0}
            , {0,1,1,0,1,0,0,1,0,1,1,0,1,0,0,1,0,1,1,0}
            , {0,1,1,0,1,0,0,1,0,1,1,1,0,0,0,1,0,1,1,0}
    };

    private final double[][] outputs = {{1,0,0,0,0,0,0,0,0,0}
            , {0,1,0,0,0,0,0,0,0,0}
            , {0,0,1,0,0,0,0,0,0,0}
            , {0,0,0,1,0,0,0,0,0,0}
            , {0,0,0,0,1,0,0,0,0,0}
            , {0,0,0,0,0,1,0,0,0,0}
            , {0,0,0,0,0,0,1,0,0,0}
            , {0,0,0,0,0,0,0,1,0,0}
            , {0,0,0,0,0,0,0,0,1,0}
            , {0,0,0,0,0,0,0,0,0,1}
    };

    @Override
    public TrainingData getTrainingData() {
        return new TrainingData(inputs, outputs);
    }
}
