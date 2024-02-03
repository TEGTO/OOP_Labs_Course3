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
import com.lines.CircleColor;
import com.lines.GameSettingsConstants;
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
    public void setOnCellMoveEvent(MyDelegate onCellMove) {this.onCellMove = onCellMove;}
    public void setOnHasNoPathEvent(MyDelegate onHasNoPath)
    {
        this.onHasNoPath = onHasNoPath;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null)
        {
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    GameSettingsConstants.GRID_WIDTH,
                    GameSettingsConstants.GRID_HEIGHT
            ));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else
            imageView = (ImageView) convertView;
        // Get the current selected cell
        final Cell cell = getItem(position);
        int row = position / gridMatrix[0].length;
        int col = position % gridMatrix[0].length;
        gridMatrix[row][col] = cell;
        // Set the background color of the ImageView based on the cell color
        imageView.setBackgroundColor(cell.getBackgroundColor());
        if (cell.isOccupied())
        {
            GradientDrawable circleDrawable = createCircleDrawable(cell.getCircleColor());
            // Set a golden stroke if the cell is selected
            if (position == selectedPosition)
                circleDrawable.setStroke(4, CircleColor.CIRCLE_SELECTED_STROKE); // Golden color
            else
                circleDrawable.setStroke(0, Color.TRANSPARENT);
            imageView.setImageDrawable(circleDrawable);
        }
        else
        {
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
                        resetSelected(); // Deselect the cell
                    else
                        selectedPosition = position; // Select the cell
                    notifyDataSetChanged(); // Refresh the grid view
                }
                else
                {
                    // Cell is unoccupied, move the selected circle if there is a selection
                    if (checkIfSelected())
                    {
                        // Get the selected cell and move the circle
                        Cell selectedCell = getItem(selectedPosition);
                        Cell toCell = getItem(position);
                        int[] selectedCellIndexes = Utilities.findElementIndexes(gridMatrix, selectedCell);
                        boolean hasPath = CellMatrixPathFinding.hasPath(gridMatrix,
                                selectedCellIndexes[0],
                                selectedCellIndexes[1],
                                row,
                                col
                        );
                        if (hasPath)
                        {
                            moveCircle(selectedCell, toCell);
                            resetSelected();
                            performAction(onCellMove, selectedCell);
                            notifyDataSetChanged();
                        }
                        else
                            performAction(onHasNoPath, selectedCell);
                    }
                }
            }
        });
        return imageView;
    }
    public void restartAdapter()
    {
        notifyDataSetChanged();  // Notify the adapter that the data has changed
        resetSelected();  // Reset the selected position
    }
    private void performAction(MyDelegate action, Cell cell)
    {
        if (action != null)
            action.onDelegateAction(cell);
    }
    private GradientDrawable createCircleDrawable(int circleColor)
    {
        Drawable myCircle = AppCompatResources.getDrawable(getContext(), R.drawable.circle);
        GradientDrawable gradientDrawable = (GradientDrawable) myCircle;
        gradientDrawable.setColor(circleColor);
        return gradientDrawable;
    }
    private void moveCircle(Cell fromCell, Cell toCell)
    {
        toCell.setOccupied(true);
        toCell.setCircleColor(fromCell.getCircleColor());
        fromCell.setOccupied(false);
        fromCell.setCircleColor(Color.TRANSPARENT);
    }
    private void resetSelected()
    {
        selectedPosition = -1;
    }
    private boolean checkIfSelected()
    {
        return selectedPosition != -1;
    }
}
