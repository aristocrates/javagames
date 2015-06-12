import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
public class Pane extends JPanel
implements MouseListener, MouseMotionListener, MouseWheelListener
{
    int x, y;
    int mouseWheelLocation;
    BufferedImage img;
    Background pict;
    Graphics buff;
    boolean bounded;
    int offsetX, offsetY;

    public static void main(String[] args)
    {
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        int x = (int)d.getWidth();
        int y = (int)d.getHeight();
        JFrame f = new JFrame("hi");
        f.setContentPane(new Pane(800,600,true));
        f.setSize(800,600);
        f.setLocation((x-f.getWidth())/2,(y-f.getHeight())/2);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    boolean paused;

    public Pane(int x, int y, boolean bounded)
    {
        setSize(x,y);
        this.x = 0;
        this.y = 0;
        this.bounded = bounded;
        mouseWheelLocation = 0;
        //pict = new ImageIcon("untitled.png").getImage();
        pict = new StarrySky(1152,864,new Color(25,25,25),Color.white);
        //pict = new StarrySky(1152,864,Color.BLACK,Color.ORANGE);
        Thread t = new Thread(new Runnable(){
                    public void run()
                    {
                        try{
                            while (true){
                                if (paused)
                                    Thread.sleep(1000);
                                else{
                                    pict.update();
                                    repaint();
                                    Thread.sleep(100);
                                }
                            }
                        }
                        catch (InterruptedException e){}
                    }
                });
        img = new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
        buff = img.getGraphics();
        buff.setColor(Color.white);

        offsetX = 0;
        offsetY = 0;

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        
        t.start();
    }

    public void paintComponent(Graphics g)
    {
        buff.fillRect(0,0,getWidth(),getHeight());
        buff.drawImage(pict.getImage(),x,y,adjustedPictWidth(),adjustedPictHeight(),
            null);
        g.drawImage(img,0,0,img.getWidth(),img.getHeight(),null);
    }

    public double getMagnification()
    {
        return 1-mouseWheelLocation*0.5/3;
    }

    public int adjustedPictWidth()
    {
        return (int)(pict.getWidth()*getMagnification());
    }

    public int adjustedPictHeight()
    {
        return (int)(pict.getHeight()*getMagnification());
    }

    public void mouseWheelMoved(MouseWheelEvent e)
    {
        mouseWheelLocation += e.getWheelRotation();
        if (bounded && (adjustedPictHeight() < getHeight() ||
            adjustedPictWidth() < getWidth()))
            mouseWheelLocation -= e.getWheelRotation();
        if (x < (getWidth() - adjustedPictWidth()))
            x = getWidth()-adjustedPictWidth();
        if (y < (getHeight() - adjustedPictHeight()))
            y = getHeight()-adjustedPictHeight();
        repaint();
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void mousePressed(MouseEvent e)
    {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        offsetX = e.getX()-x;
        offsetY = e.getY()-y;
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    /**
     * Does nothing
     */
    public void mouseMoved(MouseEvent e)
    {
    }

    public void mouseDragged(MouseEvent e)
    {
        x = e.getX()-offsetX;
        y = e.getY()-offsetY;
        if (bounded)
        {
            if (x > 0)
                x = 0;
            if (y > 0)
                y = 0;
            if (x < (getWidth() - pict.getWidth()*getMagnification()))
                x = getWidth()-adjustedPictWidth();
            if (y < (getHeight() - pict.getHeight()*getMagnification()))
                y = getHeight()-adjustedPictHeight();
        }
        repaint();
    }
}