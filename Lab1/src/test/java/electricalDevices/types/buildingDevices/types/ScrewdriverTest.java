package electricalDevices.types.buildingDevices.types;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ScrewdriverTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Screwdriver screwdriver;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        screwdriver = new Screwdriver(100.0f, true, 500, 100);
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }
    @Test
    public void testBatteryPercentageSetter() {
        screwdriver.SetBatteryPercent(120); // Over 100
        assertEquals(100, screwdriver.GetBatteryPercent());

        screwdriver.SetBatteryPercent(-10); // Below 0
        assertEquals(0, screwdriver.GetBatteryPercent());

        screwdriver.SetBatteryPercent(50); // Within range
        assertEquals(50, screwdriver.GetBatteryPercent());
    }
    @Test
    public void testPrintInfoMultipleSpins() {
        screwdriver.PrintInfo();
        String expected = "Screwdriver with 100,00 power, 100% battery left and 500 spins!";
        assertEquals(expected, outContent.toString().replace("\n", "").replace("\r", ""));
    }
    @Test
    public void testPrintInfoSingleSpin() {
        Screwdriver singleSpinScrewdriver = new Screwdriver(100.0f, true, 1, 100);
        singleSpinScrewdriver.PrintInfo();
        String expected = "Screwdriver with 100,00 power, 100% battery left and 1 spin!";
        assertEquals(expected, outContent.toString().replace("\n", "").replace("\r", ""));
    }
    @Test
    public void testEnableDeviceBatteryDrain() throws InterruptedException {
        screwdriver.EnableDevice();
        Thread.sleep(500);  // Let's wait for 2 cycles (2 * 200ms = 400ms) for simplicity
        assertTrue(screwdriver.GetBatteryPercent() < 100); // Assert battery has drained
    }
    @Test
    public void testDisableDevice() throws InterruptedException {
        screwdriver.EnableDevice();
        Thread.sleep(500);  // Let it run for a bit
        screwdriver.DisableDevice();
        String output = outContent.toString();
        assertTrue(output.contains("Stop spinning! Battery left:")); // Assert the message
        assertFalse(screwdriver.IsEnabled()); // Assert device is disabled
    }
}