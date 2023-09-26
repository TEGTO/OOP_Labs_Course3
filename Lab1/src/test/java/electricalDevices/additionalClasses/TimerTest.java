package electricalDevices.additionalClasses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest
{
    private Timer timer;

    @BeforeEach
    public void setUp() {
        timer = new Timer(10.0f);
    }

    @Test
    public void testConstructor() {
        assertEquals(10.0f, timer.getTime());
    }

    @Test
    public void testAddTime() {
        timer.addTime(5.0f);
        assertEquals(15.0f, timer.getTime());
    }

    @Test
    public void testSetTime() {
        timer.setTime(20.0f);
        assertEquals(20.0f, timer.getTime());
    }

    @Test
    public void testGetTime() {
        assertEquals(10.0f, timer.getTime());
    }
}