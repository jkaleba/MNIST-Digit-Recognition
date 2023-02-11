import Maths.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Maths.Maths.*;

class NeuralNetwork {

    int[] sizes;
    int layersNumber;
    Vector[] biases;
    Matrix[] weights;

    public NeuralNetwork(int[] sizes) {

        this.sizes = new int[sizes.length];
        this.layersNumber = sizes.length;

        System.arraycopy(sizes, 0, this.sizes, 0, sizes.length);

        biases = new Vector[this.layersNumber -1];
        Random random = new Random();
        for(int i = 0; i < biases.length; i++) {
            biases[i] = new Vector(sizes[i + 1]);
            for(int j = 0; j < biases[i].depth(); j++) {
                biases[i].set(j, random.nextGaussian(0, 1));
            }
        }
/*
        each weights[i] represents all connections weights between the i and i + 1 layer,
        each weights[i][j] represents weights between particular neurons,
 */
        weights = new Matrix[this.layersNumber - 1];
        for(int i = 0; i < layersNumber - 1; i++) {
            weights[i] = new Matrix(sizes[i + 1], sizes[i]);
            for(int j = 0; j < weights[i].depth(); j++) {;
                for(int k = 0; k < weights[i].width(); k++) {
                    weights[i].set(j, k, random.nextGaussian(0, 1));
                }
            }
        }
    }

    public Vector feedforward(Vector vector) {
        for(int phase = 0; phase < layersNumber - 1; phase++) {
            vector = sigmoid(add(multiply(weights[phase], vector), biases[phase]));
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
            for(int j = 0; j < miniBatches.length; j += 1) {
                miniBatches[j] = new Data(Math.min(left, miniBatchSize), new int[]{28, 28});

                for(int k = 0; k < miniBatches[j].length(); k++) {
                    miniBatches[j].setImage(k, trainingData.getImage(trainingData.length() - left));
                    miniBatches[j].setLabel(k, trainingData.getLabel(trainingData.length() - left));
                }
                left -= miniBatchSize;
            }

            for(Data miniBatch : miniBatches) {
                updateMiniBatch(miniBatch, eta);
            }

            if(testData != null) {
                System.out.println("Epoch: " + (i + 1) + " " + evaluate(testData) + " " + testData.length());
            }
            else {
                System.out.println("Epoch: " + (i + 1) + " complete.");
            }
        }
    }

    public Nabla backPropagation(Vector image, Vector label) {
        Nabla nabla = new Nabla();
        nabla.setSize(sizes);
        nabla.fill(0);

////  Feedforward
        Vector activation = image;
        List<Vector> activations = new ArrayList<>();
        activations.add(activation);

        List<Vector> zs = new ArrayList<>();

        for(int phase = 0; phase < layersNumber - 1; phase++) {

            Vector z = Maths.add(Maths.multiply(weights[phase], activation), biases[phase]);
            zs.add(z);

            activation = sigmoid(z);
            activations.add(activation);
        }

//  Backward pass
        Vector delta = multiplyElementwise(costDerivative(
                activations.get(activations.size() - 1), label),
                sigmoidDerivative(zs.get(zs.size() - 1)));

        nabla.setBiases(nabla.length() - 1, delta);
        nabla.setWeights(nabla.length() - 1, multiply(
                delta, activations.get(activations.size() - 2).transpose()));

        for(int i = 2; i < layersNumber; i++) {
            Vector z = zs.get(zs.size() - i);

            Vector sp = sigmoidDerivative(z);

            delta = multiplyElementwise(multiply(weights[weights.length -i + 1].transpose(), delta), sp);

            nabla.setBiases(nabla.length() - i, delta);
            nabla.setWeights(nabla.length() - i, multiply(
                    delta, activations.get(activations.size() - i - 1).transpose()));
        }

        return nabla;
    }

    private Vector costDerivative(Vector outputActivation, Vector y) {
        return subtract(outputActivation, y);
    }

    private void updateMiniBatch(Data miniBatchData, double eta) {
        Nabla nabla = new Nabla();
        nabla.setSize(sizes);
        nabla.fill(0);

        Nabla delta;

        for(int i = 0; i < miniBatchData.length(); i++) {
            delta = backPropagation(miniBatchData.getImage(i), miniBatchData.getLabel(i));

            nabla.updateBiases(delta);
            nabla.updateWeights(delta);
        }

        for(int i = 0; i < layersNumber - 1; i++) {
            weights[i] = subtract(weights[i],
                    Maths.multiply(eta / miniBatchData.length(), nabla.getWeights(i)));

            biases[i] = subtract(biases[i], Maths.multiply(eta / miniBatchData.length(), nabla.getBiases(i)));
        }
    }

    private int evaluate(Data testData) {
        int properly = 0;

        for(int i = 0; i < testData.length(); i++) {

            Vector currentOutput = feedforward(testData.getImage(i));
            if(currentOutput.max() == testData.getLabel(i).max()) {
                properly++;
            }
        }
        return properly;
    }
}