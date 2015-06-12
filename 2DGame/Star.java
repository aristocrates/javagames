import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
/**
 * Write a description of class Star here.
 * 
 * @author Nick Meyer
 * @version Beta
 */
public class Star extends Component
{
    private String orderS = "";
    private ImageIcon image;
    public Star(int x1, int y1, int sequence)
    {
        super(x1, y1);
        score = 500;
        type = 0;
        orderS += order = sequence;
        image = new ImageIcon("images\\star.gif");
    }
    public Component clone()
    {
        return new Star(x,y,order);
    }
    public void draw(Graphics g)
    {
        if (((MainPanel)GameMain.panel()).currentStar==order&&!shutDown)
        {
            g.drawImage(image.getImage(), x, y, 28, 25, null);
            g.setColor(Color.black);
            g.drawString(orderS, x+11, y+15);
        }
    }
    public int update(int X, int Y, double time)
    {
        if (!demo)
        {
            if (shutDown) return 0;
            if (((MainPanel)GameMain.panel()).currentStar==order)
            {
                if (score>=2)
                    score -= 2;
                if (Math.pow((X-x-14)*(X-x-14)+(Y-y-12.5)*(Y-y-12.5),0.5)<=18)
                {
                    disabled = true;
                    return 2;
                }
            }
        }
        return 0;
    }
}