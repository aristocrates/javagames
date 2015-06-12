import java.awt.*;
import java.awt.image.*;
/**
 * Write a description of class Level here.
 * 
 * @author Nick Meyer
 * @version 2.0
 */
public class Level implements GameComponents
{
    private Player user;
    private GameObstacle[] obstacles;
    private Image background;
    public Level(int width, int height, GameObstacle[] obstacles)
    {
        this.obstacles = new GameObstacle[obstacles.length];
        for (int i = 0; i < obstacles.length; i++)
            this.obstacles[i] = obstacles[i];
        background = new BufferedImage(665,500,BufferedImage.TYPE_INT_RGB);
    }
    public void draw(Graphics g)
    {
        g.drawImage(background,0,0,665,500,null);
        for (int i = 0; i < obstacles.length; i++)
            obstacles[i].draw(g);
    }
}
