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
        for(int i = 0; i < biases.length; i++) {
            biases[i] = new double[sizes[i + 1]];
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
    }

    private static double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    public double[] feedforward(double[] vector) {

        for(int phase = 0; phase < layersNumber - 1; phase++) {
            double [] layerOutput = new double[sizes[phase + 1]];

            for(int i = 0; i < layerOutput.length; i++) {
                layerOutput[i] = sigmoid(Matrix.dotProduct(weights[phase][i], vector) + biases[phase][i]);
            }
            vector = layerOutput;
        }
        return vector;
    }

    public void showWeights() {
        for(var row : weights) {
            System.out.println(Arrays.deepToString(row));
        }
    }

    public void showBiases() {
        for(var row : biases) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void SGD(Data trainingData, int epochs, int miniBatchSize, double eta, Data testData) {
//                                                                   learning rate
        int left;
        for(int i = 0; i < epochs; i++) {
            trainingData.shuffle();

            left = trainingData.length();

            Data[] miniBatches = new Data[(trainingData.length() - 1) / miniBatchSize + 1];
            for(int j = 0; j < trainingData.length(); j += miniBatchSize) {

                miniBatches[j].setSize(Math.min(left, miniBatchSize));

                for(int k = 0; k < miniBatches[i].length(); i++) {
                    miniBatches[j].setImage(k, trainingData.getImage(j + k));
                    miniBatches[j].setLabel(k, trainingData.getLabel(j + k));
                }
                left -= miniBatchSize;
            }

            for(Data miniBatch : miniBatches) {
                updateMiniBatch(miniBatch, eta);
            }

            if(testData.getImages() != null) {
                System.out.println("Epoch: " + i + " " + evaluate(testData) + " " + testData.length());
            }
            else {
                System.out.println("Epoch: " + i + " complete.");
            }
        }
    }

    private Data backPropagation(double[] image, double[] label) {
        Data nabla = new Data();
        nabla.setSize(this.biases.length, new int[]{this.weights[0].length, this.weights[0][0].length});
        nabla.fill(0);


        return new Data();
    }

    private void updateMiniBatch(Data miniBatchData, double eta) {

        Data nabla = new Data();
        nabla.setSize(this.weights.length, new int[]{this.weights[0].length, this.weights[0][0].length});
        nabla.fill(0);

        Data delta;

        for(int i = 0; i < miniBatchData.length(); i++) {
            delta = backPropagation(miniBatchData.getImage(i), miniBatchData.getLabel(i));
        }
    }

    private int evaluate(Data testData) {
        return 0;
    }
}