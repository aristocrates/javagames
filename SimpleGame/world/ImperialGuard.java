package world;

import java.awt.*;
import otherStuff.*;
/**
 * Write a description of class ImperialGuard here.
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public class ImperialGuard extends theRootOfAllEvil
{
    private int level;
    
    public ImperialGuard(int level)
    {
        this.level = level;
    }
    
    public int damageDone()
    {
        return (int)(0.5*Math.pow(level,3)+2.5*level+15);
    }
    
    public void update(GameEvent e)
    {
    }
    
    public Shape getDimensions()
    {
        return null;
    }
    
    public void drawMe(Graphics g)
    {
    }
}
