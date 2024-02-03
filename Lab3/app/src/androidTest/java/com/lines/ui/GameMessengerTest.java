package com.lines.ui;
import android.content.Context;
import android.widget.TextView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;
import com.lines.StartGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameMessengerTest
{
    TextView scoreView;
    TextView infoView;
    GameMessenger messenger;

    @Before
    public void setUp()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        scoreView = new TextView(appContext);
        infoView = new TextView(appContext);
        try (ActivityScenario<StartGame> scenario = ActivityScenario.launch(StartGame.class))
        {
            scenario.onActivity(activity ->
            {
                messenger = new GameMessenger(activity, scoreView, infoView);
            });
        }
    }
    @Test
    public void updateScoreLabelExpectedUpdate()
    {
        scoreView.setText("1");
        Assert.assertEquals("1", scoreView.getText());
        messenger.updateScoreLabel(2);
        Assert.assertEquals("Score: 2", scoreView.getText());
    }
    @Test
    public void printInfoMessageExpectedPrinted()
    {
        infoView.setText("1");
        Assert.assertEquals("1", infoView.getText());
        messenger.printInfoMessage("ffff");
        Assert.assertEquals("ffff", infoView.getText());
    }
}