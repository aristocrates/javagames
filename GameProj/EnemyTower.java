import java.awt.*;
/**
 * Essentially Towers for the enemy that release enemies
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class EnemyTower extends Enemy
{
    /**
     * Makes a new enemy tower
     */
    public EnemyTower(Coordinate c, int maxHealth, int radius, World w)
    {
        super(c,maxHealth, 0, w, 0, 2*radius, 0);
    }
    
    /**
     * Makes a new enemy tower
     */
    public EnemyTower(Coordinate c, int maxHealth, int radius, World w, double timeAppear)
    {
        super(c,maxHealth, 0, w, 0, 2*radius, timeAppear);
    }
    
    /**
     * Useless as EnemyTowers do not directly attack, just to override
     */
    public int damagePerTurn()
    {
        return 100;
    }
    
    /**
     * Has the tower release enemies over time
     */
    public void act()
    {
        if (Math.random() > 0.995)
            getWorld().addEnemy(new Swarmer(getCoordinate().clone(),getWorld(),0,Time.getTime()/1000+0.5));
    }
    
    public void die()
    {
        getWorld().gainMetal(80);
        super.die();
    }
    
    /**
     * Draws this tower
     */
    public void paintComponent(Graphics g)
    {
        g.setColor(new Color(200,200,50));
        g.fillOval(getCoordinate().getX()-getHeight()/2,getCoordinate().getY()-getHeight()/2,
                   getHeight(),getHeight());
        drawHealthBar(g);
    }
}