import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
/**
 * Write a description of class Wall here.
 * 
 * @author Nick Meyer
 * @version Beta
 */
public class Wall extends Component
{
    public boolean deadly, wide;
    private int[] dimension = new int[2]; //dimension[0] is x length, dimention[1] is y
    private Color color;
    public Wall(int x1, int y1, boolean toxic, boolean sideways)
    {
        super(x1,y1);
        type = 1;
        deadly = toxic;
        wide = sideways;
        if (deadly) color = Color.red;
        else color = Color.black;
        if (!wide)
        {
            dimension[0] = 10;
            dimension[1] = 20;
        }
        else
        {
            dimension[0] = 20;
            dimension[1] = 10;
        }
    }
    public Component clone()
    {
        return new Wall(x,y,deadly,wide);
    }
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x,y,dimension[0],dimension[1]);
    }
    public int update(int X, int Y, double time)
    {
        if (!demo && X>=x && X<=(dimension[0]+x) && Y>=y && Y<=(dimension[1]+y))
            if (deadly)
                return 1;
        return 0;
    }
    public int[] walls()
    {
        int[] coord = {x,y};
        int[] ans = new int[4];
        for (double i = 0.5; i < 1.1; i+=0.5)
            for (int b = 0; b < 2; b++)
                ans[b+2*(int)i] = b*dimension[(int)i]+coord[(int)i];
        return ans;
    }
}
