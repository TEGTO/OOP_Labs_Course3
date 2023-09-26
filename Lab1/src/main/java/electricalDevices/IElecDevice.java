package electricalDevices;
public interface IElecDevice
{
    public void enableDevice();
    public  void disableDevice();
    public  float getPower();
    public  void printInfo();
    public  boolean isEnabled();
}
