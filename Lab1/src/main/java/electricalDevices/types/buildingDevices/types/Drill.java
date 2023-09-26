package electricalDevices.types.buildingDevices.types;
import electricalDevices.types.buildingDevices.BuildingDevice;
import myLogger.MyLogger;

public class Drill extends BuildingDevice
{
    int spins;
    public Drill(float power, boolean isEnabled, int spins)
    {
        super(power, isEnabled);
        this.spins = spins;
    }
    @Override
    public void printInfo()
    {
        String s = spins > 1 ? "spins!" : "spin!";
        MyLogger.printInfoMessage(String.format("Drill with %1$.2f power and %2$d ", getPower(), spins) + s);
    }
    public void setSpins(int spins)
    {
        this.spins = spins;
    }
    @Override
    public void enableDevice()
    {
        super.enableDevice();
        MyLogger.printInfoMessage(String.format("Drill: Spinning on %1$d spins!", spins));
    }
    @Override
    public void disableDevice()
    {
        super.disableDevice();
        MyLogger.printInfoMessage("Drill: Stop spinning!");
    }
}
