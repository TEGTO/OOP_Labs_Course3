package electricalDevices;
import myLogger.MyLogger;

public class ElecDevice implements IElecDevice
{
    protected float power;
    protected boolean isEnabled = false;
    public ElecDevice(float power, boolean isEnabled)
    {
        this.power = power;
        this.isEnabled = isEnabled;
    }
    @Override
    public void enableDevice()
    {
        isEnabled = true;
    }
    @Override
    public void disableDevice()
    {
        isEnabled = false;
    }
    @Override
    public void printInfo()
    {
        MyLogger.printInfoMessage("ElecDevice with " + getPower() + "power!");
    }
    @Override
    public float getPower()
    {
        return power;
    }
    @Override
    public boolean isEnabled()
    {
        return isEnabled;
    }
}
