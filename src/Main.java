import java.util.Arrays;
import java.util.Random;

class Main {
    public static void main(String[] args) {

        Data data = new MNISTDataReader().readTrainingData();
        Random random = new Random();

        data.printImage(random.nextInt(200));

        NeuralNetwork network = new NeuralNetwork(new int[]{5, 4, 3, 2});

//        double[] trialOutput = network.feedforward(new double[]{0.1, 0.2, 0.3, 0.4});
        double[] label = new double[]{0, 0, 0, 0, 1.0};

        network.backPropagation(new double[]{0.1, 0.2, 0.3, 0.4, 0.5}, label);

//        System.out.println(Arrays.toString(trialOutput));
    }
}
