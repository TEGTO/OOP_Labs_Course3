package com.lines;
import android.graphics.Color;

import java.util.Arrays;
import java.util.List;

public abstract class CircleColor
{
    public static final int CIRCLE_NOT_SELECTED_STROKE = android.graphics.Color.parseColor("#FFD700");
    public static final int CIRCLE_SELECTED_STROKE = android.graphics.Color.parseColor("#FFD700");
    public static List<Integer> CircleColors()
    {
        List<Integer> colors = Arrays.asList(
                Color.RED,
                Color.BLUE,
                Color.GREEN,
                Color.parseColor("#960ac9"),
                Color.parseColor("#e08210")
        );
        return colors;
    }

}
