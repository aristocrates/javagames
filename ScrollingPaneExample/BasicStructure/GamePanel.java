package BasicStructure;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
/**
 * The panel
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class GamePanel extends JPanel
implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener
{
    private Image paint;
    private Graphics editor;
    private World w;
    
    public GamePanel(int x, int y)
    {
        setSize(x,y);
        paint = new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
        editor = paint.getGraphics();
        
        setFocusable(true);
        requestFocusInWindow();
        
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
        
        w = null;
    }
    
    public void paintComponent(Graphics g)
    {
        if (w != null)
            w.paintComponent(editor);
        g.drawImage(paint,0,0,getWidth(),getHeight(),null);
    }
    
    public void loadWorld(World w)
    {
        this.w = w;
    }
    
    public void setUserInterface(UserInterface u)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseClicked(MouseEvent e)
    {
    }
    
    public void mouseMoved(MouseEvent e)
    {
    }
    
    public void mouseDragged(MouseEvent e)
    {
    }
    
    public void mouseWheelMoved(MouseWheelEvent e)
    {
    }
    
    public void keyReleased(KeyEvent e)
    {
    }
    
    public void keyPressed(KeyEvent e)
    {
    }
    
    public void keyTyped(KeyEvent e)
    {
    }
}