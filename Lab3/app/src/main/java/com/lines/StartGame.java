package com.lines;

import android.graphics.Color;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.lines.grid.Cell;
import com.lines.grid.Grid;
import com.lines.ui.GameMessenger;

import java.util.ArrayList;
import java.util.List;

import static com.lines.GameSettingsConstants.GAME_FIELD_COLUMNS;
import static com.lines.GameSettingsConstants.GAME_FIELD_ROWS;

public class StartGame extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Integer> colors = CircleColor.CircleColors();
        // Get the grid view
        GridView gridView = findViewById(R.id.gridLayout);
        TextView scoreView = findViewById(R.id.scoreLabel);
        TextView infoView = findViewById(R.id.infoText);
        ImageButton restartButton = findViewById(R.id.restartButton);
        GameMessenger messenger = new GameMessenger(this, scoreView, infoView);
        messenger.updateScoreLabel(0);
        int numRows = GAME_FIELD_ROWS;
        int numColumns = GAME_FIELD_COLUMNS;
        List<Cell> cellList = new ArrayList<>();
        setCellsInChequerwiseBackground(cellList, numRows, numColumns);
        Cell[][] cellsMatrix = transferListToMatrix(cellList, numRows, numColumns);
        // Create the custom adapter and set it to the grid view
        Grid gridAdapter = new Grid(this, android.R.layout.simple_list_item_1, cellList, cellsMatrix);
        GameLogic gameLogic = new GameLogic(cellList, cellsMatrix, gridAdapter, colors, messenger);
        restartButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gameLogic.restartGame();
            }
        });
        gridView.setAdapter(gridAdapter);
    }
    private void setCellsInChequerwiseBackground(List<Cell> cellList, int numRows, int numColumns)
    {
        for (int i = 0; i < numRows * numColumns; i++)
        {
            int backgroundColor = i % 2 == 0 ? Color.parseColor("#858585") : Color.parseColor("#c6c6c6");
            int circleColor = 0;
            cellList.add(new Cell(backgroundColor, false, circleColor));
        }
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