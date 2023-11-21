package com.lines;
import com.lines.grid.Cell;
import com.lines.grid.Grid;
import com.lines.grid.IGameLogic;
import com.lines.ui.GameMessenger;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GameLogic implements IGameLogic
{
    private static final int POINTS_FOR_CIRCLE = 5;
    private static final int CIRCLE_IN_ROW_AT_LEAST = 5;
    private static final int AMOUNT_OF_NEW_CIRCLES_PER_MOVE = 3;
    private List<Cell> cellList;
    private Cell[][] cellMatrix;
    private Grid grid;
    private List<Integer> colors;
    private GameMessenger messanger;
    private int currentScore = 0;
    public GameLogic(List<Cell> cellList, Cell[][] cellMatrix, Grid grid, List<Integer> colors, GameMessenger messanger)
    {
        this.cellList = cellList;
        this.cellMatrix = cellMatrix;
        this.colors = colors;
        this.messanger = messanger;
        this.grid = grid;
        grid.setOnCellMove(cell ->
        {
            messanger.printInfoMessage("");
            onCellChangePosition(cell);
        });
        grid.setOnHasNoPath(cell ->
        {
            messanger.printInfoMessage("Can't find the path!");
        });
        onGameStart();
    }

    @Override
    public void onCellChangePosition(Cell cell)
    {
        removeLinesAndAwardPoints(cellMatrix);
        nextGameMove();
        removeLinesAndAwardPoints(cellMatrix);
    }
    @Override
    public void onGameStart()
    {
        nextGameMove();
    }
    @Override
    public void restartGame()
    {
        for (Cell cell : cellList)
        {
            cell.setOccupied(false);
            cell.setCircleColor(0);
        }
        messanger.setGameFinished(false);
        messanger.printInfoMessage("");
        messanger.updateScoreLabel(0);
        grid.restartAdapter();
        onGameStart();
    }
    private void nextGameMove()
    {
        List<Cell> unoccupiedCells = cellList.stream().filter(cell -> !cell.isOccupied()).collect(Collectors.toList());
        if (unoccupiedCells.size() <= 0)
        {
            finishGame();
            return;
        }
        Random random = new Random();
        Cell cell;
        for (int i = 0; i < AMOUNT_OF_NEW_CIRCLES_PER_MOVE; i++)
        {
            if (!unoccupiedCells.isEmpty())
            {
                cell = unoccupiedCells.get(random.nextInt(unoccupiedCells.size()));
                cell.setOccupied(true);
                cell.setCircleColor(colors.get(random.nextInt(colors.size())));
                unoccupiedCells = cellList.stream().filter(cell1 -> !cell1.isOccupied()).collect(Collectors.toList());
            }
            if (unoccupiedCells.isEmpty())
            {
                finishGame();
                return;
            }
        }
    }
    public void removeLinesAndAwardPoints(Cell[][] grid)
    {
        // Check rows and remove lines
        currentScore += removeLines(grid);
        // Transpose the grid to check columns
        Cell[][] transposedGrid = transposeGrid(grid);
        // Check columns and remove lines
        currentScore += removeLines(transposedGrid);
        messanger.updateScoreLabel(currentScore);
    }
    // Helper method to remove cells and count points in a line
    private int removeLines(Cell[][] lines)
    {
        int totalPoints = 0;

        for (int i = 0; i < lines.length; i++)
        {
            int consecutiveCount = 1;

            for (int j = 1; j <= lines[i].length; j++)
            {
                if (j < lines[i].length && lines[i][j].isOccupied() && lines[i][j].getCircleColor() == lines[i][j - 1].getCircleColor())
                    consecutiveCount++;
                else
                {
                    if (consecutiveCount >= CIRCLE_IN_ROW_AT_LEAST)
                    {
                        // Remove cells and award points
                        for (int k = j - 1; k > j - 1 - consecutiveCount; k--)
                        {
                            lines[i][k].setOccupied(false);
                            totalPoints += POINTS_FOR_CIRCLE; // Award 5 points for each removed cell
                        }
                    }
                    consecutiveCount = 1;
                }
            }
        }
        return totalPoints;
    }
    // Helper method to transpose a grid (swap rows with columns)
    private Cell[][] transposeGrid(Cell[][] originalGrid)
    {
        int numRows = originalGrid.length;
        int numCols = originalGrid[0].length;
        Cell[][] transposedGrid = new Cell[numCols][numRows];
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numCols; j++)
                transposedGrid[j][i] = originalGrid[i][j];
        }
        return transposedGrid;
    }
    private void finishGame()
    {
        messanger.printInfoMessage("Game is finished! Your final score: " + currentScore);
        messanger.setGameFinished(true);
    }
}
