package com.lines;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;
import com.lines.grid.Cell;
import com.lines.grid.Grid;
import com.lines.ui.GameMessenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameLogicTest
{
    TextView scoreView;
    TextView infoView;
    GameLogic gameLogic;
    List<Cell> cellList;

    @Before
    public void setUp()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        scoreView = new TextView(appContext);
        infoView = new TextView(appContext);
        int numRows = 3;
        int numColumns = 3;
        cellList = new ArrayList<>();
        List<Integer> colors = Arrays.asList(Color.RED, Color.BLUE);
        setCells(cellList, numRows, numColumns);
        Cell[][] cellsMatrix = transferListToMatrix(cellList, numRows, numColumns);
        try (ActivityScenario<StartGame> scenario = ActivityScenario.launch(StartGame.class))
        {
            scenario.onActivity(activity ->
            {
                GameMessenger messenger = new GameMessenger(activity, scoreView, infoView);
                Grid gridAdapter = new Grid(activity, android.R.layout.simple_list_item_1, cellList, cellsMatrix);
                gameLogic = new GameLogic(cellList, cellsMatrix, gridAdapter, colors, messenger);
            });
        }
    }
    @Test
    public void onCellChangePositionExpectChangedScore()
    {
        scoreView.setText("1");
        Assert.assertEquals("1", scoreView.getText());
        cellList.forEach(x -> x.setOccupied(true));
        gameLogic.onCellChangePosition(cellList.get(0));
        Assert.assertNotEquals("1", scoreView.getText());
    }
    @Test
    public void onGameStartExpectedNewOccupiedCells()
    {
        cellList.forEach(x -> x.setOccupied(false));
        List<Cell> unoccupiedCells = cellList.stream().filter(cell -> !cell.isOccupied()).collect(Collectors.toList());
        Assert.assertEquals(cellList.size(), unoccupiedCells.size());
        gameLogic.onGameStart();
        unoccupiedCells = cellList.stream().filter(cell -> !cell.isOccupied()).collect(Collectors.toList());
        Assert.assertNotEquals(cellList.size(), unoccupiedCells.size());
    }
    @Test
    public void onGameStartExpectedGameFinish()
    {
        cellList.forEach(x -> x.setOccupied(true));
        gameLogic.onGameStart();
        Assert.assertTrue(gameLogic.isGameFinised());
    }
    @Test
    public void restartGameExpectGameRestartAndNewOccupiedCells()
    {
        cellList.forEach(x -> x.setOccupied(true));
        List<Cell> unoccupiedCells = cellList.stream().filter(cell -> !cell.isOccupied()).collect(Collectors.toList());
        Assert.assertEquals(0, unoccupiedCells.size());
        gameLogic.restartGame();
        unoccupiedCells = cellList.stream().filter(cell -> !cell.isOccupied()).collect(Collectors.toList());
        Assert.assertNotEquals(0, unoccupiedCells.size());
        Assert.assertNotEquals(cellList.size(), unoccupiedCells.size());
    }
    private void setCells(List<Cell> cellList, int numRows, int numColumns)
    {
        for (int i = 0; i < numRows * numColumns; i++)
            cellList.add(new Cell(0, false, 0));
    }
    private Cell[][] transferListToMatrix(List<Cell> cellList, int numRows, int numColumns)
    {
        Cell[][] cellsMatrix = new Cell[numRows][numColumns];
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numColumns; j++)
                cellsMatrix[i][j] = cellList.get(i + j);
        }
        return cellsMatrix;
    }
}