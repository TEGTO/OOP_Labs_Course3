package device;
import device.Device;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceComparatorTest {

    @Test
    public void testComparator() {
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

        Collections.sort(devices, new DeviceComparator());

        assertEquals(100.0f, devices.get(0).getPrice());
        assertEquals(200.0f, devices.get(1).getPrice());
        assertEquals(300.0f, devices.get(2).getPrice());
    }
}
