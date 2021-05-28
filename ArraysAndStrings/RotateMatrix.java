package ArraysAndStrings;

import java.util.Arrays;

/*
 * Rotate Matrix: Given an image represented by an NxN matrix, where each
 * pixel in the image is 4 bytes, write a method to rotate the image by 90
 * degrees. Can you do this in place?
 * LeetCode: https://leetcode.com/problems/rotate-image/
 */
public class RotateMatrix {
    public static int[][] generateRandomMatrix(int m, int n, int min, int max) {
        int[][] matrix = new int[4][4];
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 4; ++j)
                matrix[i][j] = (int) (Math.random() * (max - min + 1)) + min;
        return matrix;
    }

    public static void rotateMatrix(int[][] matrix) {
        int n = matrix.length, tmp;
        for (int i = 0; i < n / 2; ++i)
            for (int j = i; j < n - i - 1; ++j) {
                tmp = matrix[i][j];

                // 0,1 = 2,0
                matrix[i][j] = matrix[n - j - 1][i];

                // 2,0 = 3,2
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];

                // 3,2 = 1,3
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];

                // 1,3 = 0,1
                matrix[j][n - i - 1] = tmp;
            }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));
    }

    public static void main(String[] args) {
        int[][] matrix;
        matrix = generateRandomMatrix(4, 4, -10, 10);
        System.out.println("Original Matrix:");
        printMatrix(matrix);
        rotateMatrix(matrix);
        System.out.println("Rotated Matrix:");
        printMatrix(matrix);
    }
}
