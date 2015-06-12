import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
/**
 * Write a description of class DeathStar here.
 * 
 * @author Nick Meyer
 * @version Beta
 */
public class DeathStar extends Component
{
    public int dX, dY;
    int fileNum = 1;
    public DeathStar(int x1, int y1, int deltaX, int deltaY)
    {
        super(x1,y1);
        dX = deltaX;
        dY = deltaY;
        type = 2;
    }
    public Component clone()
    {
        return new DeathStar(x,y,dX,dY);
    }
    public void draw(Graphics g)
    {
        String fileName = "images\\DeathStar";
        if (demo)
            fileName += "1";
        else
            fileName += fileNum;
        fileName += ".gif";
        g.drawImage((new ImageIcon(fileName)).getImage(), x-15, y-15, 30, 30, null);
    }
    public int update(int x1, int y1, double time)
    {
        if (!demo)
        {
            fileNum = fileNum%6+1;
            x += dX;
            y += dY;
            if (x>=500) x = 1;
            if (x<=0) x = 500;
            if (y>=500) y = 1;
            if (y<=0) y = 500;
            if (Math.pow((x1-x)*(x1-x)+(y1-y)*(y1-y),0.5)<=15)
                return 1;
        }
        return 0;
    }
}
