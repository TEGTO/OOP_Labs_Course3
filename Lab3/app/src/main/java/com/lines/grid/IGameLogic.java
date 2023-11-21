package com.lines.grid;
public interface IGameLogic
{
    void onCellChangePosition(Cell cell);
    void onGameStart();
    void restartGame();
}