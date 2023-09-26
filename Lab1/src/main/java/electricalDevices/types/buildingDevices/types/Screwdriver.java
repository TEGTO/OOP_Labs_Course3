package electricalDevices.types.buildingDevices.types;
import myLogger.MyLogger;

public class Screwdriver extends Drill
{
    int batteryPercent; // how much energy left in percents
    public Screwdriver(float power, boolean isEnabled, int spins, int batteryPercent)
    {
        super(power, isEnabled, spins);
        if (batteryPercent < 0)
            batteryPercent = 0;
        else if (batteryPercent > 100)
            batteryPercent = 100;
        this.batteryPercent = batteryPercent;
    }
    @Override
    public void printInfo()
    {
        String s = spins > 1 ? "spins!" : "spin!";
        MyLogger.printInfoMessage(String.format("Screwdriver with %1$.2f power, %2$d%% battery left and %3$d ", getPower(), batteryPercent, spins) + s);
    }
    public void setBatteryPercent(int batteryPercent)
    {
        if (batteryPercent < 0)
            batteryPercent = 0;
        else if (batteryPercent > 100)
            batteryPercent = 100;
        this.batteryPercent = batteryPercent;
    }
    public int getBatteryPercent()
    {
        return batteryPercent;
    }
    @Override
    public void enableDevice()
    {
        //super.EnableDevice();
        isEnabled = true;
        new Thread(() ->
        {
            while (getBatteryPercent() > 0 && isEnabled)
            {
                MyLogger.printInfoMessage(String.format("Screwdriver: Spinning on %1$d spins! Battery left: %2$d", spins, getBatteryPercent()) + "%");
                try
                {
                    Thread.sleep(200);
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                setBatteryPercent(getBatteryPercent() - 1);
            }
            if (isEnabled)
                MyLogger.printInfoMessage(String.format("Screwdriver: Can't spin! Battery is over: %1$d", getBatteryPercent()) + "%");
        }).start();
    }
    @Override
    public void disableDevice()
    {
        super.disableDevice();
        MyLogger.printInfoMessage(String.format("Screwdriver: Stop spinning! Battery left: %1$d", getBatteryPercent()) + "%");
    }
}
