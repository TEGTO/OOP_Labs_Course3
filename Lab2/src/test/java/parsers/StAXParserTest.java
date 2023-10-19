package parsers;
import static org.junit.jupiter.api.Assertions.*;

import device.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.List;

public class StAXParserTest {

    private StAXParser staxParser;

    @BeforeEach
    public void setUp() {
        staxParser = new StAXParser<Device>();
    }

    @Test
    public void testParseStAX() throws Exception {
        FileInputStream fis = new FileInputStream(Pathes.XML_PATH);
        List<Device> devices = staxParser.parseStAX(fis, new DeviceHandler());

        // Check if the devices list is not null
        assertNotNull(devices);

        // Assert the number of devices parsed
        assertEquals(4, devices.size());

        // Test the details of the first device
        Device device1 = devices.get(0);
        assertEquals(47, device1.getId());
        assertEquals("EliteDragon", device1.getName());
        assertEquals("USA", device1.getOrigin());
        assertEquals(1000, device1.getPrice());
        assertFalse(device1.getCritical());
        assertNotNull(device1.getType());
        assertEquals("PCIe 4.0", device1.getType().getPort());
        assertEquals("Graphics", device1.getType().getGroup());
        assertFalse(device1.getType().getPeripheral());
        assertEquals(6, device1.getType().getEnergyConsumption());
        assertTrue(device1.getType().getHasCooler());

        // Test the details of the second device
        Device device2 = devices.get(1);
        assertEquals(29, device2.getId());
        assertEquals("QuantumForce", device2.getName());
        assertEquals("Japan", device2.getOrigin());
        assertEquals(10000, device2.getPrice());
        assertTrue(device2.getCritical());
        assertNotNull(device2.getType());
        assertEquals("Socket AM5", device2.getType().getPort());
        assertEquals("Processor", device2.getType().getGroup());
        assertFalse(device2.getType().getPeripheral());
        assertEquals(12, device2.getType().getEnergyConsumption());
        assertTrue(device2.getType().getHasCooler());

        // Test the details of the third device
        Device device3 = devices.get(2);
        assertEquals(83, device3.getId());
        assertEquals("NeptuneDrive", device3.getName());
        assertEquals("South Korea", device3.getOrigin());
        assertEquals(4343, device3.getPrice());
        assertFalse(device3.getCritical());
        assertNotNull(device3.getType());
        assertEquals("SATA 3.0", device3.getType().getPort());
        assertEquals("Storage", device3.getType().getGroup());
        assertFalse(device3.getType().getPeripheral());
        assertEquals(41, device3.getType().getEnergyConsumption());
        assertFalse(device3.getType().getHasCooler());

        // Test the details of the fourth device
        Device device4 = devices.get(3);
        assertEquals(55, device4.getId());
        assertEquals("RapidFlow", device4.getName());
        assertEquals("Taiwan", device4.getOrigin());
        assertEquals(112121, device4.getPrice());
        assertFalse(device4.getCritical());
        assertNotNull(device4.getType());
        assertEquals("DDR5", device4.getType().getPort());
        assertEquals("Memory", device4.getType().getGroup());
        assertFalse(device4.getType().getPeripheral());
        assertEquals(25, device4.getType().getEnergyConsumption());
        assertFalse(device4.getType().getHasCooler());

        fis.close();  // Remember to close the FileInputStream
    }
}
