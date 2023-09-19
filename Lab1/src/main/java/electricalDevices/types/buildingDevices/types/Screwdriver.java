package electricalDevices.types.buildingDevices.types;
public class Screwdriver extends Drill
{
    int batteryPercent; // how much energy left in percents
    public Screwdriver(float power, boolean isEnabled, int spins, int batteryPercent)
    {
        super(power, isEnabled, spins);
        SetBatteryPercent(batteryPercent);
    }
    @Override
    public void PrintInfo()
    {
        String s = spins > 1 ? "spins!" : "spin!";
        System.out.println(String.format("Screwdriver with %1$.2f power, %2$d%% battery left and %3$d ", GetPower(), batteryPercent, spins) + s);
    }
    public void SetBatteryPercent(int batteryPercent)
    {
        if (batteryPercent < 0)
            batteryPercent = 0;
        else if (batteryPercent > 100)
            batteryPercent = 100;
        this.batteryPercent = batteryPercent;
    }
    public int GetBatteryPercent()
    {
        return batteryPercent;
    }
    @Override
    public void EnableDevice()
    {
        //super.EnableDevice();
        isEnabled = true;
        new Thread(() ->
        {
            while (GetBatteryPercent() > 0 && isEnabled)
            {
                System.out.println(String.format("Screwdriver: Spinning on %1$d spins! Battery left: %2$d", spins, GetBatteryPercent()) + "%");
                try
                {
                    Thread.sleep(200);
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                SetBatteryPercent(GetBatteryPercent() - 1);
            }
            if (isEnabled)
                System.out.println(String.format("Screwdriver: Can't spin! Battery is over: %1$d", GetBatteryPercent()) + "%");
        }).start();
    }
    @Override
    public void DisableDevice()
    {
        super.DisableDevice();
        System.out.println(String.format("Screwdriver: Stop spinning! Battery left: %1$d", GetBatteryPercent()) + "%");
    }
}
