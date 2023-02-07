import Maths.Matrix;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

class NeuralNetwork {

    int[] sizes;
    int layersNumber;
    double[][] biases;
    double[][][] weights;

    public NeuralNetwork(int[] sizes) {

        this.sizes = new int[sizes.length];
        this.layersNumber = sizes.length;

        System.arraycopy(sizes, 0, this.sizes, 0, sizes.length);

        biases = new double[this.layersNumber - 1][];
        Random random = new Random();
        for(int i = 0; i < layersNumber - 1; i++) {
            biases[i] = new double[sizes[i]];
            for(int j = 0; j < biases[i].length; j++) {
                biases[i][j] = random.nextGaussian(0, 1);
            }
        }
/*
        each weights[i] represents all connections weights between the i and i + 1 layer,
        each weights[i][j] represents weights between particular neurons,
 */
        weights = new double[layersNumber - 1][][];
        for(int i = 0; i < layersNumber - 1; i++) {
            weights[i] = new double[sizes[i + 1]][];
            for(int j = 0; j < weights[i].length; j++) {
                weights[i][j] = new double[sizes[i]];
                for(int k = 0; k < sizes[i]; k++) {
                    weights[i][j][k] = random.nextGaussian(0, 1);
                }
            }
        }
        System.out.println(Arrays.deepToString(biases));
        System.out.println();
        for(var row: weights) {
            System.out.println(Arrays.deepToString(row));
        }

    }

    private static double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    public double[] feedforward(double[] vector) {

        for(int phase = 0; phase < layersNumber - 1; phase++) {
            double [] layerOutput = new double[sizes[phase + 1]];

            for(int i = 0; i < sizes[phase + 1]; i++) {
                layerOutput[i] = sigmoid(Matrix.dotProduct(weights[phase][i], vector) + biases[phase][i]);
            }
            vector = layerOutput;
        }
        return vector;
    }

    public void SGD(Data trainingData, int epochs, int miniBatchSize, double eta, Data testData) {
//                                                                   learning rate
        int left;
        for(int i = 0; i < epochs; i++) {
            trainingData.shuffle();

            left = trainingData.length();

            Data[] miniBatches = new Data[(trainingData.length() - 1) / miniBatchSize + 1];
            miniBatches[i].setSize(Math.min(left, miniBatchSize));
            for(int j = 0; j < trainingData.length(); j += miniBatchSize) {

                for(int k = 0; k < miniBatches[i].length(); i++) {
                    miniBatches[i].setImage(k, trainingData.getImage(j + k));
                    miniBatches[i].setLabel(k, trainingData.getLabel(j + k));
                }
                left -= miniBatchSize;
            }

            for(Data miniBatch : miniBatches) {
                updateMiniBach(miniBatch, eta);
            }

            if(testData.getImages() != null) {
                System.out.println("Epoch: " + i + " " + evaluate(testData) + " " + testData.length());
            }
            else {
                System.out.println("Epoch: " + i + " complete.");
            }
        }
    }

    private void updateMiniBach(Data miniBatchData, double eta) {
        double[][] nablaB = new double[this.biases.length][];
        double[][][] nablaW = new double[this.weights.length][][];

        for(int i = 0; i < biases.length; i++) {
            nablaB[i] = new double[biases[i].length];
            Arrays.fill(nablaB[i], 0.0);

            nablaW[i] = new double[weights[i].length][];
            for(int j = 0; j < weights[i].length; j++) {
                nablaW[i][j] = new double[weights[i][j].length];
                Arrays.fill(nablaW[i][j], 0);
            }
        }


    }

    private int evaluate(Data testData) {
        return 0;
    }


}