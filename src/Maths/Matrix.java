package Maths;

import java.util.Arrays;
import java.util.Random;

public class Matrix {

    double[][] values;

    public Matrix(int depth, int width) {
        values = new double[depth][width];
    }

    protected Matrix(int depth) {
        values = new double[depth][1];
    }

    public Matrix transpose() {
        Matrix result = new Matrix(values[0].length, values.length);
        for(int i = 0; i < values[0].length; i++) {
            for(int j = 0; j < values.length; j++) {
                result.set(i, j, values[j][i]);
            }
        }
        return result;
    }

    public Matrix reshape(int depth, int width) {

        if(depth * width != values.length * values[0].length) {
            System.out.println("Wrong dimensions");
        }
        Matrix matrix = new Matrix(depth, width);

        int index = 0;
        while(index != depth * width) {
            matrix.set(index / depth, index % width, values[index / values.length][index % values[0].length]);
            index++;
        }
        return matrix;
    }

    public static Matrix unitMatrix(int depth) {
        Matrix unitMatrix = new Matrix(depth, depth);
        double row[];
        for(int i = 0; i < depth; i++) {
            row = new double[depth];
            Arrays.fill(row, 0);
            row[i] = 1.0;
            unitMatrix.set(i, row);
        }
        return unitMatrix;
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

    public void print() {
        for(final double[] value : values) {
            System.out.println(Arrays.toString(value));
        }
    }

    public void fill(double value) {
        for(final double[] doubles : this.values) {
            Arrays.fill(doubles, value);
        }
    }

    public void fillRandom() {
        Random random = new Random();
        for(int i = 0; i < values.length; i++) {
            for(int j = 0; j < values[0].length; j++) {
                set(i, j, random.nextInt(10));
            }
        }
    }
}

