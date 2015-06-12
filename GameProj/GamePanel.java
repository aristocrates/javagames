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
implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener,
ActionListener
{
    private Image paint;
    private Graphics editor;
    private World w;
    private int mouseWheelState;
    private boolean scrolling;
    private Cursor moveCursor, normalCursor;
    private UserInterface u;
    private Coordinate def;
    private Tower towerPlacing;
    
    public GamePanel(int x, int y)
    {
        setLayout(null);
        setSize(x,y);
        paint = new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
        editor = paint.getGraphics();
        moveCursor = new Cursor(Cursor.HAND_CURSOR);
        normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        
        setFocusable(true);
        requestFocusInWindow();
        
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
        u = new UserInterface(this);
        u.addSelfTo(this);
        
        w = null;
        mouseWheelState = 0;
        scrolling = false;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        def = new Coordinate(50,50);
        if (e.getActionCommand().equals("Linker"))
            towerPlacing = new Linker(def,w);
        if (e.getActionCommand().equals("AttackTower"))
            towerPlacing = new AttackTower(def,w);
        if (e.getActionCommand().equals("EnergyCollector"))
            towerPlacing = new EnergyCollector(def,w);
        if (e.getActionCommand().equals("Cancel"))
            getWorld().placeTower(towerPlacing = null);
        if (towerPlacing != null)
            getWorld().placeTower(towerPlacing);
    }
    
    public World getWorld()
    {
        return w;
    }
    
    public UserInterface userInterface()
    {
        return u;
    }
    
    public void paintComponent(Graphics g)
    {
        if (w != null)
            w.paintComponent(editor);
        g.drawImage(paint,0,0,getWidth(),getHeight(),null);
    }
    
    public void loadWorld(World w)
    {
        towerPlacing = null;
        requestFocusInWindow();
        mouseWheelState = 0;
        this.w = w;
    }
    
    /**
     * Does nothing
     */
    public void mouseExited(MouseEvent e)
    {
    }
    
    /**
     * Does nothing
     */
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
        scrolling = false;
        setCursor(normalCursor);
        e.translatePoint(u.getWidth()-getWidth(),u.getHeight()-getHeight());
        u.mouseEntered(e);
    }
    
    public void mousePressed(MouseEvent e)
    {
        requestFocusInWindow();
        if (towerPlacing != null)
        {
            w.setTower();
            towerPlacing = towerPlacing.clone();
            getWorld().placeTower(towerPlacing);
        }
        scrolling = true;
        setCursor(moveCursor);
        w.setOffset(e.getX()-w.x(),e.getY()-w.y());
    }
    
    public boolean scrolling()
    {
        return scrolling;
    }
    
    /**
     * Does nothing
     */
    public void mouseClicked(MouseEvent e)
    {
    }
    
    public void mouseMoved(MouseEvent e)
    {
        if (towerPlacing != null)
            towerPlacing.getCoordinate().setPoint((int)((e.getX()-w.x())/w.getMagnification()),
                                                  (int)((e.getY()-w.y())/w.getMagnification()));
    }
    
    public void mouseDragged(MouseEvent e)
    {
        w.setXY(e.getX(),e.getY());
    }
    
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        mouseWheelState += e.getWheelRotation();
        if (!getWorld().setMagnification(getMagnification()))
            mouseWheelState -= e.getWheelRotation();
    }
    
    private double getMagnification()
    {
        return 1-mouseWheelState*0.5/3;
    }
    
    /**
     * 
     */
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN)
            xyChange[1] = 0;
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT)
            xyChange[0] = 0;
    }
    
    private int[] xyChange = {0,0};
    /**
     * Responds to when keyboard input
     * 
     * Scrolls the game window accordingly when arrow keys are pressed
     * 
     * Zooms in with the + (or =) key and with the PgUp key
     * Zooms out with the - (or _) key and with the PgDn key
     */
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_PAGE_UP || code == KeyEvent.VK_EQUALS)
            mouseWheelMoved(new MouseWheelEvent(this,0,0,0,0,0,0,false,0,0,-1));
        if (code == KeyEvent.VK_PAGE_DOWN || code == KeyEvent.VK_MINUS)
            mouseWheelMoved(new MouseWheelEvent(this,0,0,0,0,0,0,false,0,0,1));
        
        if (code == KeyEvent.VK_UP)
            xyChange[1] = 6;
        if (code == KeyEvent.VK_DOWN)
            xyChange[1] = -6;
        if (code == KeyEvent.VK_LEFT)
            xyChange[0] = 6;
        if (code == KeyEvent.VK_RIGHT)
            xyChange[0] = -6;
        w.changeXY(xyChange[0],xyChange[1]);
    }
    
    /**
     * Does nothing, necessary to override method in interface KeyListener
     */
    public void keyTyped(KeyEvent e)
    {
    }
}