import java.util.Arrays;
import java.util.Random;

class Main {
    public static void main(String[] args) {

        Data data = new MNISTDataReader().readTrainingData();
        Random random = new Random();

        data.printImage(random.nextInt(200));

        NeuralNetwork network = new NeuralNetwork(new int[]{764, 30, 20, 10});

        double[] trialInput = new double[764];
        for(int i = 0; i < 764; i++) {
            trialInput[i] = random.nextDouble(0, 1);
        }
        double[] trialOutput = network.feedforward(trialInput);

        System.out.println(Arrays.toString(trialOutput));
    }
}
