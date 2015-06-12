import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
/**
 * Write a description of class LevelBuilderPanel here.
 * 
 * @author Nick Meyer
 * @version Beta
 */
public class LevelBuilderPanel extends GamePanel
{
    Image gameImage, componentEditorImage, mainImage, miscImage;
    ArrayList<Star> stars;
    ArrayList<Wall> walls;
    ArrayList<DeathStar> deathstars;
    ArrayList<Bomb> bombs;
    ArrayList<Teleporter> portals;
    ArrayList<MyFrame> frames = new ArrayList<MyFrame>();
    boolean paintComponents = false;
    public LevelBuilderPanel(int x1, int y1)
    {
        super(x1, y1);
        stars = new ArrayList<Star>();
        walls = new ArrayList<Wall>();
        deathstars = new ArrayList<DeathStar>();
        bombs = new ArrayList<Bomb>();
        portals = new ArrayList<Teleporter>();
    }
    public Component[] comp()
    {
        int[] i = new int[5];
        Component[] ans = new Component[stars.size()+walls.size()+portals.size()+bombs.size()+deathstars.size()];
        for (i[0] = 0; i[0] < stars.size(); i[0]++)
            ans[i[0]+i[1]+i[2]+i[3]+i[4]] = stars.get(i[0]);
        for (i[1] = 0; i[1] < walls.size(); i[1]++)
            ans[i[0]+i[1]+i[2]+i[3]+i[4]] = walls.get(i[1]);
        for (i[2] = 0; i[2] < deathstars.size(); i[2]++)
            ans[i[0]+i[1]+i[2]+i[3]+i[4]] = deathstars.get(i[2]);
        for (i[3] = 0; i[3] < bombs.size(); i[3]++)
            ans[i[0]+i[1]+i[2]+i[3]+i[4]] = bombs.get(i[3]);
        for (i[4] = 0; i[4] < portals.size(); i[4]++)
            ans[i[0]+i[1]+i[2]+i[3]+i[4]] = portals.get(i[4]);
        return ans;
    }
    public void paintComponent(Graphics g)
    {
        buffer.drawImage(gameImage,0,0,400,400,null);
        buffer.setColor(Color.black);
        buffer.fillRect(400,0,5,500);
        buffer.fillRect(0,400,500,5);
        for (int i = 1; i <= 4; i++)
            buffer.fillRect(400,i*80,100,5);
        for (int i = 1; i <= 3; i++)
            buffer.fillRect(i*100,400,5,100);
        if (stars.size()+walls.size()+portals.size()+bombs.size()+deathstars.size()>0) paintComponents = true;
        else paintComponents = false;
        if (paintComponents)
        {
            Component[] a = this.comp();
            for (int i = 0; i < a.length; i++)
            {
                a[i].demo = true;
                a[i].draw(buffer);
            }
        }
        super.paintComponent(g);
    }
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){
        ((StartPanel)GameMain.startPane()).mouseMoved(e);
        int X = e.getX();
        int Y = e.getY();
        if (Y>400)
        {
            if (X>400)
            {
                stars = new ArrayList<Star>();
                walls = new ArrayList<Wall>();
                deathstars = new ArrayList<DeathStar>();
                bombs = new ArrayList<Bomb>();
                portals = new ArrayList<Teleporter>();
                GameMain.frame().setContentPane(GameMain.startPane());
                GameMain.frame().setVisible(true);
                GameMain.frame().getContentPane().requestFocusInWindow();
            }
            else
            {
            }
        }
        else
        {
            if (X>400)
            {
                JFrame frame;
                if (Y<80)
                {
                    frame = new MyFrame("Star Creator");
                    frame.setContentPane(new StarPanel());
                }
                else if (Y<160)
                {
                    frame = new MyFrame("Wall Creator");
                    frame.setContentPane(new WallPanel());
                }
                else if (Y<240)
                {
                    frame = new MyFrame("Death Star Creator");
                    frame.setContentPane(new DeathStarPanel());
                }
                else if (Y<320)
                {
                    frame = new MyFrame("Bomb Creator");
                    frame.setContentPane(new BombPanel());
                }
                else
                {
                    frame = new MyFrame("Teleporter Creator");
                    frame.setContentPane(new TeleporterPanel());
                }
                frames.add((MyFrame)frame);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                final int index = frames.size()-1;
                frame.addWindowListener(new WindowListener() {
                    public void windowClosed(WindowEvent e) {}
                    public void windowActivated(WindowEvent e) {}
                    public void windowClosing(WindowEvent e) {
                        Component temp = ((ComponentPanel)(frames.get(index)).getContentPane()).getComponent();
                        switch (temp.type)
                        {
                            case 0: stars.add((Star)temp); break;
                            case 1: walls.add((Wall)temp); break;
                            case 2: deathstars.add((DeathStar)temp); break;
                            case 3: bombs.add((Bomb)temp); break;
                            case 4: portals.add((Teleporter)temp); break;
                        }
                    }
                    public void windowDeactivated(WindowEvent e) {}
                    public void windowDeiconified(WindowEvent e) {}
                    public void windowIconified(WindowEvent e) {}
                    public void windowOpened(WindowEvent e) {}
                });
                frame.setSize(316,338);
                frame.setLocation(GameMain.screenSize.width*3/4,GameMain.screenSize.height/6);
                frame.setVisible(true);
            }
        }
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e){
        repaint();
    }
    public void mouseDragged(MouseEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyPressed(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public abstract class ComponentPanel extends JPanel implements ActionListener
    {
        final int BUTTON_HEIGHT = 15, BUTTON_WIDTH = 20;
        NumberEnterPanel[] parameterEnterers;
        BufferedImage i;
        Graphics buffer;
        String check;
        double[] param;
        JButton[][] buttons;
        int type;
        public ComponentPanel()
        {
            this.setLayout(null);
            i = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
            buffer = i.getGraphics();
        }
        public void paintInit()
        {
            buffer.drawImage((new ImageIcon("images\\builder"+type+".jpg")).getImage(),0,0,300,300,null);
        }
        public void paintComponent(Graphics g)
        {
            g.drawImage(i,0,0,getWidth(),getHeight(),null);
        }
        public abstract Component getComponent();
        public void actionPerformed(ActionEvent e)
        {
            check = e.getActionCommand();
        }
    }
    public class StarPanel extends ComponentPanel
    {
        public StarPanel()
        {
            super();
            type = 0;
            double[] q = {0,0,0};
            param = new double[q.length];
            for (int i = 0; i < q.length; i++)
                param[i] = q[i];
            buttons = new JButton[3][2];
            buttons[0][0] = new JButton();
            buttons[0][0].setActionCommand("u0");
            buttons[0][0].setBounds(110,135,BUTTON_WIDTH,BUTTON_HEIGHT);
            this.add(buttons[0][0]);
            buttons[0][1] = new JButton();
            buttons[0][1].setActionCommand("d0");
            buttons[0][1].setBounds(110,155,BUTTON_WIDTH,BUTTON_HEIGHT);
            this.add(buttons[0][1]);
            NumberEnterPanel p = new NumberEnterPanel(20,20,20,20){
                public boolean accept(String s)
                {
                    return false;
                }
                public void buttonUp()
                {
                }
                public void buttonDown()
                {
                }
            };
            p.addMe(this);
        }
        public void paintComponent(Graphics g)
        {
            paintInit();
            super.paintComponent(g);
        }
        public Component getComponent()
        {
            return new Star((int)param[0],(int)param[1],(int)param[2]);
        }
        public void actionPerformed(ActionEvent e)
        {
            super.actionPerformed(e);
            if (check.charAt(0) == 85)
            {
                param[0]+=1;
            }
            else
            {
            }
        }
    }
    public class WallPanel extends ComponentPanel
    {
        public WallPanel()
        {
            super();
            type = 1;
            double[] q = {0,0,0,0};
            param = new double[q.length];
            for (int i = 0; i < q.length; i++)
                param[i] = q[i];
            buttons = new JButton[4][2];
        }
        public void paintComponent(Graphics g)
        {
            paintInit();
            super.paintComponent(g);
        }
        public Component getComponent()
        {
            return new Wall((int)param[0],(int)param[1],(param[2]!=0),(param[3]!=0));
        }
        public void actionPerformed(ActionEvent e)
        {
            super.actionPerformed(e);
        }
    }
    public class DeathStarPanel extends ComponentPanel
    {
        public DeathStarPanel()
        {
            super();
            type = 2;
            double[] q = {0,0,0,0};
            param = new double[q.length];
            for (int i = 0; i < q.length; i++)
                param[i] = q[i];
            buttons = new JButton[4][2];
        }
        public void paintComponent(Graphics g)
        {
            paintInit();
            super.paintComponent(g);
        }
        public Component getComponent()
        {
            return new DeathStar((int)param[0],(int)param[1],(int)param[2],(int)param[3]);
        }
        public void actionPerformed(ActionEvent e)
        {
            super.actionPerformed(e);
        }
    }
    public class BombPanel extends ComponentPanel
    {
        public BombPanel()
        {
            super();
            type = 3;
            double[] q = {0,0,0,0,0};
            param = new double[q.length];
            for (int i = 0; i < q.length; i++)
                param[i] = q[i];
            buttons = new JButton[5][2];
        }
        public void paintComponent(Graphics g)
        {
            paintInit();
            super.paintComponent(g);
        }
        public Component getComponent()
        {
            return new Bomb((int)param[0],(int)param[1],param[2],param[3],(int)param[4]);
        }
        public void actionPerformed(ActionEvent e)
        {
            super.actionPerformed(e);
        }
    }
    public class TeleporterPanel extends ComponentPanel
    {
        public TeleporterPanel()
        {
            super();
            type = 4;
            double[] q = {0,0,0,0};
            param = new double[q.length];
            for (int i = 0; i < q.length; i++)
                param[i] = q[i];
            buttons = new JButton[4][2];
        }
        public void paintComponent(Graphics g)
        {
            paintInit();
            super.paintComponent(g);
        }
        public Component getComponent()
        {
            return new Teleporter((int)param[0],(int)param[1],(int)param[2],(int)param[3]);
        }
        public void actionPerformed(ActionEvent e)
        {
            super.actionPerformed(e);
        }
    }
    public static class MyFrame extends JFrame
    {
        public static boolean on = true;
        public MyFrame(String s)
        {
            super(s);
            addWindowListener(new WindowListener(){
                public void windowDeactivated(WindowEvent e){}
                public void windowActivated(WindowEvent e){}
                public void windowDeiconified(WindowEvent e){}
                public void windowIconified(WindowEvent e){}
                public void windowClosed(WindowEvent e){}
                public void windowClosing(WindowEvent e){
                    ((StartPanel)GameMain.startPane()).lPanel.repaint();
                }
                public void windowOpened(WindowEvent e){}
            });
        }
        public void update()
        {
            GameMain.kill(this);
        }
    }
    public static abstract class NumberEnterPanel extends JPanel implements MouseListener, KeyListener
    {
        private int x,y,width,length;
        private BufferedImage img, backGroundColor;
        private Graphics buffer;
        private String showMe = "0", showStore = "", actual = "0";
        public NumberEnterPanel(int cX, int cY, int cWidth, int cLength)
        {
            setFocusable(true);
            addKeyListener(this);
            addMouseListener(this);
            
            x = cX;
            y = cY;
            width = cWidth;
            length = cLength;
            
            backGroundColor = new BufferedImage(width,length,BufferedImage.TYPE_INT_RGB);
            buffer = backGroundColor.getGraphics();
            buffer.setColor(Color.white);
            buffer.fillRect(0,0,width,length);
            
            img = new BufferedImage(width,length,BufferedImage.TYPE_INT_RGB);
            buffer = img.getGraphics();
        }
        public void setString(String s)
        {
            actual = ""+s;
            showMe = ""+actual;
        }
        public void addMe(JPanel j)
        {
            j.add(this);
            this.setBounds(x,y,width,length);
        }
        public void paintComponent(Graphics g)
        {
            buffer.drawImage(backGroundColor,0,0,width,length,null);
            buffer.setColor(Color.black);
            buffer.drawString(showMe,0,10);
            g.drawImage(img,0,0,width,length,null);
        }
        public void keyReleased(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {
            char i = e.getKeyChar();
            showStore += ""+i;
            showMe = ""+showStore;
            repaint();
        }
        public void mouseExited(MouseEvent e) {
            if (accept(showStore))
                setString(showStore);
            else
                setString(actual);
            repaint();
        }
        public void mouseEntered(MouseEvent e) {
            showStore = "";
            requestFocusInWindow();
        }
        public void mouseReleased(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public abstract boolean accept(String s);
        public abstract void buttonUp();
        public abstract void buttonDown();
    }
}