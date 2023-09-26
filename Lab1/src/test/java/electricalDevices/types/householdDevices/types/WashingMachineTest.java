package electricalDevices.types.householdDevices.types;
import electricalDevices.additionalClasses.Timer;
import electricalDevices.additionalClasses.WorkingMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class WashingMachineTest
{
    private WashingMachine washingMachine;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final Timer timer = new Timer(5.0f);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        washingMachine = new WashingMachine(2000.0f, true, WorkingMode.Medium, WashingMachine.WashingMode.WashAndSpin, timer);
        washingMachine.clothesNames.add("Shirt");
        washingMachine.clothesNames.add("Pants");
    }

    private String getOutput() {
        return outContent.toString().replace("\n", "").replace("\r", "");
    }

    @Test
    public void testPrintInfo() {
        washingMachine.printInfo();
        String expected = "WashingMachine with 2000,00 power, Medium workmode and WashAndSpin washing mode!";
        assertEquals(expected, getOutput());
    }

    @Test
    public void testSetWashingMode() {
        washingMachine.setWashingMode(WashingMachine.WashingMode.Spin);
        washingMachine.printInfo();
        String expected = "WashingMachine with 2000,00 power, Medium workmode and Spin washing mode!";
        assertEquals(expected, getOutput());
    }

    @Test
    public void testEnableDevice() throws InterruptedException {
        washingMachine.enableDevice();
        Thread.sleep(8000);  // Let it run for a bit more than the timer's time
        String expectedEndMessage = "WashingMachine: Washing is over!";
        assertTrue(getOutput().endsWith(expectedEndMessage));
    }
}