package com.ai1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LearningEntryCreator {
    public LearningEntryCreator() {
    }

    public LearningEntry getLearningEntry(Double expectedResults, Double... inputs) {
        List<Double> inputValues = new ArrayList<Double>();
        Collections.addAll(inputValues, inputs);
        return new LearningEntry(inputValues, expectedResults);
    }
}