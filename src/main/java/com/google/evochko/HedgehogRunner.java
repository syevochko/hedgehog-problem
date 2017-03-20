package com.google.evochko;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Pattern;

public class HedgehogRunner {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("You have to point 2 input params: input and output file, like this:");
            System.err.println("HedgehogWrapper.class d:/temp/in.txt d:/temp/out.txt");
            return;
        }

        String inFile = args[0];
        String outFile = args[1];
        Path in = Paths.get(inFile);

        if (Files.notExists(in)) {
            System.err.println("File not found: " + inFile);
            return;
        }

        int[][] apples = getIntArrayFromFile(in);
        if (apples == null) {
            System.err.println("Can't create int array from file: " + inFile);
            return;
        }

        Hedgehog hed = new Hedgehog();
        hed.setApples(apples);
        System.out.println("Apples:");
        printArray(hed.getApples());

        int maxApplesCount = hed.getMaxApplesCount();
        System.out.println("Weights:");
        printArray(hed.getWeights());
        System.out.println("Max apple count is " + maxApplesCount);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outFile), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(String.valueOf(maxApplesCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[][] getIntArrayFromFile(Path in) {
        try {
            List<String> rows = Files.readAllLines(in);
            int apples[][] = new int[rows.size()][];
            int i = 0;
            int maxColumn = 0;
            for (String nums : rows) {
                nums = Pattern.compile("\\s+").matcher(nums.trim()).replaceAll("-");
                String[] counts = nums.split("-");
                if (counts.length > maxColumn) {
                    maxColumn = counts.length;
                }
                apples[i] = new int[counts.length];
                int j = 0;
                for (String count : counts) {
                    int n = Integer.valueOf(count);
                    apples[i][j] = n;
                    j++;
                }
                i++;
            }

            int[][] retArray = new int[apples.length][maxColumn];       // we need to align columns count
            for (int k = 0; k < apples.length; k++) {
                System.arraycopy(apples[k], 0, retArray[k], 0, apples[k].length);
            }
            return retArray;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void printArray(int arr[][]) {
        if (arr != null) {
            for (int[] anArr : arr) {
                for (int anAnArr : anArr) {
                    System.out.print(anAnArr + "\t");
                }
                System.out.println();
            }
        }
    }
}
