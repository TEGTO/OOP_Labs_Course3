package electricalDevices.types.kitchenDevices.types;
import electricalDevices.additionalClasses.WorkingMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FridgeTest
{
    private Fridge fridge;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        fridge = new Fridge(150.0f, true, WorkingMode.Medium); // Assuming WorkingMode is an enum with a value NORMAL
    }
    @Test
    public void testPrintInfo() {
        fridge.PrintInfo();
        String expected = "Fridge with 150,00 power, Medium workmode and 0 food inside!";
        assertEquals(expected, outContent.toString().replace("\n", "").replace("\r", ""));
    }
    @Test
    public void testAddFood() {
        fridge.AddFood("Apple");
        assertEquals(List.of("Apple"), fridge.foodInside);
    }

    @Test
    public void testRemoveFood() {
        fridge.AddFood("Apple");
        fridge.RemoveFood("Apple");
        assertTrue(fridge.foodInside.isEmpty());
    }

    @Test
    public void testEnableDevice() {
        fridge.EnableDevice();
        String expectedOutput = "Fridge: Cooling food! On Medium mode!"; // Assuming "Device is now enabled!" is from the super.EnableDevice() method
        assertEquals(expectedOutput, outContent.toString().replace("\n", "").replace("\r", ""));
    }

    @Test
    public void testDisableDevice() {
        fridge.DisableDevice();
        String expectedOutput = "Fridge: Stops cooling food!"; // Assuming "Device is now disabled!" is from the super.DisableDevice() method
        assertEquals(expectedOutput, outContent.toString().replace("\n", "").replace("\r", ""));
    }
}