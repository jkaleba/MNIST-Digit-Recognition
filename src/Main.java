import java.util.Arrays;
import java.util.Random;

class Main {
    public static void main(String[] args) {

        Data data = new MNISTDataReader().readTrainingData();
        Random random = new Random();
        data.printImage(random.nextInt(100));

        NeuralNetwork network = new NeuralNetwork(new int[]{5, 3, 2});
        double[] trialOutput = network.feedforward(new double[]{1.0, 0.5, 0.5, 1, 1});

        System.out.println(Arrays.toString(trialOutput));
    }
}
