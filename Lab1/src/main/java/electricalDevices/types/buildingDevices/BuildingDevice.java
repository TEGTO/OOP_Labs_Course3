package electricalDevices.types.buildingDevices;
import electricalDevices.ElecDevice;
import myLogger.MyLogger;

public class BuildingDevice extends ElecDevice
{
    public BuildingDevice(float power, boolean isEnabled)
    {
        super(power, isEnabled);
    }
    @Override
    public void printInfo()
    {
        MyLogger.printInfoMessage(String.format("Override with %1$f power!", getPower()));
    }
}
