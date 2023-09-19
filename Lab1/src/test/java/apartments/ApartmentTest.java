package apartments;
import electricalDevices.IElecDevice;
import electricalDevices.additionalClasses.WorkingMode;
import electricalDevices.types.buildingDevices.types.Drill;
import electricalDevices.types.householdDevices.types.Vacuum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentTest
{
    @Test
    public void testDefaultInitialization()
    {
        Apartment apartment = new Apartment(1000);
        assertEquals(6, apartment.GetAllDevicesInApartment().size()); // Assuming there are 6 default devices
    }

    @Test
    public void testAddDevice()
    {
        Apartment apartment = new Apartment(1);
        int initialSize = apartment.GetAllDevicesInApartment().size();
        apartment.AddDevice(new Vacuum(45, false, WorkingMode.Easy));
        assertEquals(initialSize + 1, apartment.GetAllDevicesInApartment().size());
    }

    @Test
    public void testEnableAndDisableDevice()
    {
        Apartment apartment = new Apartment(1);
        Vacuum vacuum = new Vacuum(45, false, WorkingMode.Easy);
        apartment.AddDevice(vacuum);
        apartment.EnableDeviceInApartment(vacuum);
        assertTrue(vacuum.IsEnabled());

        apartment.DisableDeviceInApartment(vacuum);
        assertFalse(vacuum.IsEnabled());
    }

    @Test
    public void testFindByObject()
    {
        Apartment apartment = new Apartment(1);
        Drill drill = new Drill(200, false, 150);
        apartment.AddDevice(drill);
        Drill drillToSearch = new Drill(200, false, 150);
        IElecDevice foundDevice = apartment.FindByObject(drillToSearch);
        assertNotNull(foundDevice);
        assertTrue(Apartment.areObjectsEqual(drill, foundDevice));
    }
    @Test
    public void testGetPowerOfAll()
    {
        Apartment apartment = new Apartment(1,new ArrayList<IElecDevice>());
        apartment.AddDevice(new Vacuum(50, false, WorkingMode.Easy));
        apartment.AddDevice(new Drill(200, false, 150));

        float expectedPower = 50 + 200;
        assertEquals(expectedPower, apartment.GetPowerOfAll());
    }

    @Test
    public void testSortByPower()
    {
        Apartment apartment = new Apartment(1,new ArrayList<IElecDevice>());
        Drill lessPowerfulDrill = new Drill(100, false, 100);
        Drill morePowerfulDrill = new Drill(200, false, 150);

        apartment.AddDevice(morePowerfulDrill);
        apartment.AddDevice(lessPowerfulDrill);

        apartment.SortByPower();

        // Check if the devices are sorted correctly
        assertEquals(morePowerfulDrill, apartment.GetAllDevicesInApartment().get(0));
        assertEquals(lessPowerfulDrill, apartment.GetAllDevicesInApartment().get(1));
    }

    @Test
    public void testSaveAndLoadDevicesData()
    {
        ArrayList<IElecDevice> list = new ArrayList<IElecDevice>();
        list.add(new Drill(200, false, 150));
        Apartment apartment = new Apartment(1,list);
        Drill testDrill = new Drill(300, false, 300);
        apartment.AddDevice(testDrill);
        apartment.SaveDevicesData();
        apartment.GetAllDevicesInApartment().clear();
        apartment.InitializeDevicesData();
        apartment.SortByPower();
        assertTrue(Apartment.areObjectsEqual(testDrill, apartment.GetAllDevicesInApartment().get(0)));
    }
}