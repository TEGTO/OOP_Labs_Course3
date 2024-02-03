package com.lines.ui;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.widget.TextView;
import com.lines.R;

public class GameMessenger
{
    private TextView scoreView;
    private TextView infoView;
    private Context context;

    public GameMessenger(Activity activity, TextView scoreView, TextView infoView)
    {
        this.context = activity;
        this.scoreView = scoreView;
        this.infoView = infoView;
    }
    public void updateScoreLabel(Integer score)
    {
        String scoreText = "";
        try
        {
            scoreText = context.getResources().getString(R.string.score, score);
        }
        catch (Resources.NotFoundException e)
        {
            scoreText = score.toString();
        }
        scoreView.setText(scoreText);
    }
    public void printInfoMessage(String messege)
    {
        infoView.setText(messege);
    }
}
