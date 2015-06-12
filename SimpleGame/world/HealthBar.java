package world;
import otherStuff.*;
import java.awt.*;
/**
 * Write a description of class HealthBar here.
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public class HealthBar extends GameComponent
{
    public HealthBar(int level)
    {
    }
    
    public GameComponent clone()
    {
        return new HealthBar(5);
    }
    
    public void update(GameEvent e)
    {
    }
    
    public void drawMe(Graphics g)
    {
    }
    
    public boolean loopable()
    {
        return true;
    }
    
    public Shape getDimensions()
    {
        return null;
    }
}
