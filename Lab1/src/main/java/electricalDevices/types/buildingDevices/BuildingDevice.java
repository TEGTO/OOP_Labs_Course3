package electricalDevices.types.buildingDevices;
import electricalDevices.ElecDevice;

public class BuildingDevice extends ElecDevice
{
    public BuildingDevice(float power, boolean isEnabled)
    {
        super(power, isEnabled);
    }
    @Override
    public void PrintInfo()
    {
        System.out.println(String.format("Override with %1$f power!", GetPower()));
    }
}
