package BasicStructure;
import java.util.*;
import java.awt.*;
/**
 * The background
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public abstract class Tower
{
    private Coordinate c;
    private ArrayList<Tower> links;
    private int maxNumLinks;
    private double maxLinkDistance;
    
    public Tower(Coordinate c, int maxLinks, double linkDist)
    {
        this.c = c;
        links = new ArrayList<Tower>();
        maxNumLinks = maxLinks;
        maxLinkDistance = linkDist;
    }
    
    public Coordinate getCoordinate()
    {
        return c;
    }
    
    public void link(Tower other)
    {
        if (links.size() < maxNumLinks && 
            getCoordinate().distanceTo(other.getCoordinate()) < maxLinkDistance)
        {
            links.add(other);
            other.links.add(this);
        }
    }
    
    public abstract Color towerColor();
    
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.blue);
        for (Tower t : links)
            g.drawLine(getCoordinate().getX(),getCoordinate().getY(),
            t.getCoordinate().getX(),t.getCoordinate().getY());
        
    }
}