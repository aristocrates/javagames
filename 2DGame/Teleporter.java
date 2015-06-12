import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
/**
 * Write a description of class Teleporter here.
 * 
 * @author Nick Meyer
 * @version Beta
 */
public class Teleporter extends Component
{
    int x2, y2;
    public Teleporter(int x1, int y1, int X, int Y)
    {
        super(x1, y1);
        x2 = X;
        y2 = Y;
        type = 4;
    }
    public Component clone()
    {
        return new Teleporter(x,y,x2,y2);
    }
    public void draw(Graphics g)
    {
        g.drawImage((new ImageIcon("images\\portal.gif")).getImage(),x,y,23,37,null);
    }
    public int update(int x1, int y1, double time)
    {
        if (!demo&&MainPanel.distanceFormula(x+12,y+18,x1,y1)<10)
        {
            ((MainPanel)GameMain.panel()).ballX = x2;
            ((MainPanel)GameMain.panel()).ballY = y2;
        }
        return 0;
    }
}
