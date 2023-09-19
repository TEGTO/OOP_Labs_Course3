package electricalDevices.types.householdDevices;
import electricalDevices.ElecDevice;
import electricalDevices.additionalClasses.IWorkingMode;
import electricalDevices.additionalClasses.WorkingMode;

public class HouseholdDevice extends ElecDevice implements IWorkingMode
{
    protected WorkingMode workingMode;
    public HouseholdDevice(float power, boolean isEnabled, WorkingMode workingMode)
    {
        super(power, isEnabled);
        SetWorkingMode(workingMode);
    }
    @Override
    public void PrintInfo()
    {
        System.out.println(String.format("HouseholdDevice with %1$f power and %2$s workmode!", GetPower(), GetWorkingMode().toString()));
    }
    @Override
    public WorkingMode GetWorkingMode()
    {
        return workingMode;
    }
    @Override
    public void SetWorkingMode(WorkingMode workingMode)
    {
        this.workingMode = workingMode;
    }
}
