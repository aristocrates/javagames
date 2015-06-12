package BasicStructure;
/**
 * The background
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public abstract class GameThread implements Runnable
{
    private Thread t;
    
    public Thread getThread()
    {
        if (t == null)
            t = new Thread(this);
        return t;
    }
}