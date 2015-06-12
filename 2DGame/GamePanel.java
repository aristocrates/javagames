import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
/**
 * Abstract class GamePanel - write a description of the class here
 * 
 * @author Nick Meyer
 * @version Beta
 */
public abstract class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{
    public int x, y;
    public Graphics buffer;
    public BufferedImage image;
    public boolean typable;
    public GamePanel(int x1, int y1)
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        addKeyListener(this);
        
        x = x1;
        y = y1;
        typable = false;
        image = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        buffer = image.getGraphics();
        buffer.setColor(Color.white);
        buffer.fillRect(0,0,x,y);
        
        setLayout(null);
        setFocusTraversalKeysEnabled(false);
    }
    public void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
    public static void main(String[] args)
    {
    }
}
