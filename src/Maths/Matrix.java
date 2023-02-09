package Maths;

import java.util.Arrays;
import java.util.stream.Stream;

public class Matrix {

    public static double[] multiply(double[][] m1, double[] v1) {

        if(m1[0].length != v1.length) {
            System.out.println(m1[0].length + " " + v1.length);
        }

        double[] result = new double[m1.length];
        Arrays.fill(result, 0);

        for(int i = 0; i < m1.length; i++) {
            for(int j = 0; j < v1.length; j++) {
                result[i] += m1[i][j] * v1[j];
            }
        }
        return result;
    }
    public static double[][] multiply(double[][] m1, double[][] m2) {

        double[][] result = new double[m2.length][m1[0].length];

        for(int resultRow = 0; resultRow < m1.length; resultRow++) {
            for(int resultCol = 0; resultCol < m2.length; resultCol++) {

                int newElement = 0;
                for(int index = 0; index < m1[0].length; index++) {
                    newElement += m1[resultRow][index] * m2[resultCol][index];
                }

                result[resultCol][resultRow] = newElement;
            }
        }
        return result;
    }
    public static double[][] weirdAddition(double[][] activation, double[] biases) {
        double[][] product = new double[biases.length][activation[0].length];
        for(int i = 0; i < biases.length; i++) {
            product[i] = activation[Math.min(i, activation.length - 1)];
            for(int j = 0; j < activation[0].length; j++) {
                product[i][j] += biases[i];
            }
        }
        return product;
    }

    public static int determinant(int[][] matrix) {
        return determinant(matrix, 0);
    }
    private static int determinant(int [][] matrix, int depth) {
        int n = matrix.length;

        if(n == 1 && depth == matrix[0].length - 1) {
            return matrix[0][depth];
        }

        int stageSum = 0;
        for(int i = 0; i < n; i++) {
            int sign = (i + 2 * depth) % 2 == 0 ? 1 : (-1);

            int[][] nextMatrix = Stream.concat(Arrays.stream(Arrays.copyOfRange(matrix, 0, i)),
                            Arrays.stream(Arrays.copyOfRange(matrix, i + 1, n)))
                    .toArray(int[][]::new);

            stageSum += matrix[i][depth] * sign * determinant(nextMatrix, depth + 1);
        }

        return stageSum;
    }

    public static int[][] add(int[][] m1, int[][] m2) {

        try {
            for(int i = 0; i < m1.length; i++) {
                for(int j = 0; j < m1[0].length; j++) {
                    m1[i][j] += m2[i][j];
                }
            }
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException) {
            System.err.println(indexOutOfBoundsException.getMessage());
        }

        return m1;
    }

    public static double[] add(double[] vector, double[] biases) {
        for(int i = 0; i < vector.length; i++) {
            vector[i] += biases[i];
        }
        return vector;
    }

    public static void print(int[][] matrix) {
        try {
            for(final int[] ints : matrix) {
                for(int j = 0; j < matrix[0].length; j++) {
                    System.out.print(ints[j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException) {
            System.err.println("Incorrect matrix.");
        }
    }

    public static double[] vectorSubtract(double[] v1, double[] v2) {
        double[] res = new double[v1.length];
        for(int i = 0; i < v1.length; i++) {
            res[i] = v1[i] - v2[i];
        }
        return res;
    }


    public static double dotProduct(double[] v1, double[] v2) {

        double product = 0;
        for(int i = 0; i < v1.length; i++) {
            product += v1[i] * v2[i];
        }
        return product;
    }

}