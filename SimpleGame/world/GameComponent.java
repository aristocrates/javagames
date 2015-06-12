package world;
import java.awt.*;
import otherStuff.*;
/**
 * All the random stuff extends this. All subclasses of GameComponent must be either
 * helpful, neutral, or apathetic toward Blarg (unless said subclass is also a
 * subclass of theRootOfAllEvil).
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public abstract class GameComponent
{
    public abstract void drawMe(Graphics g);
    
    public abstract Shape getDimensions();
    
    public abstract void update(GameEvent e);

    public abstract boolean loopable();
    
    public abstract GameComponent clone();
}