import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
/**
 * Abstract class Component - write a description of the class here
 * 
 * @author Nick Meyer
 * @version Beta
 */
public abstract class Component
{
    public int x, y, score;
    public int order;
    public int type = -1;
    public boolean disabled = false, shutDown = false, exploding = false, demo = false;
    public Component(int x1, int y1)
    {
        x = x1;
        y = y1;
    }
    public abstract Component clone();
    public abstract void draw(Graphics g);
    public abstract int update(int x, int y, double time);
}
