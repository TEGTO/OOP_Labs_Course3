package electricalDevices;
public interface IElecDevice
{
    public void EnableDevice();
    public  void DisableDevice();
    public  float GetPower();
    public  void PrintInfo();
    public  boolean IsEnabled();
}
