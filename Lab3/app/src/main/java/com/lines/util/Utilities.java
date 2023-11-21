package com.lines.util;
public class Utilities
{
    public static <T> int[] findElementIndexes(T[][] matrix, T target)
    {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numCols; j++)
            {
                if (matrix[i][j].equals(target))
                {
                    return new int[]{i, j}; // Found the target element
                }
            }
        }
        return null; // Element not found in the matrix
    }
}
