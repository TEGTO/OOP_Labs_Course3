package electricalDevices.types.kitchenDevices.types;

import electricalDevices.types.kitchenDevices.KitchenDevice;
import electricalDevices.additionalClasses.WorkingMode;

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
    public void PrintInfo()
    {
        System.out.println(String.format("Fridge with %1$.2f power, %2$s workmode and %3$d food inside!", GetPower(), GetWorkingMode().toString(),foodInside.size()));
    }
    public void AddFood(String foodName)
    {
        foodInside.add((foodName));
    }
    public void RemoveFood(String foodName)
    {
        foodInside.remove((foodName));
    }
    @Override
    final public void EnableDevice()
    {
        super.EnableDevice();
        System.out.println(String.format("Fridge: Cooling food! On %1$s mode!",GetWorkingMode().toString()));
    }
    @Override
    final public void DisableDevice()
    {
        super.DisableDevice();
        System.out.println("Fridge: Stops cooling food!");
    }
}
