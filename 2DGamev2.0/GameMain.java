import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class GameMain here.
 * 
 * @author Nick Meyer
 * @version 2.0
 */
public class GameMain implements GamePane
{
    private static Dimension screenSize;
    private static MusicPlayer music;
    public static void main(String[] args)
    {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        music = new MusicPlayer();
        music.playSound("test.wav");
        EndGame e = new EndGame();
    }
    public static class EndGame extends JFrame
    {
        public EndGame()
        {
            setResizable(false);
            setUndecorated(true);
            setSize(120,120);
            setLocation((int)screenSize.getWidth()/2-50,(int)screenSize.getHeight()/2-25);
            final JFrame reference = this;
            setContentPane(new JPanel(){
                {
                    setSize(50,50);
                    setLayout(null);
                    final ActionListener closeIfYesPressed = new ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                            if (e.getActionCommand().equals("Exit"))
                                System.exit(0);
                            reference.hide();
                        }
                    };
                    add(new JButton("Y"){
                        {
                            setBounds(10,50,45,25);
                            setActionCommand("Exit");
                            addActionListener(closeIfYesPressed);
                        }
                    });
                    add(new JButton("N"){
                        {
                            setBounds(60,50,45,25);
                            setActionCommand("Cancel");
                            addActionListener(closeIfYesPressed);
                        }
                    });
                }
                public void paintComponent(Graphics g)
                {
                    g.drawRect(0,0,getWidth(),getHeight());
                    g.drawString("Are you sure?",20,10);
                }
            });
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            setVisible(true);
        }
    }
}