package Maths;

import java.util.Arrays;

public class Matrix {

    double[][] values;

    public Matrix(int depth, int width) {
        values = new double[depth][width];
    }

    public Matrix(int depth) {
        values = new double[depth][1];
    }


    public Matrix reshape(int depth, int width) {

        if(depth * width != values.length * values[0].length) {
            System.out.println("Wrong dimensions");
        }
        Matrix matrix = new Matrix(depth, width);
        double[][] result = new double[depth][width];

        int index = 0;
        while(index != depth * width) {
            result[index / depth][index % width] =
                    values[index / values.length][index % values[0].length];
        }
        return matrix;
    }

    double[] getRow(int idx) {
        return this.values[idx];
    }

    public int depth() {
        return values.length;
    }
    public int width() {
        return values[0].length;
    }
    public int elements() {
        return values.length * values[0].length;
    }

    public void set(int row, int column, double value) {
        this.values[row][column] = value;
    }
    public void set(int row, double[] value) {
        values[row] = value;
    }

    public double get(int row, int col) {
        return this.values[row][col];
    }


}

