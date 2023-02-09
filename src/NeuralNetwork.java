import Maths.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private static double sigmoidDerivative(double z) {
        return sigmoid(z) * (1 - sigmoid(z));
    }

    private static double[] sigmoid(double[] z) {
        double[] result = new double[z.length];
        for(int i = 0; i < z.length; i++) {
            result[i] = sigmoid(z[i]);
        }
        return result;
    }
    private static double[] sigmoidDerivative(double[] z) {
        double[] result = new double[z.length];
        for(int i = 0; i < z.length; i++) {
            result[i] = sigmoidDerivative(z[i]);
        }
        return result;
    }
    private static double[][] sigmoid(double[][] z) {
        double[][] result = new double[z.length][];
        for(int i = 0; i < z.length; i++) {
            result[i] = sigmoid(z[i]);
        }
        return result;
    }

    public double[] feedforward(double[] vector) {

        for(int phase = 0; phase < layersNumber - 1; phase++) {
            vector = sigmoid(
                    Matrix.add(
                    Matrix.multiply(
                    weights[phase], vector), biases[phase]));
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

    public Nabla backPropagation(double[] image, double[] label) {
        Nabla nabla = new Nabla();
        nabla.setSize(sizes);
        nabla.fill(0);


//  Feedforward
        double[][] activation = new double[1][];
        activation[0] = image;

        List<double[][]> activations = new ArrayList<>();
        activations.add(activation);

        List<double[][]> zs = new ArrayList<>();

        for(int phase = 0; phase < layersNumber - 1; phase++) {

            double[][] z = Matrix.weirdAddition(
                    Matrix.multiply(
                            weights[phase], activation
                    ),
                    biases[phase]
            );
            System.out.println(Arrays.deepToString(z));
            zs.add(z);

            activation = sigmoid(z);
            activations.add(activation);
        }

//  Backward pass

//        double[] temp = sigmoidDerivative(zs.get(zs.size() - 1));

//        double[] delta = Matrix.vectorMultiply(
//            costDerivative(activations.get(activation.length - 1), label), temp);

//        nabla.setBiases(nabla.length() - 1, delta);



        return nabla;
    }


    double[] costDerivative(double[] outputActivations, double[] y) {
        return Matrix.vectorSubtract(outputActivations, y);
    }


    private void updateMiniBatch(Data miniBatchData, double eta) {

        Nabla nabla = new Nabla();
        nabla.setSize(sizes);
        nabla.fill(0);

        Nabla delta;

        for(int i = 0; i < miniBatchData.length(); i++) {
            delta = backPropagation(miniBatchData.getImage(i), miniBatchData.getLabel(i));
        }
    }

    private int evaluate(Data testData) {
        return 0;
    }
}