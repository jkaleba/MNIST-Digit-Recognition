class Main {
    public static void main(String[] args) {

        Data trainingData = new MNISTDataReader().readTrainingData();

        NeuralNetwork network = new NeuralNetwork(new int[]{784, 28, 10});
        Data testData = new MNISTDataReader().readTestData();

        network.SGD(trainingData, 100, 5, 1.0, testData);
    }
}