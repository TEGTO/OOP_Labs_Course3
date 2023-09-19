package electricalDevices.types.kitchenDevices.types;
import electricalDevices.additionalClasses.Timer;
import electricalDevices.types.kitchenDevices.KitchenDevice;
import electricalDevices.additionalClasses.WorkingMode;

public class Microwave extends KitchenDevice
{
    Timer MicrowavingTimerInSeconds;
    String foodName;
    public Microwave(float power, boolean isEnabled, WorkingMode workingMode, Timer timer, String foodName)
    {
        super(power, isEnabled, workingMode);
        this.MicrowavingTimerInSeconds = timer;
        SetFood(foodName);
    }
    @Override
    public void PrintInfo()
    {
        System.out.println(String.format("Microwave with %1$.2f power, %2$s workmode and %3$s food inside!", GetPower(), GetWorkingMode().toString(),foodName));
    }
    public void SetFood(String foodName)
    {
        this.foodName = foodName;
    }
    @Override
    final public void EnableDevice()
    {
        super.EnableDevice();
        new Thread(() ->
        {
            float currentTimeOfWorking = 0;
            while (currentTimeOfWorking <= MicrowavingTimerInSeconds.GetTime() && isEnabled)
            {
                String timeToTheEnd = MicrowavingTimerInSeconds.GetTime() - currentTimeOfWorking == 1 ? (MicrowavingTimerInSeconds.GetTime() - currentTimeOfWorking) + " second"
                        : (MicrowavingTimerInSeconds.GetTime() - currentTimeOfWorking) + " seconds";
                System.out.println(String.format("Microwave: Heating up the %1$s! On %2$s mode! ", foodName, GetWorkingMode().toString()) + timeToTheEnd + " to the end!");
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
                System.out.println(String.format("Microwave: Beep-beep %1$s is heated!", foodName));
        }).start();
        isEnabled = false;
    }
    @Override
    final public void DisableDevice()
    {
        super.DisableDevice();
        System.out.println("Microwave: Beep!");
    }
}
