package otherStuff;
/**
 * Maybe calling this GameThread was a bit deceptive considering that it doesn't actually
 * extend Thread
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public abstract class GameThread implements Runnable
{
    //well at least it has thread here
    private static Thread thread;
    
    public Thread getThread()
    {
        if (thread == null)
            thread = new Thread(this);
        return thread;
    }
}