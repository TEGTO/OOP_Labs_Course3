package electricalDevices.types.kitchenDevices;
import electricalDevices.ElecDevice;
import electricalDevices.additionalClasses.IWorkingMode;
import electricalDevices.additionalClasses.WorkingMode;
import myLogger.MyLogger;

public class KitchenDevice extends ElecDevice implements IWorkingMode
{
    protected WorkingMode workingMode;
    public KitchenDevice(float power, boolean isEnabled, WorkingMode workingMode)
    {
        super(power, isEnabled);
        this.workingMode = workingMode;
    }
    @Override
    public void printInfo()
    {
        MyLogger.printInfoMessage(String.format("KitchenDevice with %1$f power and %2$s workmode!", getPower(), getWorkingMode().toString()));
    }
    @Override
    public WorkingMode getWorkingMode()
    {
        return workingMode;
    }
    public void setWorkingMode(WorkingMode workingMode)
    {
        this.workingMode = workingMode;
    }
}
