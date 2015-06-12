import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
/**
 * Write a description of class Bomb here.
 * 
 * @author Nick Meyer
 * @version Beta
 */
public class Bomb extends Component
{
    public double time, startTime, explodeTime;
    public double explodeRadius;
    public int magnitude, rad, maxRad;
    public Bomb(int x1, int y1, double StartTime, double ExplodeTime, int magnit)
    {
        super(x1,y1);
        startTime = StartTime;
        explodeTime = ExplodeTime;
        magnitude = magnit;
        maxRad = 16*magnitude;
        type = 3;
    }
    public Component clone()
    {
        return new Bomb(x,y,startTime,explodeTime,magnitude);
    }
    public void draw(Graphics g)
    {
        exploding = false;
        if (demo)
        {
            String s = "";
            s+=(int)(explodeTime-time);
            g.drawImage((new ImageIcon("images\\bomb.gif")).getImage(), x-11, y-23, 30, 30, null);
            g.setColor(Color.white);
            g.drawString(s,x-4,y+4);
            Color c = new Color(255,0,0,80);
            g.setColor(c);
            g.drawOval(x-maxRad/2,y-maxRad/2,maxRad,maxRad);
        }
        else
            if (time >= startTime)
            {
                if (time < explodeTime)
                {
                    String s = "";
                    s+=(int)(explodeTime-time);
                    g.drawImage((new ImageIcon("images\\bomb.gif")).getImage(), x-11, y-23, 30, 30, null);
                    g.setColor(Color.white);
                    g.drawString(s,x-4,y+4);
                    if (((int)((explodeTime-time)*10))%10>5)
                    {
                        Color c = new Color(255,0,0,80);
                        g.setColor(c);
                        g.drawOval(x-maxRad/2,y-maxRad/2,maxRad,maxRad);
                    }
                }
                else
                {
                    if (time  <= explodeTime+0.51)
                    {
                        exploding = true;
                        rad = (int)((time-explodeTime)*100/3);
                        Color c = new Color(255-rad,255-rad*15,0,255-rad*8);
                        rad*=magnitude;
                        g.setColor(c);
                        g.fillOval(x-rad/2,y-rad/2,rad,rad);
                    }
                }
            }
    }
    public int update(int x1, int y1, double Time)
    {
        if (!demo)
        {
            time = Time;
            if (exploding && MainPanel.distanceFormula(x1,y1,x,y)<=(rad/2+5))
                return 1;
        }
        return 0;
    }
}
