package ArraysAndStrings;

import java.util.Arrays;

// LeetCode: https://leetcode.com/problems/set-matrix-zeroes/
public class ZeroMatrix {
    public static int[][] generateRandomMatrix(int rows, int cols, int min,
                                               int max) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                matrix[i][j] = min + (int) (Math.random() * (max - min + 1));
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));
    }

    public static void zeroMatrix(int[][] matrix) {
        /* If matrix[i][j] = 0, then mark matrix[i][0] = 0 and matrix[0][j] = 0.
         * The first column is a row marker.
         * The first row is a column marker.
         * Keep 2 boolean variables to:
         * 1. mark first row should be zeroed.
         * 2. mark first column should be zeroed.
         */
        int rows = matrix.length, cols = matrix[0].length;
        boolean zeroFirstRow = false, zeroFirstCol = false;

        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j)
                if (matrix[i][j] == 0) {
                    if (i == 0)
                        zeroFirstRow = true;
                    if (j == 0)
                        zeroFirstCol = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }

        for (int i = 1; i < rows; ++i)
            if (matrix[i][0] == 0)
                Arrays.fill(matrix[i], 0);

        for (int j = 1; j < cols; ++j)
            if (matrix[0][j] == 0)
                for (int i = 0; i < rows; ++i)
                    matrix[i][j] = 0;

        if (zeroFirstCol)
            for (int i = 0; i < rows; ++i)
                matrix[i][0] = 0;

        if (zeroFirstRow)
            Arrays.fill(matrix[0], 0);
    }

    public static void main(String[] args) {
        int[][] matrix;
        // matrix = generateRandomMatrix(4, 4, -10, 10);
        matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        System.out.println("Original Matrix:");
        printMatrix(matrix);
        zeroMatrix(matrix);
        System.out.println("Zeroed Matrix:");
        printMatrix(matrix);
    }
}
