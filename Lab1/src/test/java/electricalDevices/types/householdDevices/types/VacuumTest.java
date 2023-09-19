package electricalDevices.types.householdDevices.types;
import electricalDevices.additionalClasses.WorkingMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class VacuumTest
{
    private Vacuum vacuum;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        vacuum = new Vacuum(1500.0f, true, WorkingMode.Medium); // Assuming WorkingMode is an enum with a value NORMAL
    }

    @Test
    public void testPrintInfo() {
        vacuum.PrintInfo();
        String expected = "Vacuum with 1500,00 power and Medium workmode!";
        assertEquals(expected, outContent.toString().replace("\n", "").replace("\r", ""));
    }

    @Test
    public void testEnableDevice() {
        vacuum.EnableDevice();
        String expectedOutput = "Vacuum: Suck up dust Medium!"; // Assuming "Device is now enabled!" is from the super.EnableDevice() method
        assertEquals(expectedOutput, outContent.toString().replace("\n", "").replace("\r", ""));
    }

    @Test
    public void testDisableDevice() {
        vacuum.DisableDevice();
        String expectedOutput = "Vacuum: Stop sucking up dust!"; // Assuming "Device is now disabled!" is from the super.DisableDevice() method
        assertEquals(expectedOutput, outContent.toString().replace("\n", "").replace("\r", ""));
    }
}