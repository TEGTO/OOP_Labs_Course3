package electricalDevices;
public class ElecDevice implements IElecDevice
{
    protected float power;
    protected boolean isEnabled = false;
    public ElecDevice(float power, boolean isEnabled)
    {
        this.power = power;

    }
    @Override
    public void EnableDevice()
    {
        isEnabled = true;
    }
    @Override
    public void DisableDevice()
    {
        isEnabled = false;
    }
    @Override
    public void PrintInfo()
    {
        System.out.println("ElecDevice with " + GetPower() + "power!");
    }
    @Override
    public float GetPower()
    {
        return power;
    }
    @Override
    public boolean IsEnabled()
    {
        return isEnabled;
    }
}
