import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
/**
 * Write a description of interface GamePane here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface GamePane
{
    public abstract class GameFrame extends JFrame
    {
        private static Thread myThread;
        public GameFrame(String s, JPanel j, Thread t)
        {
            super(s);
            
            myThread = t;
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowListener(){
                public void windowDeactivated(WindowEvent e){}
                public void windowActivated(WindowEvent e){}
                public void windowDeiconified(WindowEvent e){}
                public void windowIconified(WindowEvent e){}
                public void windowClosed(WindowEvent e){}
                public void windowClosing(WindowEvent e){
                    new GameMain.EndGame();
                }
                public void windowOpened(WindowEvent e){}
            });
            setContentPane(j);
            setSize(j.size());
            setResizable(false);
            setVisible(true);
        }
        public void resize(int timeInMilliseconds, int width, int height)
        {
            int currentWidth = getWidth();
            int currentHeight = getHeight();
            
        }
        public void resize(int timeInMilliseconds, JPanel p)
        {
        }
        public void resize(JPanel p)
        {
        }
    }
    public abstract class GamePanel extends JPanel
    {
        BufferedImage img;
        Graphics buffer;
        public GamePanel()
        {
            super();
            
            img = new BufferedImage(665,500,BufferedImage.TYPE_INT_RGB);
        }
        public void paintComponent(Graphics g)
        {
            g.drawImage(img,0,0,getWidth(),getHeight(),null);
        }
    }
    public abstract class ButtonPanel extends JPanel implements MouseListener
    {
        public ButtonPanel()
        {
            super();
        }
    }
}
