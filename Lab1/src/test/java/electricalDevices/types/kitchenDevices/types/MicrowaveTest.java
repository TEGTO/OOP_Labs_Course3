package electricalDevices.types.kitchenDevices.types;
import electricalDevices.additionalClasses.Timer;
import electricalDevices.additionalClasses.WorkingMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MicrowaveTest
{
    private Microwave microwave;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final Timer timer = new Timer(10.0f);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        microwave = new Microwave(1000.0f, true, WorkingMode.Medium, timer, "Chicken");
    }

    @Test
    public void testPrintInfo() {
        microwave.printInfo();
        String expected = "Microwave with 1000,00 power, Medium workmode and Chicken food inside!";
        assertEquals(expected, outContent.toString().replace("\n", "").replace("\r", ""));
    }

    @Test
    public void testSetFood() {
        microwave.setFood("Pasta");
        microwave.printInfo();
        String expected = "Microwave with 1000,00 power, Medium workmode and Pasta food inside!";
        assertEquals(expected, outContent.toString().replace("\n", "").replace("\r", ""));
    }

    @Test
    public void testEnableDevice() throws InterruptedException {
        microwave.enableDevice();
        Thread.sleep(12000);  // Let it run for a bit more than the timer's time
        String expectedEndMessage = "Microwave: Beep-beep Chicken is heated!";
        assertTrue(outContent.toString().replace("\n", "").replace("\r", "").endsWith(expectedEndMessage));
    }

    @Test
    public void testDisableDevice() {
        microwave.disableDevice();
        String expected = "Microwave: Beep!"; // Assuming "Device is now disabled!" is from the super.DisableDevice() method
        assertEquals(expected,  outContent.toString().replace("\n", "").replace("\r", ""));
    }
}