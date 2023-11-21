package com.lines.ui;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import com.lines.R;


public class GameMessenger
{
    private TextView scoreView;
    private TextView infoView;
    private Context context;  // Add a Context field
    private boolean isGameFinished = false;  // Add a Context field

    public GameMessenger(Activity activity, TextView scoreView, TextView infoView)
    {
        this.context = activity;  // Initialize the context with the activity
        this.scoreView = scoreView;
        this.infoView = infoView;
    }

    public void updateScoreLabel(int score)
    {
        // Use the context to get resources
        String scoreText = context.getResources().getString(R.string.score, score);
        scoreView.setText(scoreText);
    }
    public void printInfoMessage(String messege)
    {
        if(!isGameFinished)
        infoView.setText(messege);
    }
    public boolean isGameFinished()
    {
        return isGameFinished;
    }
    public void setGameFinished(boolean gameFinished)
    {
        isGameFinished = gameFinished;
    }
}
