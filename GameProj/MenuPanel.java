import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class MenuPanel here.
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class MenuPanel extends JPanel implements ActionListener
{
    private Background backG;
    private Image gameName;
    
    public MenuPanel(int width, int height)
    {
        setLayout(null);
        backG = new StarrySky(width,height,new Color(50,50,50),Color.white);
        gameName = new ImageIcon(getClass().getResource("gameName.png")).getImage();
        
        JButton current;
        add(current = new JButton("Start"));
        current.addActionListener(this);
        current.setBounds(300,400,200,100);
        current.setActionCommand("start");
    }
    
    public void paintComponent(Graphics g)
    {
        g.drawImage(backG.getImage(),0,0,backG.getWidth(),backG.getHeight(),null);
        g.drawImage(gameName,5,5,gameName.getWidth(null),gameName.getHeight(null),null);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("start"))
            Main.startGame();
    }
}