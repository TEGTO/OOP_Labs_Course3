package electricalDevices.types.buildingDevices.types;
import electricalDevices.types.buildingDevices.BuildingDevice;

public class Drill extends BuildingDevice
{
    int spins;
    public Drill(float power, boolean isEnabled, int spins)
    {
        super(power, isEnabled);
        SetSpins(spins);
    }
    @Override
    public void PrintInfo()
    {
        String s = spins > 1 ? "spins!" : "spin!";
        System.out.println(String.format("Drill with %1$.2f power and %2$d ", GetPower(), spins) + s);
    }
    public void SetSpins(int spins)
    {
        this.spins = spins;
    }
    @Override
    public void EnableDevice()
    {
        super.EnableDevice();
        System.out.println(String.format("Drill: Spinning on %1$d spins!", spins));
    }
    @Override
    public void DisableDevice()
    {
        super.DisableDevice();
        System.out.println("Drill: Stop spinning!");
    }
}
