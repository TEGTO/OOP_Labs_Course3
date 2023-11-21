package com.lines.grid;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import com.lines.R;
import com.lines.util.MyDelegate;
import com.lines.util.Utilities;

import java.util.List;


public class Grid extends ArrayAdapter<Cell>
{
    private int selectedPosition = -1; // Store the position of the selected cell
    private Cell[][] gridMatrix; // 2D array to represent the grid matrix
    private MyDelegate onCellMove;
    private MyDelegate onHasNoPath;
    public Grid(Context context, int resource, List<Cell> objects, Cell[][] cellsMatrix)
    {
        super(context, resource, objects);
        this.gridMatrix = cellsMatrix;
    }
    public void setOnCellMove(MyDelegate onCellMove)
    {
        this.onCellMove = onCellMove;
    }
    public void setOnHasNoPath(MyDelegate onHasNoPath)
    {
        this.onHasNoPath = onHasNoPath;
    }
    public void performAction(MyDelegate action, Cell cell)
    {
        if (action != null)
            action.onDelegateAction(cell);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null)
        {
            // If it's not recycled, initialize some attributes
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100)); // Adjust size as needed
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else
            imageView = (ImageView) convertView;
        // Get the current cell
        final Cell cell = getItem(position);
        // Update the corresponding cell in the grid matrix
        int row = position / gridMatrix[0].length;
        int col = position % gridMatrix[0].length;
        gridMatrix[row][col] = cell;
        // Set the background color of the ImageView based on the cell color
        imageView.setBackgroundColor(cell.getBackgroundColor());
        // Add logic to display a filled circle for occupied cells
        if (cell.isOccupied())
        {
            GradientDrawable circleDrawable = createCircleDrawable(cell.getCircleColor());
            // Set a golden stroke if the cell is selected
            if (position == selectedPosition)
                circleDrawable.setStroke(4, Color.parseColor("#FFD700")); // Golden color
            else
            {
                // Clear previous strokes
                circleDrawable.setStroke(0, Color.TRANSPARENT);
            }
            imageView.setImageDrawable(circleDrawable);
            // Set a click listener to handle cell selection
        }
        else
        {
            // The cell is unoccupied
            // Clear any previous content
            imageView.setImageResource(0);
        }
        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Check if the cell is occupied
                if (cell.isOccupied())
                {
                    // Toggle the selection
                    if (position == selectedPosition)
                        selectedPosition = -1; // Deselect the cell
                    else
                        selectedPosition = position; // Select the cell
                    notifyDataSetChanged(); // Refresh the grid view
                }
                else
                {
                    // Cell is unoccupied, move the selected circle if there is a selection
                    if (selectedPosition != -1)
                    {
                        // Get the selected cell and move the circle
                        Cell selectedCell = getItem(selectedPosition);
                        Cell toCell = getItem(position);
                        int[] selectedCellIndexes = Utilities.findElementIndexes(gridMatrix, selectedCell);
                        boolean hasPath = CellMatrixPathFinding.hasPath(gridMatrix, selectedCellIndexes[0], selectedCellIndexes[1], row, col);
                        if (hasPath)
                        {
                            moveCircle(selectedCell, toCell);
                            selectedPosition = -1; // Deselect the cell after moving
                            performAction(onCellMove, selectedCell);
                            notifyDataSetChanged(); // Refresh the grid view
                        }
                        else
                        {
                            performAction(onHasNoPath, selectedCell);
                        }
                    }
                }
            }
        });
        return imageView;
    }

    public void restartAdapter() {

        notifyDataSetChanged();  // Notify the adapter that the data has changed

        // Update the grid matrix reference
        selectedPosition = -1;  // Reset the selected position
    }
    // Helper method to create a GradientDrawable for a filled circle with a border
    private GradientDrawable createCircleDrawable(int circleColor)
    {
        Drawable myCircle = AppCompatResources.getDrawable(getContext(), R.drawable.circle);
        GradientDrawable gradientDrawable = (GradientDrawable) myCircle;
        gradientDrawable.setColor(circleColor);
        // Set border properties (you can adjust the color and width as needed)
        return gradientDrawable;
    }
    private void moveCircle(Cell fromCell, Cell toCell)
    {
        // Handle the logic to move the circle from 'fromCell' to the cell at 'toPosition'
        // You need to update the 'Cell' objects in your dataset accordingly
        // For example, swap the state of 'fromCell' and the cell at 'toPosition'
        toCell.setOccupied(true);
        toCell.setCircleColor(fromCell.getCircleColor());
        fromCell.setOccupied(false);
        fromCell.setCircleColor(0); // Set to the default color and unoccupied state
    }
}
