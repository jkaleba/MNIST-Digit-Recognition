package Maths;

import java.util.Arrays;
import java.util.stream.Stream;

public class Maths {
    public static Matrix multiply(Matrix m1, Matrix m2) {

        Matrix result = new Matrix(m1.depth(), m2.width());
        for(int resultRow = 0; resultRow < m1.depth(); resultRow++) {
            for(int resultCol = 0; resultCol < m2.depth(); resultCol++) {
                for(int index = 0; index < m1.width(); index++) {
                    result.set(resultRow, resultCol, m1.get(resultRow, index) * m2.get(index, resultCol));
                }
            }
        }
        return result;
    }
    public static Vector multiply(Matrix m1, Vector v2) {
        Vector result = new Vector(m1.depth());
        for(int resultRow = 0; resultRow < m1.depth(); resultRow++) {
            for(int index = 0; index < m1.width(); index++) {
                result.set(resultRow, 0, m1.get(resultRow, index) * v2.get(index, 0));
            }
        }
        return result;
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

    public static Matrix add(Matrix m1, Matrix m2) {

        try {
            for(int i = 0; i < m1.depth(); i++) {
                for(int j = 0; j < m1.width(); j++) {
                    m1.set(i, j, m1.get(i, j) + m2.get(i, j));
                }
            }
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException) {
            System.err.println(indexOutOfBoundsException.getMessage());
        }

        return m1;
    }
    public static Vector add(Vector v1, Vector v2) {
        for(int i = 0; i < v1.depth(); i++) {
            v1.set(i, v1.get(i) + v2.get(i));
        }
        return v1;
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

    public static double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }
    public static double sigmoidDerivative(double z) {
        return sigmoid(z) * (1 - sigmoid(z));
    }

    public static Matrix sigmoid(Matrix z) {
        for(int i = 0; i < z.depth(); i++) {
            for(int j = 0; j < z.width(); j++) {
                z.set(i, j, sigmoid(z.get(i, j)));
            }
        }
        return z;
    }
    public static Matrix sigmoidDerivative(Matrix z) {
        for(int i = 0; i < z.depth(); i++) {
            for(int j = 0; j < z.width(); j++) {
                z.set(i, j, sigmoid(z.get(i, j)));
            }
        }
        return z;
    }

    public static Vector sigmoid(Vector z) {
        for(int i = 0; i < z.depth(); i++) {
            for(int j = 0; j < z.width(); j++) {
                z.set(i, j, sigmoid(z.get(i, j)));
            }
        }
        return z;
    }
    public static Vector sigmoidDerivative(Vector z) {
        for(int i = 0; i < z.depth(); i++) {
            for(int j = 0; j < z.width(); j++) {
                z.set(i, j, sigmoid(z.get(i, j)));
            }
        }
        return z;
    }

}