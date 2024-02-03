package com.lines.grid;
import org.junit.*;

public class CellMatrixPathFindingTest
{
    private static Cell[][] matrix;

    @BeforeClass
    public static void setUp()
    {
        int [][] matrixBuffer =
                {
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 1}
                };
        matrix = new Cell[4][4];
        for (int i = 0; i < matrixBuffer.length; ++i)
        {
            for (int j = 0; j < matrixBuffer[0].length; ++j)
            {
                if(matrixBuffer[i][j] == 1)
                    matrix[i][j] = new Cell(1, false, 1);
                else
                    matrix[i][j] = new Cell(1, true, 1);
            }
        }
    }
    @Test
    public void hasPathExpectedPathIsFound()
    {
        int startX = 0;
        int startY = 1;
        int endX = 3;
        int endY = 3;
        boolean hasPath = CellMatrixPathFinding.hasPath(matrix,startX, startY, endX, endY);
        Assert.assertTrue(hasPath);
    }
    @Test
    public void hasPathExpectedPathIsNotFound()
    {
        int startX = 0;
        int startY = 0;
        int endX = 3;
        int endY = 0;
        boolean hasPath = CellMatrixPathFinding.hasPath(matrix,startX, startY, endX, endY);
        Assert.assertFalse(hasPath);
    }
}