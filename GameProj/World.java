import java.awt.*;
import java.util.*;
/**
 * The class that stores the information 
 * 
 * Note: avoids using for each loops to prevent concurrent modification errors
 * 
 * @author Nick Meyer, Lawrence Hu
 * @version 1.0
 */
public class World
{
    private Background b;
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;
    private ArrayList<TextBubble> texts;
    private double magnify;
    private int width, height;
    private Tower towerPlacing;
    private Objective c;
    private ResourceManager m;
    private ResourceManager orig;

    private int offsetX, offsetY;
    private int x, y;

    /**
     * Makes a new World
     */
    public World(Background b, int width, int height, ResourceManager m)
    {
        this.b = b;
        this.m = m;
        orig = new ResourceManager(m);
        setSize(width,height);
        towers = new ArrayList<Tower>();
        enemies = new ArrayList<Enemy>();
        texts = new ArrayList<TextBubble>();
        magnify = 1;
    }
    
    /**
     * Used for the Score in the game's Account in Main
     */
    public int scoreIncrease()
    {
        return 0;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    /**
     * adds metal
     */
    public void gainMetal(int amnt)
    {
        resourceManager().subtract(new ResourceManager(-amnt,0));
    }
    
    /**
     * adds people
     */
    public void addPeople(int num)
    {
        resourceManager().subtract(new ResourceManager(0,-num));
    }
    
    /**
     * Subclasses override this method to provide another world object for
     * the purpose of restarting levels
     */
    public World newClone()
    {
        return new World(b,getWidth(),getHeight(),resourceManager());
    }
    
    /**
     * allows access to the game resources
     */
    public ResourceManager resourceManager()
    {
        return m;
    }
    
    public void setObjective(Objective c)
    {
        this.c = c;
    }
    
    public Objective getObjective()
    {
        return c;
    }
    
    public void addTextBubble(TextBubble b)
    {
        texts.add(b);
    }
    
    /**
     * adds an enemy to world
     */
    public void addEnemy(Enemy e)
    {
        enemies.add(e);
    }

    /**
     * moves the background
     */
    public void updateBackground()
    {
        b.update();
    }
    
    /**
     * Makes the world prepare 
     */
    public void placeTower(Tower t)
    {
        towerPlacing = t;
    }
    
    /**
     * Places the tower where the mouse was clicked in the world
     */
    public void setTower()
    {
        if (!resourceManager().construct(towerPlacing))
            return;
        addTower(towerPlacing);
        towerPlacing = null;
    }

    /**
     * updates the game components
     */
    public void updateComponents()
    {
        if (c.complete())
            Main.winLevel();
        if (c.fail())
            Main.loseLevel();
        for (int i = 0; i < enemies.size(); i++)
            enemies.get(i).act();
        for (int i = 0; i < towers.size(); i++)
            towers.get(i).act();
    }

    /**
     * sets the true size of the world (not the same as the window size,
     * it may be necessary to scroll to view the entire world
     */
    public void setSize(int x, int y)
    {
        width = x;
        height = y;
    }

    public int x()
    {
        return x;
    }

    public int y()
    {
        return y;
    }
    
    /**
     * returns a list of all towers within r from c, for enemies
     */
    public ArrayList<Tower> towersInRange(Coordinate c, double r)
    {
        ArrayList<Tower> ans = new ArrayList<Tower>();
        for (int i = 0; i < towers.size(); i++)
            if (towers.get(i).getCoordinate().distanceTo(c) < r)
                ans.add(towers.get(i));
        return ans;
    }
    
    public ArrayList<Tower> getAllTowers()
    {
        return towers;
    }

    /**
     * returns all enemies within r of c, for towers
     */
    public ArrayList<Enemy> enemiesInRange(Coordinate c, double r)
    {
        ArrayList<Enemy> ans = new ArrayList<Enemy>();
        for (int i = 0; i < enemies.size(); i++)
            if (enemies.get(i).getCoordinate().distanceTo(c) < r)
                ans.add(enemies.get(i));
        return ans;
    }
    
    /**
     * Returns a random tower weighted to be closer to the coordinate c
     */
    public Tower getRandomTower(Coordinate c)
    {
        if (towers.size() < 1)
            return null;
        Tower last = randTow();
        for (int i = 0; i < (int)(Math.random()*9)+1; i++)
        {
            Tower current = randTow();
            if (Math.random() > 0.95)
                return current;
            if (c.distanceTo(current.getCoordinate()) < 
                c.distanceTo(last.getCoordinate()))
                last = current;
            if (Math.random() > 0.9)
                return last;
        }
        return last;
    }
    
    private Tower randTow()
    {
        return towers.get((int)(Math.random()*towers.size()));
    }

    /**
     * returns the tower closest to c that is at most r away from c
     * 
     * returns null is there is no such tower
     */
    public Tower getClosestTower(Coordinate c, double r)
    {
        Tower ans = null;
        double minDistance = 0;
        for (int i = 0; i < towers.size(); i++)
        {
            Tower t = towers.get(i);
            double distance = t.getCoordinate().distanceTo(c);
            if(distance<=r)
            {
                if(ans == null)
                {
                    ans = t;
                    minDistance = distance;
                }
                else
                {
                    if ( distance < minDistance)
                    {
                        ans = t;
                        minDistance = distance;
                    }
                }
            }
        }
        return ans;
    }
    
    /**
     * returns the enemy closest to c that is at most r away from c
     * 
     * returns null is there is no such enemy
     */
    public Enemy getClosestEnemy(Coordinate c, double r)
    {
        Enemy ans = null;
        double minDistance = 0;
        for (int i = 0; i < enemies.size(); i++)
        {
            Enemy t = enemies.get(i);
            double distance = t.getCoordinate().distanceTo(c);
            if(distance <= r)
            {
                if(ans == null)
                {
                    ans = t;
                    minDistance = distance;
                }
                else
                {
                    if ( distance < minDistance)
                    {
                        ans = t;
                        minDistance = distance;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * Sets the magnification of the world when the user zooms in and out
     */
    public boolean setMagnification(double num)
    {
        double temp = magnify;
        magnify = num;
        if (adjustedPictHeight() < height || 
        adjustedPictWidth() < width)
        {
            magnify = temp;
            return false;
        }
        if (x < (width - adjustedPictWidth()))
            x = width-adjustedPictWidth();
        if (y < (height - adjustedPictHeight()))
            y = height-adjustedPictHeight();
        return true;
    }

    /**
     * For the dragging to scroll in the world feature
     */
    public void setOffset(int x, int y)
    {
        offsetX = x;
        offsetY = y;
    }

    /**
     * To be used with the mouseDragged() method in GamePanel
     */
    public void setXY(int eventX, int eventY)
    {
        x = eventX-offsetX;
        y = eventY-offsetY;
        if (x > 0)
            x = 0;
        if (y > 0)
            y = 0;
        if (x < (width - b.getWidth()*magnify))
            x = width-adjustedPictWidth();
        if (y < (height - b.getHeight()*magnify))
            y = height-adjustedPictHeight();
    }
    
    /**
     * For the dragging to scroll in the world feature
     */
    public void changeXY(int deltaX, int deltaY)
    {
        x += deltaX;
        y += deltaY;
        if (x > 0)
            x = 0;
        if (y > 0)
            y = 0;
        if (x < (width - b.getWidth()*magnify))
            x = width-adjustedPictWidth();
        if (y < (height - b.getHeight()*magnify))
            y = height-adjustedPictHeight();
    }

    /**
     * Adds a tower to the list, and links them
     */
    public void addTower(Tower t)
    {
        for (Tower t2 : towers)
            t.link(t2);
        towers.add(t);
    }

    private final int adjustedPictWidth()
    {
        return (int)(b.getWidth()*magnify);
    }
    
    public double getMagnification()
    {
        return magnify;
    }

    private final int adjustedPictHeight()
    {
        return (int)(b.getHeight()*magnify);
    }

    /**
     * Gets rid of tower and de-links it
     */
    public void removeTower(Tower t)
    {
        int index = 0;
        while (index < towers.size() && 
        !towers.get(index).getCoordinate().equals(t.getCoordinate()))
            index++;
        if (index != towers.size())
            towers.remove(index);
    }
    
    public void removeEnemy(Enemy e)
    {
        int index = 0;
        while (index < enemies.size() && 
        !enemies.get(index).getCoordinate().equals(e.getCoordinate()))
            index++;
        if (index != enemies.size())
            enemies.remove(index);
    }

    /**
     * Paints the world
     */
    public void paintComponent(Graphics g)
    {
        Image img = b.getImage();
        Graphics edit = img.getGraphics();
        for (int i = 0; i < towers.size(); i++)
            towers.get(i).paintComponent(edit);
        for (int i = 0; i < enemies.size(); i++)
            enemies.get(i).paintComponent(edit);
        if (towerPlacing != null)
            towerPlacing.paintOutline(edit);
        g.drawImage(img,x,y,
            adjustedPictWidth(),adjustedPictHeight(),null);
        for (int i = 0; i < texts.size(); i++)
            texts.get(i).drawMe(g);
    }
}