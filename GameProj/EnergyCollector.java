import java.awt.*;
public class EnergyCollector extends Tower
{
    public EnergyCollector(Coordinate c, World w)
    {
        super(c,3,75,100,w);
        buildCredits = 0;
    }
    
    public EnergyCollector(Coordinate c, int maxLinks, double linkDist, int maxHealth,
    World w)
    {
        super(c,maxLinks,linkDist,maxHealth,w);
        buildCredits = 0;
    }
    
    public void doStuff()
    {
        if (Math.random()>.99)
            getWorld().addPeople(1);
    }
    
    private int buildCredits;
    private final int CREDITS_NEEDED = 20;
    public void build()
    {
        if (buildCredits >= CREDITS_NEEDED)
            activate();
        else
            buildCredits++;
    }
    
    public Tower clone()
    {
        return new EnergyCollector(getCoordinate().clone(),getWorld());
    }
    
    public ResourceManager cost()
    {
        return new ResourceManager(100,5);
    }
    
    public Color towerColor()
    {
        return Color.blue;
    }
    
    public int radius()
    {
        return 10;
    }
    
    public String toString()
    {
        return "EnergyCollector";
    }
}