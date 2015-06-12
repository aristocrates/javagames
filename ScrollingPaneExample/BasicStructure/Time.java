package BasicStructure;
public class Time
{
    private static long timeZero;

    public static void setToTimeZero()
    {
        timeZero = System.currentTimeMillis();
    }
    
    public static int getTime()
    {
        return (int)(System.currentTimeMillis()-timeZero);
    }
}