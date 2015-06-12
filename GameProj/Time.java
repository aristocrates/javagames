/**
 * Contains methods to access the game time during levels and set its time
 * to zero like a stop watch
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class Time
{
    private static long timeZero;
    private static int timeStore;
    private static boolean paused;
    
    static {
        setToTimeZero();
        paused = false;
    }

    /**
     * Sets the time to zero
     */
    public static void setToTimeZero()
    {
        timeZero = System.currentTimeMillis();
        timeStore = 0;
    }
    
    /**
     * If paused, 
     * 
     * @return the time elapsed in milliseconds
     */
    public static int getTime()
    {
        if (paused)
            return timeStore;
        return (int)(System.currentTimeMillis()-timeZero)+timeStore;
    }
    
    /**
     * Called to signify that the game is paused. If this is called, getTime() 
     * will not increase until resume() is called, when it will then increase
     * normally
     */
    public static void pause()
    {
        timeStore = getTime();
        paused = true;
    }
    
    /**
     * Called to signify that a paused game has now been resumed.
     * Does nothing if the game has not been paused
     */
    public static void resume()
    {
        if (!paused)
            return;
        timeZero = System.currentTimeMillis();
        paused = false;
    }
}