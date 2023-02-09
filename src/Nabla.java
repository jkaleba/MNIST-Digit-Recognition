import java.util.Arrays;

class Nabla {
    double[][] biases;
    double[][][] weights;

    public Nabla() {}

    void setSize(int[] sizes) {
        biases = new double[sizes.length - 1][];
        for(int i = 0; i < biases.length; i++) {
            biases[i] = new double[sizes[i + 1]];
        }

        weights = new double[sizes.length - 1][][];
        for(int i = 0; i < sizes.length - 1; i++) {
            weights[i] = new double[sizes[i + 1]][];
            for(int j = 0; j < weights[i].length; j++) {
                weights[i][j] = new double[sizes[i]];
            }
        }
    }

    int length() {
        return biases.length;
    }

    void setBiases(int idx, double[] biases) {
        this.biases[idx] = biases;
    }

    void fill(int value) {
        for(int i = 0; i < biases.length; i++) {
            Arrays.fill(biases[i], value);
            for(int j = 0; j < weights[i].length; j++) {
                Arrays.fill(weights[i][j], value);
            }
        }
    }
}
