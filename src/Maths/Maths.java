package Maths;

public class Maths {

    public static Matrix multiply(Matrix m1, Matrix m2) {
        Matrix result = new Matrix(m1.depth(), m2.width());
        double sum;
        for(int resultRow = 0; resultRow < m1.depth(); resultRow++) {
            for(int resultCol = 0; resultCol < m2.width(); resultCol++) {
                sum = 0;
                for(int index = 0; index < m1.width(); index++) {
                    sum += m1.get(resultRow, index) * m2.get(index, resultCol);
                }
                result.set(resultRow, resultCol, sum);
            }
        }
        return result;
    }
    public static Vector multiply(Matrix m1, Vector v2) {
        Vector result = new Vector(m1.depth());

        double sum;
        for(int resultRow = 0; resultRow < m1.depth(); resultRow++) {
            sum = 0;
            for(int index = 0; index < m1.width(); index++) {
                sum += m1.get(resultRow, index) * v2.get(index, 0);
            }
            result.set(resultRow, 0, sum);
        }
        return result;
    }
    public static Matrix multi(double scalar, Matrix matrix) {
        Matrix result = new Matrix(matrix.depth(), matrix.width());
        for(int i = 0; i < result.depth(); i++) {
            for(int j = 0; j < result.width(); j++) {
                result.set(i, j, scalar * matrix.get(i, j));
            }
        }
        return result;
    }
    public static Vector multi(double scalar, Vector vector) {
        Vector result = new Vector(vector.depth());
        for(int i = 0; i < result.depth(); i++) {
            result.set(i, scalar * vector.get(i));
        }
        return result;
    }
    public static Vector multiplyElementwise(Vector v1, Vector v2) {
        Vector result = new Vector(v1.depth());
        for(int i = 0; i < result.depth(); i++) {
            result.set(i, v1.get(i) * v2.get(i));
        }
        return result;
    }

    public static Matrix add(Matrix m1, Matrix m2) {
        Matrix result = new Matrix(m1.depth(), m2.width());
        try {
            for(int i = 0; i < m1.depth(); i++) {
                for(int j = 0; j < m1.width(); j++) {
                    result.set(i, j, m1.get(i, j) + m2.get(i, j));
                }
            }
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException) {
            System.err.println(indexOutOfBoundsException.getMessage());
        }
        return result;
    }
    public static Vector add(Vector v1, Vector v2) {
        Vector result = new Vector(v1.depth());
        for(int i = 0; i < Math.max(v1.depth(), v2.depth()); i++) {
            result.set(i, v1.get(i) + v2.get(i));
        }
        return result;
    }
    public static Vector subtract(Vector v1, Vector v2) {
        Vector result = new Vector(v1.depth());
        for(int i = 0; i < v1.depth(); i++) {
            result.set(i, v1.get(i) - v2.get(i));
        }
        return result;
    }
    public static Matrix subtract(Matrix m1, Matrix m2) {
        Matrix result = new Matrix(m1.depth(), m2.width());
        try {
            for(int i = 0; i < m1.depth(); i++) {
                for(int j = 0; j < m1.width(); j++) {
                    result.set(i, j, m1.get(i, j) - m2.get(i, j));
                }
            }
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException) {
            System.err.println(indexOutOfBoundsException.getMessage());
        }
        return result;
    }

    public static double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }
    public static double sigmoidDerivative(double z) {
        return sigmoid(z) * (1 - sigmoid(z));
    }

    public static Vector sigmoid(Vector z) {
        Vector result = new Vector(z.depth());
        for(int i = 0; i < z.depth(); i++) {
            for(int j = 0; j < z.width(); j++) {
                result.set(i, j, sigmoid(z.get(i, j)));
            }
        }
        return result;
    }
    public static Vector sigmoidDerivative(Vector z) {
        Vector result = new Vector(z.depth());

        for(int i = 0; i < z.depth(); i++) {
            for(int j = 0; j < z.width(); j++) {
                result.set(i, j, sigmoidDerivative(z.get(i, j)));
            }
        }
        return result;
    }
}