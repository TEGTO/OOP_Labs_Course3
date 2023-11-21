package com.lines.grid;
public class Cell
{
    private int backGroundColor;
    private boolean isOccupied;
    private int circleColor;
    public Cell(int color, boolean isOccupied, int circleColor)
    {
        this.backGroundColor = color;
        this.isOccupied = isOccupied;
        this.circleColor = circleColor;
    }
    public int getBackgroundColor()
    {
        return backGroundColor;
    }
    public boolean isOccupied()
    {
        return isOccupied;
    }
    public void setOccupied(boolean isOccupied) {this.isOccupied = isOccupied;}
    public void setCircleColor(int color) {this.circleColor = color;}
    public int getCircleColor()
    {
        return circleColor;
    }
}