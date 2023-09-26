package electricalDevices.types.householdDevices;
import electricalDevices.ElecDevice;
import electricalDevices.additionalClasses.IWorkingMode;
import electricalDevices.additionalClasses.WorkingMode;
import myLogger.MyLogger;

public class HouseholdDevice extends ElecDevice implements IWorkingMode
{
    protected WorkingMode workingMode;
    public HouseholdDevice(float power, boolean isEnabled, WorkingMode workingMode)
    {
        super(power, isEnabled);
        this.workingMode = workingMode;
    }
    @Override
    public void printInfo()
    {
        MyLogger.printInfoMessage(String.format("HouseholdDevice with %1$f power and %2$s workmode!", getPower(), getWorkingMode().toString()));
    }
    @Override
    public WorkingMode getWorkingMode()
    {
        return workingMode;
    }
    @Override
    public void setWorkingMode(WorkingMode workingMode)
    {
        this.workingMode = workingMode;
    }
}
