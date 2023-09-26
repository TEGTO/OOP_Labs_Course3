package electricalDevices.types.householdDevices.types;
import electricalDevices.additionalClasses.Timer;
import electricalDevices.types.householdDevices.HouseholdDevice;
import electricalDevices.additionalClasses.WorkingMode;
import myLogger.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class WashingMachine extends HouseholdDevice
{
    public enum WashingMode
    {
        Wash, Spin, WashAndSpin
    }

    public Timer washingTimerInSeconds;
    List<String> clothesNames = new ArrayList<String>();
    WashingMode washingMode;
    public WashingMachine(float power, boolean isEnabled, WorkingMode workingMode, WashingMode washingMode, Timer timer)
    {
        super(power, isEnabled, workingMode);
        this.washingMode = washingMode;
        this.washingTimerInSeconds = timer;
    }

    @Override
    public void printInfo()
    {
        MyLogger.printInfoMessage(String.format("WashingMachine with %1$.2f power, %2$s workmode and %3$s washing mode!", getPower(), getWorkingMode().toString(), washingMode.toString()));
    }
    public void setWashingMode(WashingMode washingMode)
    {
        this.washingMode = washingMode;
    }
    @Override
    final public void enableDevice()
    {
        super.enableDevice();
        new Thread(() ->
        {
            float currentTimeOfWorking = 0;
            while (currentTimeOfWorking <= washingTimerInSeconds.getTime() && isEnabled)
            {
                String timeToTheEnd = washingTimerInSeconds.getTime() - currentTimeOfWorking == 1 ? (washingTimerInSeconds.getTime() - currentTimeOfWorking) + " second"
                        : (washingTimerInSeconds.getTime() - currentTimeOfWorking) + " seconds";
                String text = clothesNames.size() > 1 ? "Washing one cloth!" : String.format("Washing %1$s clothes!", clothesNames.size());
                MyLogger.printInfoMessage(String.format("WashingMachine: %1$s WashingMode is %2$s on %3$s! ", text, washingMode, getWorkingMode().toString()) + timeToTheEnd + " to the end!");
                currentTimeOfWorking++;
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
            }
            if (isEnabled)
                MyLogger.printInfoMessage("WashingMachine: Washing is over!");
            isEnabled = false;
        }).start();
    }
}
