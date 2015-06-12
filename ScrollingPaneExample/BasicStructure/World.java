package BasicStructure;
import java.awt.*;
import java.util.*;
/**
 * The background
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class World
{
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;
    private double magnify;
    
    public World(Background b, Objective o)
    {
        towers = new ArrayList<Tower>();
        enemies = new ArrayList<Enemy>();
    }
    
    public void magnifyBy(double num)
    {
        magnify *= num;
    }
    
    public void addTower(Tower t)
    {
        for (Tower t2 : towers)
            t.link(t2);
        towers.add(t);
    }
    
    /**
     * @precondition  t must be in the Tower list
     */
    public void removeTower(Tower t)
    {
        
    }
    
    public void paintComponent(Graphics g)
    {
    }
}