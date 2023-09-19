package electricalDevices.additionalClasses;
public class Timer
{
    float time;
    public Timer(float time)
    {
        this.time = time;
    }
    public void AddTime(float time)
    {
        this.time += time;
    }
    public void SetTime(float time)
    {
        this.time = time;
    }
    public float GetTime()
    {
        return time;
    }
}
