class Main {
    public static void main(String[] args) {

        Data trainingData = new MNISTDataReader().readTrainingData();

        NeuralNetwork network = new NeuralNetwork(new int[]{784, 84, 28, 10});
        Data testData = new MNISTDataReader().readTestData();

        network.SGD(trainingData, 1000, 10, 0.7, testData);
    }
}
