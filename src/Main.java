class Main {
    public static void main(String[] args) {

        Data trainingData = new MNISTDataReader().readTrainingData();
        Data testData = new MNISTDataReader().readTestData();

        NeuralNetwork network = new NeuralNetwork(new int[]{784, 28, 10});

        network.SGD(trainingData, 100, 5, 1, testData);
    }
}