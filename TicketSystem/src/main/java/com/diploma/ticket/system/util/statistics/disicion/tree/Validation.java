package com.diploma.ticket.system.util.statistics.disicion.tree;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Validation {

    private Set<Passenger> [] splitData;
    private final int NUMBER_OF_SPLITS;
    private String[] trainAttributes;
    private String targetAttribute;

    public Validation(Set<Passenger> data, int numberOfSplits, String [] trainAttributes, String targetAttribute){
        this.splitData = splitData(data, numberOfSplits);
        this.NUMBER_OF_SPLITS = numberOfSplits;
        this.trainAttributes = trainAttributes;
        this.targetAttribute = targetAttribute;
    }

    public double crossValidate(){
        double precisions[] = new double[NUMBER_OF_SPLITS];
        Set<Passenger> trainData, testData;
        for(int i = 0; i < NUMBER_OF_SPLITS; i++){
            int correct = 0, incorrect = 0;
            // get train and test data
            testData = splitData[i];
            trainData = mergeAllExceptIndex(splitData, i);
            // create decision tree based on the train data
            DecisionTree decisionTree = new DecisionTree(targetAttribute, trainAttributes);
            decisionTree.train(trainData);
            decisionTree.print();
            for(Passenger p : testData){
                // decisionTree.classify: 0 = not survived, 1 = survived, 2 = can not classify data
                if(Integer.parseInt(decisionTree.classify(p)) == p.getAttributeValue(targetAttribute)) correct++;
                else incorrect++;
            }
            //System.out.println("Korrekt klassifiziert: " + correct + "\nInkorrekt klassifiziert: " + incorrect);
            precisions[i] = (double) correct / ((double) correct + (double) incorrect);
            //System.out.println("Präzision: " + precisions[i] + "\n\n");
        }
        double avgPrecision = average(precisions);
        System.out.println("\nСРЕДНА ТОЧНОСТ (CROSS-VALIDATION): " + avgPrecision);
        return avgPrecision;
    }

    // creates csv file "evaluation.csv" with 2 columns (PassengerID, classifiedValue)
    public void evaluateKaggleTestData(DecisionTree decisionTree, Set<Passenger> testData){
        try {
            PrintWriter writer = new PrintWriter(new File("src/data/result.csv"));
            // append header line to csv file (see kaggle requirements)
            writer.write("TicketID,Finished\n");
            // append each classification result
            for(Passenger p : testData){
                String line = String.valueOf(p.getId()) + ",";
                line += decisionTree.classify(p);
                line += "\n";
                writer.write(line);
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Set<Passenger>[] splitData(Set<Passenger> data, int numberSplits){
        Set<Passenger> [] splitSets = new Set[numberSplits];
        for(int i = 0; i < splitSets.length ; i++){
            splitSets[i] = new HashSet<>();
        }
        Object [] data_arr = data.toArray();
        Passenger p;
        int i = 0;
        for(Object o : data_arr){
            p = (Passenger) o;
            splitSets[i].add(p);
            i++;
            if(i >= numberSplits){
                i = 0;
            }
        }
        return splitSets;
    }

    private double average(double [] nums){
        double sum = 0.0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        return sum / nums.length;
    }

    // merges all sets from array except for the one at index idx
    private Set<Passenger> mergeAllExceptIndex(Set<Passenger> [] dataSplits, int idx){
        Set<Passenger> trainData = new HashSet<>();
        for(int i = 0; i < dataSplits.length; i++){
            if(i != idx){
                trainData.addAll(dataSplits[i]);
            }
        }
        return trainData;
    }
}