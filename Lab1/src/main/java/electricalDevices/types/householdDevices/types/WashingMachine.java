package electricalDevices.types.householdDevices.types;
import electricalDevices.additionalClasses.Timer;
import electricalDevices.types.householdDevices.HouseholdDevice;
import electricalDevices.additionalClasses.WorkingMode;

import java.util.ArrayList;
import java.util.List;

public class WashingMachine extends HouseholdDevice
{
    public enum WashingMode
    {
        Wash, Spin, WashAndSpin
    }

    public Timer WashingTimerInSeconds;
    List<String> clothesNames = new ArrayList<String>();
    WashingMode washingMode;
    public WashingMachine(float power, boolean isEnabled, WorkingMode workingMode, WashingMode washingMode, Timer timer)
    {
        super(power, isEnabled, workingMode);
        SetWashingMode(washingMode);
        this.WashingTimerInSeconds = timer;
    }

    @Override
    public void PrintInfo()
    {
        System.out.println(String.format("WashingMachine with %1$.2f power, %2$s workmode and %3$s washing mode!", GetPower(), GetWorkingMode().toString(), washingMode.toString()));
    }
    public void SetWashingMode(WashingMode washingMode)
    {
        this.washingMode = washingMode;
    }
    @Override
    final public void EnableDevice()
    {
        super.EnableDevice();
        new Thread(() ->
        {
            float currentTimeOfWorking = 0;
            while (currentTimeOfWorking <= WashingTimerInSeconds.GetTime() && isEnabled)
            {
                String timeToTheEnd = WashingTimerInSeconds.GetTime() - currentTimeOfWorking == 1 ? (WashingTimerInSeconds.GetTime() - currentTimeOfWorking) + " second"
                        : (WashingTimerInSeconds.GetTime() - currentTimeOfWorking) + " seconds";
                String text = clothesNames.size() > 1 ? "Washing one cloth!" : String.format("Washing %1$s clothes!", clothesNames.size());
                System.out.println(String.format("WashingMachine: %1$s WashingMode is %2$s on %3$s! ", text, washingMode, GetWorkingMode().toString()) + timeToTheEnd + " to the end!");
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
                System.out.println("WashingMachine: Washing is over!");
            isEnabled = false;
        }).start();
    }
}
