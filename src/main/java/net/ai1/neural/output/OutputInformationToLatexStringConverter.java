package net.ai1.neural.output;

import java.util.List;

public class OutputInformationToLatexStringConverter {
    public String convert(OutputInformation outputInformation) {
        StringBuilder outputLine = new StringBuilder();
        outputLine.append(getWeightString(outputInformation.getInitialWeights()));
        outputLine.append("&");
        outputLine.append(outputInformation.getLearningRate());
        outputLine.append("&");
        outputLine.append(outputInformation.getNumberOfEpochs());
        outputLine.append("&");
        outputLine.append(getWeightString(outputInformation.getFinalWeights()));
        outputLine.append("&");
        outputLine.append(outputInformation.getEpsilon());
        outputLine.append("\\\\");
        return outputLine.toString();
    }

    private String getWeightString(List<Double> weights) {
        StringBuilder weightsString = new StringBuilder();
        for(Double weight : weights) {
            weightsString.append(String.format("%.3f", weight).replace(",", "."));
            if(weights.indexOf(weight) != weights.size()-1) {
                weightsString.append(",");
            }
        }
        return weightsString.toString();
    }
}
