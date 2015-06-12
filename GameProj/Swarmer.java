import java.awt.*;
/**
 * Write a description of class Swarmer here.
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class Swarmer extends Enemy
{
    public Swarmer(Coordinate c, World w, int direction, double timeAppear)
    {
        super(c,100,100,w,direction,10,timeAppear);
    }
    
    public void paintComponent(Graphics g)
    {
        if (Time.getTime()/1000 < timeAppear())
            return;
        g.setColor(Color.green);
        g.fillPolygon(triangle());
        drawHealthBar(g);
    }
    
    public int damagePerTurn()
    {
        return 1;
    }
}
