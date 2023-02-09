import Maths.*;

import java.util.Random;

class Nabla {
    Vector[] biases;
    Matrix[] weights;

    public Nabla() {}

    void setSize(int[] sizes) {

        biases = new Vector[sizes.length -1];
        Random random = new Random();
        for(int i = 0; i < biases.length; i++) {
            biases[i] = new Vector(sizes[i + 1]);
        }

        weights = new Matrix[sizes.length - 1];
        for(int i = 0; i < sizes.length - 1; i++) {
            weights[i] = new Matrix(sizes[i + 1], sizes[i]);
        }
    }

    int length() {
        return biases.length;
    }

    Vector getBiases(int idx) {
        return biases[idx];
    }

    Matrix getWeights(int idx) {
        return weights[idx];
    }

    void setBiases(int idx, Vector biases) {
        this.biases[idx] = biases;
    }

    void setWeights(int idx, Matrix weights) {
        this.weights[idx] = weights;
    }

    void updateBiases(Nabla delta) {
        for(int i = 0; i < biases.length; i++) {
            biases[i] = Maths.add(biases[i], delta.getBiases(i));
        }
    }

    void updateWeights(Nabla delta) {
        for(int i = 0; i < weights.length; i++) {
            weights[i] = Maths.add(weights[i], delta.getWeights(i));
        }
    }

    void fill(double value) {
        for(int i = 0; i < biases.length; i++) {
            biases[i].fill(0);
            weights[i].fill(0);
        }
    }
}
