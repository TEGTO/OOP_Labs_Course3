package com.lines.grid;
import java.util.LinkedList;
import java.util.Queue;

public class CellMatrixPathFinding
{
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static boolean hasPath(Cell[][] matrix, int startX, int startY, int endX, int endY)
    {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (startX < 0 || startX >= numRows || startY < 0 || startY >= numCols || endX < 0 || endX >= numRows || endY < 0 || endY >= numCols)
            return false; // Invalid start or end position
        if (matrix[endX][endY].isOccupied())
            return false; // Start or end cell is occupied

        boolean[][] visited = new boolean[numRows][numCols];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty())
        {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];

            if (currentX == endX && currentY == endY)
                return true; // Path found

            for (int[] dir : DIRECTIONS)
            {
                int newX = currentX + dir[0];
                int newY = currentY + dir[1];

                if (isValidMove(newX, newY, numRows, numCols) && !visited[newX][newY] && !matrix[newX][newY].isOccupied())
                {
                    queue.offer(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }
        return false; // No path found
    }
    private static boolean isValidMove(int x, int y, int numRows, int numCols)
    {
        return x >= 0 && x < numRows && y >= 0 && y < numCols;
    }
}
