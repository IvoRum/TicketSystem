
import java.io.*;
import java.util.Set;

class App {
    public static void main(String [] args){
        /**1. read in data*/
        final String TRAIN_DATA_PATH = "src/data/train.csv";
        Set<Passenger> data = CSV_Helper.readTrainData(TRAIN_DATA_PATH); // read in data from csv file
        /**2. define attributes for training and the target Attribute (the one our classifier is going to predict)*/
        String [] trainAttributes = {
                Attribute.TIMEISSUED,
                Attribute.OPENDCOUNTERS,
                Attribute.ACTIVETICKETS
            };
        String targetAttribute = Attribute.FINISHED;
        /**3. Create and train your decision tree*/
        DecisionTree decisionTree = new DecisionTree(targetAttribute, trainAttributes);
        decisionTree.train(data);
        //decisionTree.print(); // print resulting decision tree
        /**4. Classify new unseen data*/
        System.out.println(
                "Classified as: " +
                decisionTree.classify(new Passenger("123","AM","4","10"))
        );

        /**Cross validation*/
        final int NUMBER_OF_SPLITS = 10;
        Validation validator = new Validation(data, NUMBER_OF_SPLITS, trainAttributes, targetAttribute);
        //validator.crossValidate();

        /**Validation on Kaggle test data*/
        final String TEST_DATA_PATH = "src/data/test.csv";
        Set<Passenger> testData = CSV_Helper.readTestData(TEST_DATA_PATH);
        validator.evaluateKaggleTestData(decisionTree, testData);
    }
}