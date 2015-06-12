/**
 * Just makes it simpler to create threads
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public abstract class GameThread implements Runnable
{
    private Thread t;
    
    /**
     * @return a thread for this Runnable
     */
    public Thread getThread()
    {
        if (t == null)
            t = new Thread(this);
        return t;
    }
}