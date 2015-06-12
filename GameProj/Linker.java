import java.awt.*;
/**
 * Write a description of class Link here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Linker extends Tower
{
    public Linker(Coordinate c, World w)
    {
        super(c,5,100,50,w);
    }
    
    public Linker(Coordinate c, int maxLinks, double linkDist, int maxHealth,
    World w)
    {
        super(c,maxLinks,linkDist,maxHealth,w);
    }
    
    public void doStuff()
    {
    }
    
    public void build()
    {
        for (int i = 0; i < getLinks().size(); i++)
            if (getLinks().get(i).activated())
            {
                activate();
                return;
            }
    }
    
    public ResourceManager cost()
    {
        return new ResourceManager(10,0);
    }
    
    public Tower clone()
    {
        return new Linker(getCoordinate().clone(),getWorld());
    }
    
    public Color towerColor()
    {
        return Color.gray;
    }
    
    public void link(Tower other)
    {
        super.link(other);
    }
    
    public int radius()
    {
        return 3;
    }
    
    public String toString()
    {
        return "Linker";
    }
}
