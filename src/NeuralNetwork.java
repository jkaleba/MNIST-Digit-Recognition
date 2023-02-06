import Maths.Matrix;

import java.util.Arrays;
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

    private static void shuffle(double[][][] array) {
        int index;
        double[][] temp;
        Random random = new Random();
        for(int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);

            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public void SGD(double[][][] trainingData, int[] desiredOutputs, int epochs, int miniBatchSize, double eta, double[][][] testData, int[] desiredTestOutput) {
//                                  picture               0-9                                      learning rate

        for(int i = 0; i < epochs; i++) {

            shuffle(trainingData);

            double[][][][] miniBatches = new double[(trainingData.length - 1) / miniBatchSize + 1][][][];
            for(int j = 0; j < trainingData.length; j += miniBatchSize) {
                miniBatches[j] = new double[miniBatchSize][][];
                for(int k = 0; k < miniBatchSize; k++) {
                    miniBatches[j][k] = trainingData[j];
                }
            }

            for(var batch : miniBatches) {
                updateMiniBach(batch, eta);
            }

            if(testData.length != 0) {
                System.out.println("Epoch: " + i + " " + evaluate(testData) + " " + testData.length);
            }
            else {
                System.out.println("Epoch: " + i + " complete.");
            }

        }

    }

    private void updateMiniBach(double[][][] miniBatch, double eta) {
        double[] nablaB = new double[this.biases.length];
        double[] nablaW = new double[this.weights.length];

    }

    private int evaluate(final double[][][] testData) {
        return 0;
    }


}