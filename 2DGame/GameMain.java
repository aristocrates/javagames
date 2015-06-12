import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Calls everything to start the game
 * 
 * @author Nick Meyer
 * @version Beta
 */
public class GameMain
{
    private static JFrame frame, scoreFrame;
    public static Dimension screenSize;
    private static JPanel panel;
    private static JPanel startPane;
    public static boolean running = true, stopped=false;
    private static Thread a;
    public static double time;
    public static void main(String[] args) throws InterruptedException
    {
        a = new Thread();
        a.start();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("Gravitron");
        frame.setResizable(false);
        startPane = new StartPanel(500,500);
        panel = new MainPanel(500,500);
        frame.setSize(516,538);
        frame.setLocation(screenSize.width/2-250, screenSize.height/2-250);
        frame.setContentPane(startPane);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowListener() {
            public void windowClosed(WindowEvent e) {
            }
            public void windowActivated(WindowEvent e) {
                stopped = false;
                frame.getContentPane().requestFocusInWindow();
            }
            public void windowClosing(WindowEvent e) {
                running = false;
                a.stop();
                System.exit(0);
            }
            public void windowDeactivated(WindowEvent e) {
                stopped = true;
                scoreFrame.setVisible(true);
            }
            public void windowDeiconified(WindowEvent e) {
            }
            public void windowIconified(WindowEvent e) {
            }
            public void windowOpened(WindowEvent e) {
            }
        });
        frame.setVisible(true);
        time = 0;
        scoreFrame = new MainPanel.ScorePanel(frame);
        while (running)
        {
            if (frame.getContentPane().equals(panel))
            {
                while (stopped)
                {
                    a.sleep(100);
                }
                a.sleep(30);
                time += 0.03;
                ((MainPanel)panel).update(time);
            }
            else
            {
                a.sleep(500);
                ((MainPanel.ScorePanel)scoreFrame).resetContent();
            }
        }
    }
    public static JFrame frame()
    {
        return frame;
    }
    public static JPanel panel()
    {
        return panel;
    }
    public static JFrame scoreFrame()
    {
        return scoreFrame;
    }
    public static JPanel startPane()
    {
        return startPane;
    }
    public static void kill(JFrame f) {
        WindowEvent wev = new WindowEvent(f, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }
}
