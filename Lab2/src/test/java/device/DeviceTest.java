package device;
import device.types.DeviceType;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceTest {

    @Test
    public void testSettersAndGetters() {
        Device device = new Device();

        device.setId(1);
        device.setName("TestDevice");
        device.setOrigin("TestOrigin");
        device.setPrice(100.0f);
        device.setCritical(true);
        DeviceType type = new DeviceType();
        device.setType(type);

        assertEquals(1, device.getId());
        assertEquals("TestDevice", device.getName());
        assertEquals("TestOrigin", device.getOrigin());
        assertEquals(100.0f, device.getPrice());
        assertEquals(true, device.getCritical());
        assertEquals(type, device.getType());
    }

    @Test
    public void testCompareTo() {
        Device device1 = new Device();
        device1.setPrice(100.0f);

        Device device2 = new Device();
        device2.setPrice(200.0f);

        assertEquals(-1, device1.compareTo(device2));
        assertEquals(1, device2.compareTo(device1));
        assertEquals(0, device1.compareTo(device1));
    }

    @Test
    public void testSorting() {
        Device device1 = new Device();
        device1.setPrice(300.0f);

        Device device2 = new Device();
        device2.setPrice(200.0f);

        Device device3 = new Device();
        device3.setPrice(100.0f);

        List<Device> devices = new ArrayList<>();
        devices.add(device1);
        devices.add(device2);
        devices.add(device3);

        Collections.sort(devices);

        assertEquals(100.0f, devices.get(0).getPrice());
        assertEquals(200.0f, devices.get(1).getPrice());
        assertEquals(300.0f, devices.get(2).getPrice());
    }
}
