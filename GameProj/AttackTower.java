import java.awt.*;
public class AttackTower extends Tower
{
    public AttackTower(Coordinate c, World w)
    {
        super(c,1,50,200,w);
    }

    public AttackTower(Coordinate c, int maxLinks, double linkDist, int maxHealth,
    World w)
    {
        super(c,maxLinks,linkDist,maxHealth,w);
    }

    public Tower clone()
    {
        return new AttackTower(getCoordinate().clone(),getWorld());
    }
    
    public ResourceManager cost()
    {
        return new ResourceManager(200,10);
    }

    public Color towerColor()
    {
        return Color.red;
    }

    public int radius()
    {
        return 6;
    }

    public void attack(Enemy e)
    {
        e.takeDamage(damagePerTurn());
    }

    public void takeDamage(Enemy e)
    {
        changeHealth(e.damagePerTurn());
    }

    public int damagePerTurn()
    {
        if (getLinks().size() == 0 || getLinks().get(0).toString().equals("AttackTower"))
            return 2;
        if (getLinks().get(0).toString().equals("Linker"))
            return 20;
        else
            return 5;
    }

    public void doStuff()
    {
        Enemy e = getWorld().getClosestEnemy(getCoordinate(), 100);
        if(e != null)
            attack(e);
    }
    
    private int currentCharge;
    private static final int CHARGE_AMOUNT = 50;
    public void build()
    {
        if (currentCharge >= CHARGE_AMOUNT)
        {
            activate();
            return;
        }
        if (getLinks().size() != 1)
            return;
        if (!getLinks().get(0).activated())
            return;
        currentCharge += 5;
    }
    
    public void paintOutline(Graphics g)
    {
        g.setColor(new Color(250,250,250,190));
        g.fillOval(getCoordinate().getX()-50,getCoordinate().getY()-50,100,100);
        super.paintOutline(g);
    }

    public String toString()
    {
        return "AttackTower";
    }
}