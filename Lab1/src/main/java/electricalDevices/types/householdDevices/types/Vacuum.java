package electricalDevices.types.householdDevices.types;
import electricalDevices.types.householdDevices.HouseholdDevice;
import electricalDevices.additionalClasses.WorkingMode;

public class Vacuum extends HouseholdDevice
{
    public Vacuum(float power, boolean isEnabled, WorkingMode workingMode)
    {
        super(power, isEnabled, workingMode);
    }
    @Override
    public void PrintInfo()
    {
        System.out.println(String.format("Vacuum with %1$.2f power and %2$s workmode!", GetPower(), GetWorkingMode().toString()));
    }
    @Override
    final public void EnableDevice()
    {
        super.EnableDevice();
        System.out.println(String.format("Vacuum: Suck up dust %1$s!", GetWorkingMode().toString()));
    }
    @Override
    final public void DisableDevice()
    {
        super.DisableDevice();
        System.out.println("Vacuum: Stop sucking up dust!");
    }
}
