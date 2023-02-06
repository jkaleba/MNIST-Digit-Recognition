import Maths.*;

import java.util.Arrays;

class Main {
    public static void main(String[] args) {

        NeuralNetwork network = new NeuralNetwork(new int[]{5, 3, 2});
        double[] trialOutput = network.feedforward(new double[]{1.0, 0.5, 0.5, 1, 1});

        System.out.println(Arrays.toString(trialOutput));
    }
}
