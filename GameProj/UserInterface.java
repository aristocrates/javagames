import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Provides the interface for creating towers, pausing the game
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class UserInterface extends JPanel implements MouseListener, ActionListener
{
    private int width, height;
    private boolean expanded;
    private boolean bombMode;
    private GamePanel gme;
    private ArrayList<JButton> towerSelectors;
    private ArrayList<JPanel> otherDisplays;

    /**
     * Makes a new UserInterface
     */
    public UserInterface(GamePanel gme)
    {
        setLayout(null);

        this.gme = gme;
        this.width = gme.getWidth();
        this.height = gme.getHeight();
        int currentWidth = width/15;
        expanded = false;
        bombMode = false;
        setBounds(width-currentWidth,0,currentWidth,height);

        addMouseListener(this);
        setOpaque(false);

        towerSelectors = new ArrayList<JButton>();
        addTowerChoices();
        otherDisplays = new ArrayList<JPanel>();
        JPanel current;
        otherDisplays.add(current = new JPanel(){
                public void paintComponent(Graphics g)
                {
                    g.setColor(new Color(255,255,255,150));
                    g.fillRect(0,0,getWidth(),getHeight());
                }
            });
        current.setBounds(0,0,100,50);
        current.setLayout(null);
        JButton currButton;
        current.add(currButton = new JButton(new Icon(){
                    private Image pause, play;
                    
                    {
                        pause = new ImageIcon(getClass().getResource("pauseButton.png")).getImage();
                        play = new ImageIcon(getClass().getResource("playButton.png")).getImage();
                    }
                    
                    public void paintIcon(Component c, Graphics g, int x, int y)
                    {
                        g.drawImage(getImage(),x,y,getIconWidth(),getIconHeight(),null);
                    }
            
                    public Image getImage()
                    {
                        return Main.paused()?play:pause;
                    }
                    
                    public int getIconWidth()
                    {
                        return getImage().getWidth(null);
                    }
                    
                    public int getIconHeight()
                    {
                        return getImage().getHeight(null);
                    }
                }){
            });
        currButton.addActionListener(this);
        currButton.setBounds(0,0,100,50);
        
        final UserInterface tHis = this;
        otherDisplays.add(current = new JPanel(){
                public void paintComponent(Graphics g)
                {
                    g.setColor(new Color(255,255,255,150));
                    g.fillRect(0,0,getWidth(),getHeight());
                    String resources = "metal pieces:"+tHis.gme.getWorld().resourceManager().getMetalPieces()+
                    "  num people:"+tHis.gme.getWorld().resourceManager().getNumPeople()+
                    Main.getAccount().toString();
                    g.setColor(Color.black);
                    g.drawString(resources,30,30);
                }
            });
        current.setBounds(0,gme.getHeight()-50,400,50);
    }

    /**
     * Adds all panels of the UserInterface to the JPanel
     */
    public void addSelfTo(JPanel p)
    {
        p.add(this);
        for (JPanel j : otherDisplays)
            p.add(j);
    }

    /**
     * adds tower select buttons
     */
    private void addTowerChoices()
    {
        towerSelectors.add(new JButton(new EnergyCollector(new Coordinate(70,70),null)));
        towerSelectors.add(new JButton(new AttackTower(new Coordinate(70,150),null)));
        towerSelectors.add(new JButton(new Linker(new Coordinate(70,230),null)));
        for (int i = 0; i < towerSelectors.size(); i++)
        {
            JButton j = towerSelectors.get(i);
            ((Tower)j.getIcon()).activate();
            add(j);
            j.setVisible(false);
            j.addActionListener(gme);
            j.setActionCommand(j.getIcon().toString());
            towerSelectors.get(i).setBounds(45,50+i*100,50,50);
        }
        JButton b = new JButton(new Icon(){
            Image img;
            {
                img = new ImageIcon(getClass().getResource("x.png")).getImage();
            }
            public void paintIcon(Component c, Graphics g, int x, int y)
            {
                g.drawImage(img,x,y,getIconWidth(),getIconHeight(),null);
            }
            
            public int getIconWidth()
            {
                return img.getWidth(null);
            }
            
            public int getIconHeight()
            {
                return img.getHeight(null);
            }
        });
        towerSelectors.add(b);
        b.setActionCommand("Cancel");
        b.setVisible(false);
        b.addActionListener(gme);
        add(b);
        b.setBounds(45,400,50,50);
    }

    /**
     * shrinks right display back down
     */
    public void mouseExited(MouseEvent e)
    {
        if (e.getX() <= getWidth() && e.getX() >= 0
        && e.getY() >= 0 && e.getY() <= getHeight())
            return;
        expanded = false;
        int currentWidth = width/15;
        setBounds(width-currentWidth,0,currentWidth,height);
        for (JButton t : towerSelectors)
            t.setVisible(false);
    }

    /**
     * paints the user interface
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(250,250,250,200));
        g.fillRect(0,0,getWidth(),getHeight());
        if (expanded)
            for (JButton t : towerSelectors)
                t.setVisible(true);
    }

    /**
     * Handles the code to make the sidebar "pop out" when scrolled over
     */
    public void mouseEntered(MouseEvent e)
    {
        if (gme.scrolling() || e.getX() > getWidth() || e.getX() < 0
        || e.getY() < 0 || e.getY() > getHeight())
            return;
        expanded = true;
        int currentWidth = 3*width/20;
        setBounds(width-currentWidth,0,currentWidth,height);
    }

    /**
     * Does nothing (needed to implement MouseListener)
     */
    public void mouseReleased(MouseEvent e)
    {
    }

    /**
     * Does nothing (needed to implement MouseListener)
     */
    public void mousePressed(MouseEvent e)
    {
    }

    /**
     * Does nothing (needed to implement MouseListener)
     */
    public void mouseClicked(MouseEvent e)
    {
    }
    
    /**
     * for the pause button
     */
    public void actionPerformed(ActionEvent e)
    {
        if (Main.paused())
            Main.resumeGame();
        else
            Main.pauseGame();
    }
}