package electricalDevices.types.householdDevices.types;
import electricalDevices.types.householdDevices.HouseholdDevice;
import electricalDevices.additionalClasses.WorkingMode;
import myLogger.MyLogger;

public class Vacuum extends HouseholdDevice
{
    public Vacuum(float power, boolean isEnabled, WorkingMode workingMode)
    {
        super(power, isEnabled, workingMode);
    }
    @Override
    public void printInfo()
    {
        MyLogger.printInfoMessage(String.format("Vacuum with %1$.2f power and %2$s workmode!", getPower(), getWorkingMode().toString()));
    }
    @Override
    final public void enableDevice()
    {
        super.enableDevice();
        MyLogger.printInfoMessage(String.format("Vacuum: Suck up dust %1$s!", getWorkingMode().toString()));
    }
    @Override
    final public void disableDevice()
    {
        super.disableDevice();
        MyLogger.printInfoMessage("Vacuum: Stop sucking up dust!");
    }
}
