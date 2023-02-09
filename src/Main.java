import Maths.*;
import Maths.Vector;


import java.util.Arrays;
import java.util.Random;

class Main {
    public static void main(String[] args) {

        Data trainingData = new MNISTDataReader().readTrainingData();
        Random random = new Random();

        NeuralNetwork network = new NeuralNetwork(new int[]{784, 20, 10});

        network.SGD(trainingData, 10, 10, 3, null);

    }
}
