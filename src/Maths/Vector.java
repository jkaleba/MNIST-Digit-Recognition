package Maths;

import java.util.Arrays;

public class Vector extends Matrix {

    public Vector(final int depth) {
        super(depth, 1);
    }

    public void set(int i, double value) {
        super.set(i, 0, value);
    }

    public void fill(double value) {
        for(int i = 0; i < super.values.length; i++) {
            values[i][0] = value;
        }
    }
    public double get(int i) {
        return this.values[i][0];
    }

    public void print() {
        System.out.println(Arrays.deepToString(super.values));
    }

    public int max() {
        double maxV = -10;
        int idx = -1;
        for(int i = 0; i < values.length; i++) {
            if(values[i][0] > maxV) {
                maxV = values[i][0];
                idx = i;
            }
        }
        return idx;
    }
}