package electricalDevices.types.kitchenDevices.types;

import electricalDevices.types.kitchenDevices.KitchenDevice;
import electricalDevices.additionalClasses.WorkingMode;
import myLogger.MyLogger;

import java.util.ArrayList;
import java.util.List;

public class Fridge extends KitchenDevice
{
    List<String> foodInside = new ArrayList<>();
    public Fridge(float power, boolean isEnabled, WorkingMode workingMode)
    {
        super(power, isEnabled, workingMode);
    }
    @Override
    public void printInfo()
    {
        MyLogger.printInfoMessage(String.format("Fridge with %1$.2f power, %2$s workmode and %3$d food inside!", getPower(), getWorkingMode().toString(),foodInside.size()));
    }
    public void addFood(String foodName)
    {
        foodInside.add((foodName));
    }
    public void removeFood(String foodName)
    {
        foodInside.remove((foodName));
    }
    @Override
    final public void enableDevice()
    {
        super.enableDevice();
        MyLogger.printInfoMessage(String.format("Fridge: Cooling food! On %1$s mode!", getWorkingMode().toString()));
    }
    @Override
    final public void disableDevice()
    {
        super.disableDevice();
        MyLogger.printInfoMessage("Fridge: Stops cooling food!");
    }
}
