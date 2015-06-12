import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
/**
 * Write a description of class MainPanel here.
 * 
 * @author Nick Meyer
 * @version Beta
 */
public class MainPanel extends GamePanel
{
    public JPanel startUpPanel;
    public int ballX, ballY;
    public int numStars;
    public double theTime;
    private BufferedImage backImage;
    private Graphics init;
    public int current = 0;
    private int[] directionFactor = {0,0};
    private Component[] extrasCopy;
    private Component[] extras;
    public int currentStar;
    private int score = 0;
    private boolean door;
    private boolean[] pressed = new boolean[4];
    private int[] doorCoord = {450,50};
    public MainPanel(){super(1,1);}
    public MainPanel(int x1, int y1)
    {
        super(x1, y1);
        setLayout(null);
        door = false;
        backImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        init = backImage.getGraphics();
        init.setColor(Color.white);
        init.fillRect(0,0,x,y);
        drawBackground(init);
        ((StartPanel)GameMain.startPane()).lPanel.gameImage = backImage;
        ballX = ballY = 245;
        buffer.setColor(Color.black);
        buffer.fillOval(ballX,ballY,10,10);
        startUpPanel = new JPanel(){
            {                
                addMouseListener(new MouseListener(){
                    public void mouseExited(MouseEvent e){}
                    public void mouseEntered(MouseEvent e){}
                    public void mouseReleased(MouseEvent e){}
                    public void mousePressed(MouseEvent e){
                        startUpPanel.setVisible(false);
                        GameMain.stopped = false;
                    }
                    public void mouseClicked(MouseEvent e){}
                });
                setVisible(false);
            }
            public void paintComponent(Graphics g)
            {
                Color c = g.getColor();
                g.setColor(Color.white);
                g.fillRect(0,0,100,50);
                g.setColor(c);
            }
        };
        this.add(startUpPanel);
        startUpPanel.setBounds(200,225,100,50);
    }
    public void loadComponents(Component[] e, int xD, int yD)
    {
        GameMain.scoreFrame().setVisible(true);
        doorCoord[0] = xD;
        doorCoord[1] = yD;
        extras = new Component[e.length];
        extrasCopy = new Component[e.length];
        for (int i = 0; i < extras.length; i++)
        {
            extras[i] = e[i];
            extrasCopy[i] = e[i].clone();
        }
        if (extras[0].type==0)
            currentStar = 1;
        boolean[] pressed1 = {false,false,false,false};
        for (int i = 0; i < 4; i++)
            pressed[i] = pressed1[i];
    }
    public void drawBackground(Graphics init)
    {
        for (int i = 0; i < 8; i++)
        {
            init.setColor(new Color(216-20*i,216-20*i,216-20*i));
            init.fillOval(10+30*i,10+30*i,480-60*i,480-60*i);
        }
    }
    public double formula(int x, int y)
    {
        return Math.pow((250-x)*(250-x)+(250-y)*(250-y),0.5)/30+1;
    }
    public void paintComponent(Graphics g)
    {
        buffer.drawImage(backImage, 0, 0, 500, 500, null);
        for (int i = 0; i < extras.length; i++)
            extras[i].draw(buffer);
        buffer.setColor(Color.black);
        buffer.fillOval(ballX,ballY,10,10);
        if (door) buffer.drawImage((new ImageIcon("images\\door.jpg")).getImage(),doorCoord[0],doorCoord[1],28,43,null);
        if (GameMain.stopped)
        {
            buffer.setColor(new Color(100,100,255,100));
            buffer.fillRect(0,0,getWidth(),getHeight());
        }
        super.paintComponent(g);
    }
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key < 41 && key > 36)
        {
            pressed[key-37] = false;
            if ((key&1)==1)
            {
                if (pressed[0])
                {
                    directionFactor[0] = -1;
                    return;
                }
                if (pressed[2])
                {
                    directionFactor[0] = 1;
                    return;
                }
                directionFactor[0] = 0;
            }
            else
            {
                if (pressed[1])
                {
                    directionFactor[1] = -1;
                    return;
                }
                if (pressed[3])
                {
                    directionFactor[1] = 1;
                    return;
                }
                directionFactor[1] = 0;
            }
        }
    }
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        switch (key) {
            case 37:
            directionFactor[0] = -1;
            break;
            case 39:
            directionFactor[0] = 1;
            break;
            case 38:
            directionFactor[1] = -1;
            break;
            case 40:
            directionFactor[1] = 1;
        }
        if (key > 36 && key < 41)
            pressed[key-37] = true;
    }
    public void update(double time)
    {
        theTime = time;
        double c = formula(ballX, ballY);
        ((ScorePanel)GameMain.scoreFrame()).update(score);
        if (door && within(ballX,ballY,doorCoord[0],doorCoord[1],28,43))
        {
            System.out.println(score);
            GameMain.frame().setContentPane(new StartPanel.LevelPanel(500,500));
            GameMain.frame().setVisible(true);
            GameMain.frame().getContentPane().requestFocusInWindow();
            ballX = 250;
            ballY = 250;
            GameMain.time = 0;
            score = 0;
            door = false;
            for (int i = 0; i < 2; i++)
                directionFactor[i] = 0;
            GameMain.scoreFrame().setVisible(false);
            return;
        }
        if (pathIsClear(c))
        {
            ballX+=(int)c*directionFactor[0];
            ballY+=(int)c*directionFactor[1];
        }
        else
            while (pathIsClear(2))
            {
                ballX+=directionFactor[0];
                ballY+=directionFactor[1];
            }
        if (ballX<=0) ballX=499;
        if (ballX>=500) ballX=1;
        if (ballY<=0) ballY=499;
        if (ballY>=500) ballY=1;
        for (int i = 0; i < extras.length; i++)
        {
            current = i;
            eventHandler(extras[i].update(ballX+5,ballY+5,time));
        }
        door = true;
        int i = 0;
        while (i<extras.length&&extras[i].type==0)
        {
            if (!extras[i].shutDown) door = false;
            i++;
        }
        repaint();
    }
    public boolean within(int x, int y, int upLeftX, int upLeftY, int width, int height)
    {
        return (x+5)<(upLeftX+width) && (x+5)>upLeftX && (y+5)<(upLeftY+height) && (y+5)>upLeftY;
    }
    public void keyTyped(KeyEvent e){}
    public void eventHandler(int e)
    {
        if (e==0) return;
        if (e==1) killAndRestart();
        if (e==2)
        {
            score+=disabledStar().score;
            if (numStars(currentStar)==0) currentStar++;
        }
    }
    public void killAndRestart()
    {
        score = 0;
        directionFactor[0] = 0;
        directionFactor[1] = 0;
        GameMain.time = 0;
        door = false;
        ballX=250;
        ballY=250;
        currentStar=1;
        int i = -1;
        loadComponents(extrasCopy,doorCoord[0],doorCoord[1]);
        GameMain.stopped = true;
        startUpPanel.setVisible(true);
    }
    public Star disabledStar()
    {
        int i = 0;
        while (extras[i].type==0)
        {
            if (extras[i].disabled&&!extras[i].shutDown)
            {
                extras[i].disabled = false;
                extras[i].shutDown = true;
                return (Star)extras[i];
            }
            i++;
        }
        return null;
    }
    public int numStars(int Order)
    {
        int ans = 0;
        int i = 0;
        while (i<extras.length&&extras[i].type==0&&extras[i].order<=Order)
        {
            if (extras[i].order==Order&&!extras[i].shutDown) ans++;
            i++;
        }
        return ans;
    }
    public boolean pathIsClear(double c)
    {
        for (int i = 0; i < extras.length; i++)
            if (extras[i].type==1)
            {
                Wall a = (Wall)extras[i];
                if (a.deadly == false)
                {
                    double[] xy = {(ballX+5)+c*directionFactor[0],(ballY+5)+c*directionFactor[1]};
                    double slope = (ballY+5-xy[1])/(ballX+5-xy[0]);
                    if (Math.abs(slope)>1000) slope = 1000;
                    double b = (ballY+5)-slope*(ballX+5);
                    for (int i2 = 0; i2 < 4; i2++)
                    {
                        if (i2 < 2)
                        {
                            double ans = slope*a.walls()[i2]+b;
                            if (ans >= a.walls()[2] && ans <= a.walls()[3] && distanceFormula(ballX+5,ballY+5,a.walls()[i2],ans)<=distanceFormula(ballX+5,ballY+5,xy[0],xy[1]) && distanceFormula(xy[0],xy[1],a.walls()[i2],ans)<=distanceFormula(xy[0],xy[1],ballX+5,ballY+5))
                                return false;
                        }
                        else
                        {
                            double ans = (a.walls()[i2]-b)/slope;
                            if (ans >= a.walls()[0] && ans <= a.walls()[1] && distanceFormula(ballX+5,ballY+5,ans,a.walls()[i2])<=distanceFormula(ballX+5,ballY+5,xy[0],xy[1]) && distanceFormula(xy[0],xy[1],ans,a.walls()[i2])<=distanceFormula(xy[0],xy[1],ballX+5,ballY+5))
                                return false;
                        }
                    }
                }
            }
        return true;
    }
    public static double distanceFormula(double x1, double y1, double x2, double y2)
    {
        return Math.pow((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2),0.5);
    }
    public static class ScorePanel extends JFrame
    {
        private int displayScore;
        JPanel content;
        public ScorePanel(JFrame f)
        {
            setLocation((int)f.getLocation().getX()+520,(int)f.getLocation().getY());
            setSize(100,100);
            resetContent();
        }
        public void resetContent()
        {
            displayScore = 0;
            content = new JPanel(){
                {
                    setSize(100,100);
                }
                public void paintComponent(Graphics g)
                {
                    System.out.println(displayScore);
                    g.setFont(new Font("Arial",Font.PLAIN,32));
                    g.setColor(Color.white);
                    g.fillRect(0,0,getWidth(),getHeight());
                    g.setColor(Color.black);
                    g.drawString(displayScore+"",20,50);
                }
            };
            setContentPane(content);
        }
        public void update(int sc)
        {
            if (displayScore!=sc)
            {
                System.out.println(sc);
                displayScore = sc;
                content.repaint();
            }
        }
    }
}