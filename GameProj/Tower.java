import java.util.*;
import java.awt.*;
import javax.swing.*;
/**
 * The background
 * 
 * @author Nick Meyer, Sydney Desai
 * @version 1.0
 */
public abstract class Tower implements Icon
{
    private Coordinate c;
    private World w;
    private ArrayList<Tower> links;
    private int maxNumLinks;
    private double maxLinkDistance;
    private double currentHealth;
    private int maxHealth;
    private boolean activated;

    public Tower(Coordinate c, int maxLinks, double linkDist, int maxHealth,
                 World w)
    {
        this.c = c;
        this.w = w;
        links = new ArrayList<Tower>();
        maxNumLinks = maxLinks;
        maxLinkDistance = linkDist;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        activated = false;
    }
    
    public ArrayList<Tower> getLinks()
    {
        return links;
    }
    
    public boolean activated()
    {
        return activated;
    }
    
    public void activate()
    {
        if (!activated())
            activated = true;
    }
    
    public final void act()
    {
        if (activated())
            doStuff();
        else
            build();
    }
    
    public abstract void doStuff();
    
    public abstract void build();
    
    public int getIconHeight()
    {
        return 2*radius();
    }
    
    public abstract ResourceManager cost();
    
    public int getIconWidth()
    {
        return 2*radius();
    }
    
    public void changeHealth(int x)
    {
        currentHealth -= x;
    }
    
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        this.c.setPoint(x,y);
        paintComponent(g);
    }
    
    public World getWorld()
    {
        return w;
    }

    public Coordinate getCoordinate()
    {
        return c;
    }
    
    public abstract Tower clone();

    public void link(Tower other)
    {
        if (canLink(other))
        {
            links.add(other);
            other.links.add(this);
        }
    }
    
    public boolean canLink(Tower other)
    {
        return links.size() < maxNumLinks && other.links.size() < other.maxNumLinks &&
        (withinDistance(other) || other.withinDistance(this));
    }

    private boolean withinDistance(Tower other)
    {
        return getCoordinate().distanceTo(other.getCoordinate()) < maxLinkDistance;
    }

    public abstract Color towerColor();

    public abstract int radius();
    
    public abstract String toString();

    public void takeDamage(int damage)
    {
        currentHealth-=damage;
        if(currentHealth<=0)
            removeSelf();
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.orange);
        for (Tower t : links)
        {
            drawLinkTo(t,g);
        }
        if (activated())
            g.setColor(towerColor());
        else
            g.setColor(Color.white);
        g.fillOval(getCoordinate().getX()-radius(),
            getCoordinate().getY()-radius(),
            2*radius(),2*radius());
        drawHealthBar(g);
    }
    
    public void drawLinkTo(Tower t, Graphics g)
    {
        g.drawLine(getCoordinate().getX(),getCoordinate().getY(),
                t.getCoordinate().getX(),t.getCoordinate().getY());
    }
    
    public void drawHealthBar(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(getCoordinate().getX()-radius(),
            getCoordinate().getY()-radius(),
            2*radius(),3);
        g.setColor(Color.green);
        g.fillRect(getCoordinate().getX()-radius(),
            getCoordinate().getY()-radius(),
            (int)(2*radius()*(1.0*currentHealth/maxHealth)),3);
    }
    
    public void paintOutline(Graphics g)
    {
        g.setColor(new Color(towerColor().getRGB()+(200<<24),true));
        g.fillOval(getCoordinate().getX()-radius(),getCoordinate().getY()-radius(),
                   2*radius(),2*radius());
        for (Tower t : getWorld().getAllTowers())
            if (canLink(t))
                drawLinkTo(t,g);
    }
    
    public boolean equals(Object o)
    {
        if (!(o instanceof Tower))
            return false;
        Tower t = (Tower)o;
        return toString().equals(t.toString()) && t.getCoordinate().equals(getCoordinate());
    }
    
    public void removeTower(Tower t)
    {
        int index = 0;
        while (index < links.size() && !links.get(index).equals(t))
            index++;
        if (index != links.size())
            links.remove(index);
    }

    public void removeSelf()
    {
        w.removeTower(this);
        for (Tower t : links)
            t.removeTower(this);
        setRemoved(true);
    }
    
    private boolean removed;
    public boolean removed()
    {
        return removed;
    }
    
    public void setRemoved(boolean b)
    {
        removed = b;
    }
}