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
        assertEquals(6, apartment.getAllDevicesInApartment().size()); // Assuming there are 6 default devices
    }

    @Test
    public void testAddDevice()
    {
        Apartment apartment = new Apartment(1);
        int initialSize = apartment.getAllDevicesInApartment().size();
        apartment.addDevice(new Vacuum(45, false, WorkingMode.Easy));
        assertEquals(initialSize + 1, apartment.getAllDevicesInApartment().size());
    }

    @Test
    public void testEnableAndDisableDevice()
    {
        Apartment apartment = new Apartment(1);
        Vacuum vacuum = new Vacuum(45, false, WorkingMode.Easy);
        apartment.addDevice(vacuum);
        apartment.enableDeviceInApartment(vacuum);
        assertTrue(vacuum.isEnabled());

        apartment.disableDeviceInApartment(vacuum);
        assertFalse(vacuum.isEnabled());
    }

    @Test
    public void testFindByObject()
    {
        Apartment apartment = new Apartment(1);
        Drill drill = new Drill(200, false, 150);
        apartment.addDevice(drill);
        Drill drillToSearch = new Drill(200, false, 150);
        IElecDevice foundDevice = apartment.findByObject(drillToSearch);
        assertNotNull(foundDevice);
        assertTrue(Apartment.areObjectsEqual(drill, foundDevice));
    }
    @Test
    public void testGetPowerOfAll()
    {
        Apartment apartment = new Apartment(1,new ArrayList<IElecDevice>());
        apartment.addDevice(new Vacuum(50, false, WorkingMode.Easy));
        apartment.addDevice(new Drill(200, false, 150));

        float expectedPower = 50 + 200;
        assertEquals(expectedPower, apartment.getPowerOfAll());
    }

    @Test
    public void testSortByPower()
    {
        Apartment apartment = new Apartment(1,new ArrayList<IElecDevice>());
        Drill lessPowerfulDrill = new Drill(100, false, 100);
        Drill morePowerfulDrill = new Drill(200, false, 150);

        apartment.addDevice(morePowerfulDrill);
        apartment.addDevice(lessPowerfulDrill);

        apartment.sortByPower();

        // Check if the devices are sorted correctly
        assertEquals(morePowerfulDrill, apartment.getAllDevicesInApartment().get(0));
        assertEquals(lessPowerfulDrill, apartment.getAllDevicesInApartment().get(1));
    }

    @Test
    public void testSaveAndLoadDevicesData()
    {
        ArrayList<IElecDevice> list = new ArrayList<IElecDevice>();
        list.add(new Drill(200, false, 150));
        Apartment apartment = new Apartment(1,list);
        Drill testDrill = new Drill(300, false, 300);
        apartment.addDevice(testDrill);
        apartment.saveDevicesData();
        apartment.getAllDevicesInApartment().clear();
        apartment.initializeDevicesData();
        apartment.sortByPower();
        assertTrue(Apartment.areObjectsEqual(testDrill, apartment.getAllDevicesInApartment().get(0)));
    }
}