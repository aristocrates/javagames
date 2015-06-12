
/**
 * Write a description of class TimeKeeper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimeKeeper
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