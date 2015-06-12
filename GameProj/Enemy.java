import java.awt.*;
/**
 * The background
 * 
 * @author Nick Meyer, Sydney Desai
 * @version 1.0
 */
public abstract class Enemy
{
    private int currentHealth, maxHealth;
    private int radius;
    private int direction;
    private int height;
    private double timeAppear;
    private World w;
    private Coordinate c;
    private Tower longRange;
    private boolean removed;
    
    public Enemy(Coordinate c, int maxHealth, int radius, World w, 
    int direction, int height, double timeAppear)
    {
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.radius = radius;
        this.w = w;
        this.c = c;
        this.direction = direction;
        this.height = height;
        this.timeAppear = timeAppear;
        longRange = w.getRandomTower(c);
    }
    
    public int getHeight()
    {
        return height;
    }
    
    /**
     * Creates a right isoceles triangle such that the enemy's coordinate is the
     * centroid of the triangle and the line connecting the centroid with the 
     * vertex opposite the hypotenuse forms an angle of direction degrees with
     * the x axis
     */
    public Polygon triangle()
    {
        Polygon ans = new Polygon();
        double hypotenuse = 2*height;
        double[] angles = {direction,direction+90+Math.atan(height*2.0/(hypotenuse*3.0)),
                           direction+270-Math.atan(height*2.0/(hypotenuse*3.0))};
        for (double angle : angles)
            ans.addPoint((int)(c.getX()+Math.sin(angle*Math.PI/180)*2.0*height/3),
                         (int)(c.getY()-Math.cos(angle*Math.PI/180)*2.0*height/3));
        return ans;
    }
    
    /**
     * Angle is in degrees
     */
    public void setAngle(int angle)
    {
        direction = angle;
    }
    
    public Coordinate getCoordinate()
    {
        return c;
    }
    
    public World getWorld()
    {
        return w;
    }
    
    public double timeAppear()
    {
        return timeAppear;
    }
    
    public abstract void paintComponent(Graphics g);
    
    public void drawHealthBar(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(getCoordinate().getX()-height,
            getCoordinate().getY()-height,
            2*height,3);
        g.setColor(Color.green);
        g.fillRect(getCoordinate().getX()-height,
            getCoordinate().getY()-height,
            (int)(2*height*(1.0*currentHealth/maxHealth)),3);
    }
    
    /**
     * Translates the enemy, intended mostly for teleportation-style moves
     */
    public void move(int dx, int dy)
    {
        c.setPoint(c.getX()+dx,c.getY()+dy);
    }
    
    /**
     * Moves the enemy by distance amount in it's current direction
     */
    public void move(int distance)
    {
        int angle = direction;
        c.setPoint(getCoordinate().getX()+(int)(distance*Math.sin(angle*Math.PI/180)),
                   getCoordinate().getY()-(int)(distance*Math.cos(angle*Math.PI/180)));
    }
    
    public abstract int damagePerTurn();
    
    public double getCurrentHealth()
    {
        return currentHealth;
    }
    
    public int getRadius()
    {
        return radius;
    }
    
    public void act()
    {
        if (Time.getTime()/1000. < timeAppear)
            return;
        if (longRange == null || longRange.removed())
            longRange = getWorld().getRandomTower(getCoordinate());
        Tower t = w.getClosestTower(c, radius);
        if(t != null)
        {
            setAngle(getCoordinate().angleToward(t.getCoordinate()));
            attack(t);
            if (getCoordinate().distanceTo(t.getCoordinate()) > 20)
                move(2);
        }
        else
        {
            if (longRange != null)
                setAngle(getCoordinate().angleToward(longRange.getCoordinate()));
            move(2);
        }
    }
    
    public void removeSelf()
    {
        getWorld().removeEnemy(this);
        removed = true;
    }
    
    public boolean removed()
    {
        return removed;
    }
    
    public void attack(Tower t)
    {
        t.takeDamage(damagePerTurn());
    }
    
    public void die()
    {
        getWorld().gainMetal(20);
        removeSelf();
    }
    
    public void takeDamage(int amount)
    {
        currentHealth -= amount;
        if (currentHealth < 0)
            die();
    }
}