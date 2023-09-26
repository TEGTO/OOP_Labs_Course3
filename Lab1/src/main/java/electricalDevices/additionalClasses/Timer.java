package electricalDevices.additionalClasses;
public class Timer
{
    float time;
    public Timer(float time)
    {
        this.time = time;
    }
    public void addTime(float time)
    {
        this.time += time;
    }
    public void setTime(float time)
    {
        this.time = time;
    }
    public float getTime()
    {
        return time;
    }
}
