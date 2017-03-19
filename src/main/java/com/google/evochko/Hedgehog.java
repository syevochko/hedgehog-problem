package com.google.evochko;

public class Hedgehog {
    private int[][] apples;
    private int[][] weights;

    public static void main(String[] args) {
        Hedgehog hed = new Hedgehog();
        hed.apples = new int[][]{{3, 4, 2}, {0, 1, 9}, {12, 5, 4}, {1, 20, 3}};
        hed.printArray(hed.apples);
        System.out.println(hed.getMaxApplesCount());
        hed.printArray(hed.weights);
    }

    public void printArray(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void setApples(int[][] apples) {
        this.apples = apples;
        weights = new int[apples.length][apples[0].length];
    }

    public int getMaxApplesCount() {
        weights = new int[apples.length][apples[0].length];
        for (int i = 0; i < apples.length; i++) {
            for (int j = 0; j < apples[i].length; j++) {
                int lw = (j - 1) >= 0 ? weights[i][j - 1] : 0;
                int uw = (i - 1) >= 0 ? weights[i - 1][j] : 0;
                weights[i][j] = apples[i][j] + ((lw >= uw) ? lw : uw);
            }
        }
        return weights[apples.length - 1][apples[0].length - 1];
    }
}
