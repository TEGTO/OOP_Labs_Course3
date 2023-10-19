package parsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import device.Device;
import java.util.List;

public class SaxParserTest
{

    private SaxParser saxParser;

    @BeforeEach
    public void setUp()
    {
        saxParser = new SaxParser<Device>();
    }

    @Test
    public void testParseSAX() throws Exception
    {
        List<Device> devices = saxParser.parseSAX(Pathes.XML_PATH, new DeviceHandler());

        // Assert the number of devices parsed
        assertEquals(4, devices.size());

        // Test the details of the first device
        Device device1 = devices.get(0);
        assertEquals(47, device1.getId());
        assertEquals("EliteDragon", device1.getName());
        assertEquals("USA", device1.getOrigin());
        assertEquals(1000, device1.getPrice(), 0.01);
        assertFalse(device1.getCritical());
        assertFalse(device1.getType().getPeripheral());
        assertEquals(6, device1.getType().getEnergyConsumption());
        assertTrue(device1.getType().getHasCooler());
        assertEquals("PCIe 4.0", device1.getType().getPort());
        assertEquals("Graphics", device1.getType().getGroup());

        // Test the details of the second device
        Device device2 = devices.get(1);
        assertEquals(29, device2.getId());
        assertEquals("QuantumForce", device2.getName());
        assertEquals("Japan", device2.getOrigin());
        assertEquals(10000, device2.getPrice(), 0.01);
        assertTrue(device2.getCritical());
        assertFalse(device2.getType().getPeripheral());
        assertEquals(12, device2.getType().getEnergyConsumption());
        assertTrue(device2.getType().getHasCooler());
        assertEquals("Socket AM5", device2.getType().getPort());
        assertEquals("Processor", device2.getType().getGroup());

        // Test the details of the third device
        Device device3 = devices.get(2);
        assertEquals(83, device3.getId());
        assertEquals("NeptuneDrive", device3.getName());
        assertEquals("South Korea", device3.getOrigin());
        assertEquals(4343, device3.getPrice(), 0.01);
        assertFalse(device3.getCritical());
        assertFalse(device3.getType().getPeripheral());
        assertEquals(41, device3.getType().getEnergyConsumption());
        assertFalse(device3.getType().getHasCooler());
        assertEquals("SATA 3.0", device3.getType().getPort());
        assertEquals("Storage", device3.getType().getGroup());

        // Test the details of the fourth device
        Device device4 = devices.get(3);
        assertEquals(55, device4.getId());
        assertEquals("RapidFlow", device4.getName());
        assertEquals("Taiwan", device4.getOrigin());
        assertEquals(112121, device4.getPrice(), 0.01);
        assertFalse(device4.getCritical());
        assertFalse(device4.getType().getPeripheral());
        assertEquals(25, device4.getType().getEnergyConsumption());
        assertFalse(device4.getType().getHasCooler());
        assertEquals("DDR5", device4.getType().getPort());
        assertEquals("Memory", device4.getType().getGroup());
    }
}