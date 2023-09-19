package electricalDevices.types.buildingDevices.types;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DrillTest {
    private Drill drill;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        drill = new Drill(1500.0f, true, 500);
    }

    private String getOutput() {
        return outContent.toString().replace("\n", "").replace("\r", "");
    }

    @Test
    public void testPrintInfoMultipleSpins() {
        drill.PrintInfo();
        String expected = "Drill with 1500,00 power and 500 spins!";
        assertEquals(expected, getOutput());
    }

    @Test
    public void testPrintInfoSingleSpin() {
        drill.SetSpins(1);
        drill.PrintInfo();
        String expected = "Drill with 1500,00 power and 1 spin!";
        assertEquals(expected, getOutput());
    }

    @Test
    public void testSetSpins() {
        drill.SetSpins(1000);
        assertEquals(1000, drill.spins);
    }

    @Test
    public void testEnableDevice() {
        drill.EnableDevice();
        String expectedOutput = "Drill: Spinning on 500 spins!";
        assertEquals(expectedOutput, getOutput());
    }

    @Test
    public void testDisableDevice() {
        drill.DisableDevice();
        String expectedOutput = "Drill: Stop spinning!";
        assertEquals(expectedOutput, getOutput());
    }
}
