package Maths;

import java.util.Arrays;
import java.util.stream.Stream;

public class Matrix {

    public static int[][] multiply(int[][] m1, int[][] m2) {

        int[][] result = new int[m1.length][m2[0].length];

        try {
            for(int resultRow = 0; resultRow < m1[0].length; resultRow++) {
                for(int resultCol = 0; resultCol < m2[0].length; resultCol++) {

                    int newElement = 0;
                    for(int index = 0; index < m1[0].length; index++) {
                        newElement += m1[resultRow][index] * m2[index][resultCol];
                    }

                    result[resultRow][resultCol] = newElement;
                }
            }
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException) {
            System.err.println(indexOutOfBoundsException.getMessage());
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

    public static double dotProduct(double[] v1, double[] v2) {

        double product = 0;
        for(int i = 0; i < v1.length; i++) {
            product += v1[i] * v2[i];
        }
        return product;
    }

}