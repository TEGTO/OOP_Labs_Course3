package electricalDevices.types.kitchenDevices;
import electricalDevices.ElecDevice;
import electricalDevices.additionalClasses.IWorkingMode;
import electricalDevices.additionalClasses.WorkingMode;

public class KitchenDevice extends ElecDevice implements IWorkingMode
{
    protected WorkingMode workingMode;
    public KitchenDevice(float power, boolean isEnabled, WorkingMode workingMode)
    {
        super(power, isEnabled);
        SetWorkingMode(workingMode);
    }
    @Override
    public void PrintInfo()
    {
        System.out.println(String.format("KitchenDevice with %1$f power and %2$s workmode!", GetPower(), GetWorkingMode().toString()));
    }
    @Override
    public WorkingMode GetWorkingMode()
    {
        return workingMode;
    }
    public void SetWorkingMode(WorkingMode workingMode)
    {
        this.workingMode = workingMode;
    }
}
