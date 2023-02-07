import Maths.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        DataReader data = new DataReader();
        data.readData();
        System.out.println(Arrays.toString(data.labels));


        NeuralNetwork network = new NeuralNetwork(new int[]{5, 3, 2});
        double[] trialOutput = network.feedforward(new double[]{1.0, 0.5, 0.5, 1, 1});

        System.out.println(Arrays.toString(trialOutput));
    }
}
