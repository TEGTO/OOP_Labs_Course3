package electricalDevices.types.kitchenDevices.types;
import electricalDevices.additionalClasses.Timer;
import electricalDevices.types.kitchenDevices.KitchenDevice;
import electricalDevices.additionalClasses.WorkingMode;
import myLogger.MyLogger;

public class Microwave extends KitchenDevice
{
    Timer microwavingTimerInSeconds;
    String foodName;
    public Microwave(float power, boolean isEnabled, WorkingMode workingMode, Timer timer, String foodName)
    {
        super(power, isEnabled, workingMode);
        this.microwavingTimerInSeconds = timer;
        this.foodName = foodName;
    }
    @Override
    public void printInfo()
    {
        MyLogger.printInfoMessage(String.format("Microwave with %1$.2f power, %2$s workmode and %3$s food inside!", getPower(), getWorkingMode().toString(),foodName));
    }
    public void setFood(String foodName)
    {
        this.foodName = foodName;
    }
    @Override
    final public void enableDevice()
    {
        super.enableDevice();
        new Thread(() ->
        {
            float currentTimeOfWorking = 0;
            while (currentTimeOfWorking <= microwavingTimerInSeconds.getTime() && isEnabled)
            {
                String timeToTheEnd = microwavingTimerInSeconds.getTime() - currentTimeOfWorking == 1 ? (microwavingTimerInSeconds.getTime() - currentTimeOfWorking) + " second"
                        : (microwavingTimerInSeconds.getTime() - currentTimeOfWorking) + " seconds";
                MyLogger.printInfoMessage(String.format("Microwave: Heating up the %1$s! On %2$s mode! ", foodName, getWorkingMode().toString()) + timeToTheEnd + " to the end!");
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
                MyLogger.printInfoMessage(String.format("Microwave: Beep-beep %1$s is heated!", foodName));
        }).start();
        isEnabled = false;
    }
    @Override
    final public void disableDevice()
    {
        super.disableDevice();
        MyLogger.printInfoMessage("Microwave: Beep!");
    }
}
