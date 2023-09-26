package apartments;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import electricalDevices.IElecDevice;
import electricalDevices.additionalClasses.IElecDeviceTypeAdapter;
import electricalDevices.additionalClasses.Timer;
import electricalDevices.additionalClasses.WorkingMode;
import electricalDevices.types.buildingDevices.types.Drill;
import electricalDevices.types.buildingDevices.types.Screwdriver;
import electricalDevices.types.householdDevices.types.Vacuum;
import electricalDevices.types.householdDevices.types.WashingMachine;
import electricalDevices.types.kitchenDevices.types.Fridge;
import electricalDevices.types.kitchenDevices.types.Microwave;
import myLogger.MyLogger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

public class Apartment
{
    public static final String FILE_PATH = "elecDevicesData";
    protected static int amountOfInstances = 0;
    protected int id = 0;
    List<IElecDevice> devicesInApartment = new ArrayList<IElecDevice>();
    public Apartment()
    {
        new Apartment(amountOfInstances + 1);
    }
    public Apartment(int id)
    {
        amountOfInstances++;
        this.id = id;
        initializeDevicesData();
    }
    public Apartment(int id, List<IElecDevice> devicesInApartment)
    {
        amountOfInstances++;
        this.id = id;
        this.devicesInApartment = devicesInApartment;
    }
    public void enableAllDevices()
    {
        for (IElecDevice device : devicesInApartment)
        {
            enableDeviceInApartment(device);
        }
    }
    public void disableAllDevices()
    {
        for (IElecDevice device : devicesInApartment)
        {
            disableDeviceInApartment(device);
        }
    }
    public void enableSomeDevices()
    {
        for (IElecDevice device : devicesInApartment)
        {
            if ((new Random().nextInt(100) > 50))
                enableDeviceInApartment(device);
        }
    }
    public IElecDevice findByObject(IElecDevice iElecDeviceWhichCompare)
    {
        for (IElecDevice device : devicesInApartment)
        {
            if (areObjectsEqual(device, iElecDeviceWhichCompare))
                return device;
        }
        return null;
    }

    public static boolean areObjectsEqual(Object obj1, Object obj2)
    {
        if (obj1 == null || obj2 == null)
            return false;

        // Compare classes
        if (!obj1.getClass().equals(obj2.getClass()))
            return false;

        // Use reflection to compare fields
        for (Field field : obj1.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            try
            {
                Object fieldObj1 = field.get(obj1);
                Object fieldObj2 = field.get(obj2);

                if (fieldObj1 == null || fieldObj2 == null)
                {
                    if (fieldObj1 != fieldObj2)
                    {
                        return false;
                    }
                }
                else if (!fieldObj1.getClass().isPrimitive() && !(fieldObj1 instanceof Float)
                        && !(fieldObj1 instanceof Double)
                        && !(fieldObj1 instanceof Integer)
                        && !(fieldObj1 instanceof Long)
                        && !(fieldObj1 instanceof Character)
                        && !(fieldObj1 instanceof Boolean)
                        && !(fieldObj1 instanceof Byte)
                        && !(fieldObj1 instanceof Short)
                        && !(fieldObj1 instanceof String))
                {
                    // Recursively check nested object fields
                    if (!areObjectsEqual(fieldObj1, fieldObj2))
                    {
                        return false;
                    }
                }
                else if (!Objects.equals(fieldObj1, fieldObj2))
                {
                    return false;
                }
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
    public void enableDeviceInApartment(IElecDevice device)
    {
        if (devicesInApartment.contains(device))
            device.enableDevice();
        else
            MyLogger.printInfoMessage("There is no such device in apartment!");
    }
    public void disableDeviceInApartment(IElecDevice device)
    {
        if (devicesInApartment.contains(device))
            device.disableDevice();
        else
            MyLogger.printInfoMessage("There is no such device in apartment!");
    }
    public int getPowerOfAll()
    {
        int powerOfAll = 0;

        for (IElecDevice device : devicesInApartment)
        {
            powerOfAll += device.getPower();
        }
        return powerOfAll;
    }
    public void sortByPower()
    {
        devicesInApartment.sort(Comparator.comparing(IElecDevice::getPower).reversed());
    }
    public void printAllDevices()
    {
        for (IElecDevice device : devicesInApartment)
        {
            device.printInfo();
        }
    }
    public void addDevice(IElecDevice device)
    {
        devicesInApartment.add(device);
    }
    public List<IElecDevice> getAllDevicesInApartment()
    {
        return devicesInApartment;
    }
    public void initializeDevicesData()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter(IElecDevice.class, new IElecDeviceTypeAdapter());
        Gson customGson = gsonBuilder.create();
        try (FileReader reader = new FileReader(FILE_PATH + id + ".json"))
        {
            Type deviceListType = new TypeToken<ArrayList<IElecDevice>>()
            {
            }.getType();
            devicesInApartment = customGson.fromJson(reader, deviceListType);
        }
        catch (IOException e)
        {
            if (devicesInApartment == null || devicesInApartment.isEmpty())
            {
                Fridge f = new Fridge(10, true, WorkingMode.Medium);
                f.addFood("Pizza");
                f.addFood("Salad");
                f.addFood("Tomato");
                devicesInApartment.add(f);
                devicesInApartment.add(new Microwave(200, true, WorkingMode.Medium, new Timer(10), "Pizza"));
                devicesInApartment.add(new Vacuum(55, true, WorkingMode.Medium));
                devicesInApartment.add(new WashingMachine(350, true, WorkingMode.Medium, WashingMachine.WashingMode.WashAndSpin, new Timer(10)));
                devicesInApartment.add(new Drill(15, true, 100));
                devicesInApartment.add(new Screwdriver(110, true, 100, 50));
            }
        }
    }
    public void saveDevicesData()
    {
        if (devicesInApartment != null)
        {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeHierarchyAdapter(IElecDevice.class, new IElecDeviceTypeAdapter());
            Gson customGson = gsonBuilder.create();
            String jsonString = customGson.toJson(devicesInApartment);
            try (FileWriter file = new FileWriter(FILE_PATH + id + ".json"))
            {
                file.write(jsonString);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

