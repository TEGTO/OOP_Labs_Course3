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
import java.util.Arrays;
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
        List<Integer> colors = Arrays.asList(Color.RED, Color.BLUE, Color.GREEN, Color.parseColor("#960ac9"), Color.parseColor("#e08210"));
        // Get the grid view
        GridView gridView = findViewById(R.id.gridLayout);
        TextView scoreView = findViewById(R.id.scoreLabel);
        TextView infoView = findViewById(R.id.infoText);
        ImageButton restartButton = findViewById(R.id.restartButton);
        GameMessenger messenger = new GameMessenger(this, scoreView, infoView);
        messenger.updateScoreLabel(0);

        // Define the number of rows and columns in the grid
        int numRows = GAME_FIELD_ROWS;
        int numColumns = GAME_FIELD_COLUMNS;
        // Create a list of cells for the 9x9 grid
        List<Cell> cellList = new ArrayList<>();
        for (int i = 0; i < numRows * numColumns; i++)
        {
            int color = i % 2 == 0 ? Color.parseColor("#858585") : Color.parseColor("#c6c6c6");
            int circleColor = 0; // Set circle colors as needed
            cellList.add(new Cell(color, false, circleColor));
        }
        Cell[][] cellsMatrix = new Cell[numRows][numColumns];
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numColumns; j++)
                cellsMatrix[i][j] = cellList.get(i + j);
        }
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
}