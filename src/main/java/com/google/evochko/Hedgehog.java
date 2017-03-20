package com.google.evochko;

import java.io.File;

public class Hedgehog {
    private int[][] apples;
    private int[][] weights;

    public int[][] getWeights() {
        return weights;
    }

    public int[][] getApples() {
        return apples;
    }

    public void setApples(int[][] apples) {
        this.apples = apples;
    }

    public int getMaxApplesCount() {
        weights = new int[apples.length][];
        for (int i = 0; i < apples.length; i++) {
            weights[i] = new int[apples[i].length];
            for (int j = 0; j < apples[i].length; j++) {
                int lw = (j - 1 >= 0 && weights[i].length > j - 1) ? weights[i][j - 1] : 0;
                int uw = (i - 1 >= 0 && weights[i - 1].length > j) ? weights[i - 1][j] : 0;
                weights[i][j] = apples[i][j] + ((lw >= uw) ? lw : uw);
            }
        }
        int lastI = apples.length - 1;
        return weights[lastI][apples[lastI].length - 1];
    }
}
