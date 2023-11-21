package com.lines.util;
import com.lines.grid.Cell;

@FunctionalInterface
public interface MyDelegate {
    void onDelegateAction(Cell cell);
}
