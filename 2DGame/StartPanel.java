import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
/**
 * Write a description of class StartPanel here.
 * 
 * @author Nick Meyer
 * @version Beta
 */
public class StartPanel extends GamePanel
{
    public int X, Y;
    public LevelBuilderPanel lPanel = new LevelBuilderPanel(500,500);
    private ImageIcon image2;
    Color c1, c2, c3, c4;
    boolean play, levelEditor, instructions, credits;
    public StartPanel(int x1, int y1)
    {
        super(x1, y1);
        play = false;
        levelEditor = false;
        instructions = false;
        credits = false;
        image2 = new ImageIcon("images\\Title.png");
        c1 = new Color(120,11,185,150);
        c2 = new Color(120,11,185,80);
        c3 = new Color(41,173,18,150);
        c4 = new Color(41,173,18,80);
        requestFocusInWindow();
    }
    public void paintComponent(Graphics g)
    {
        buffer.drawImage(image2.getImage(),0,0,500,500,null);
        if (play) drawRects(g,340,115,c3,c4);
        else drawRects(g,340,115,c1,c2);
        if (levelEditor) drawRects(g,340,210,c3,c4);
        else drawRects(g,340,210,c1,c2);
        if (instructions) drawRects(g,340,305,c3,c4);
        else drawRects(g,340,305,c1,c2);
        if (credits) drawRects(g,340,400,c3,c4);
        else drawRects(g,340,400,c1,c2);
        buffer.setColor(Color.black);
        buffer.setFont(new Font("Arial",Font.PLAIN,20));
        buffer.drawString("Play",360,150);
        buffer.drawString("Level Editor",360,245);
        buffer.drawString("Instructions",360,340);
        buffer.drawString("Credits",360,435);
        super.paintComponent(g);
    }
    public void drawRects(Graphics g, int x1, int y1, Color i1, Color i2)
    {
        buffer.setColor(i1);
        buffer.fillRect(x1,y1,150,10);
        buffer.fillRect(x1,y1+65,150,10);
        buffer.fillRect(x1,y1+10,10,55);
        buffer.fillRect(x1+140,y1+10,10,55);
        buffer.setColor(i2);
        buffer.fillRect(x1+10,y1+10,140,65);
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
        X = e.getX();
        Y = e.getY();
        if (X>340&&X<490)
        {
            if (Y>115&&Y<190)
            {
                GameMain.frame().setContentPane(new LevelPanel(500,500));
                GameMain.frame().setVisible(true);
                GameMain.frame().getContentPane().requestFocusInWindow();
            }
            if (Y>210&&Y<285)
            {
                GameMain.frame().setContentPane(lPanel);
                GameMain.frame().setVisible(true);
                GameMain.frame().getContentPane().requestFocusInWindow();
            }
            if (Y>305&&Y<380)
            {
                GameMain.frame().setContentPane(new InstructionsPanel(500,500));
                GameMain.frame().setVisible(true);
                GameMain.frame().getContentPane().requestFocusInWindow();
            }
            if (Y>400&&Y<475)
            {
                GameMain.frame().setContentPane(new CreditsPanel(500,500));
                GameMain.frame().setVisible(true);
                GameMain.frame().getContentPane().requestFocusInWindow();
            }
        }
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseMoved(MouseEvent e)
    {
        X = e.getX();
        Y = e.getY();
        play = false;
        levelEditor = false;
        instructions = false;
        credits = false;
        if (X>340&&X<490)
        {
            if (Y>115&&Y<190)
                play = true;
            if (Y>210&&Y<285)
                levelEditor = true;
            if (Y>305&&Y<380)
                instructions = true;
            if (Y>400&&Y<475)
                credits = true;
        }
        repaint();
    }
    public void mouseDragged(MouseEvent e)
    {
        X = e.getX();
        Y = e.getY();
        play = false;
        levelEditor = false;
        instructions = false;
        credits = false;
        if (X>340&&X<490)
        {
            if (Y>115&&Y<190)
                play = true;
            if (Y>210&&Y<285)
                levelEditor = true;
            if (Y>305&&Y<380)
                instructions = true;
            if (Y>400&&Y<475)
                credits = true;
        }
        repaint();
    }
    public void keyReleased(KeyEvent e)
    {
    }
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode()==KeyEvent.VK_TAB)
        {
        }
    }
    public void keyTyped(KeyEvent e)
    {
    }
    public class CreditsPanel extends GamePanel
    {
        public CreditsPanel(int x1, int y1)
        {
            super(x1, y1);
        }
        public void paintComponent(Graphics g)
        {
            buffer.drawImage((new ImageIcon("images\\credits.png")).getImage(),0,0,500,500,null);
            super.paintComponent(g);
        }
        public void mouseExited(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mousePressed(MouseEvent e){
            ((StartPanel)GameMain.startPane()).mouseMoved(e);
            GameMain.frame().setContentPane(GameMain.startPane());
            GameMain.frame().getContentPane().requestFocusInWindow();
        }
        public void mouseClicked(MouseEvent e){}
        public void mouseMoved(MouseEvent e){}
        public void mouseDragged(MouseEvent e){}
        public void keyReleased(KeyEvent e){}
        public void keyPressed(KeyEvent e){}
        public void keyTyped(KeyEvent e){}
    }
    public static class LevelPanel extends GamePanel
    {
        private static Color color1;
        private static Color color2;
        int total;
        int rows;
        int[][] upperLeftCorners;
        int whichLevel = -1;
        ArrayList<LevelButton> levelChoose;
        public LevelPanel(int x1, int y1)
        {
            super(x1, y1);
            color1 = new Color((int)(Math.random()*1000)%256,(int)(Math.random()*1000)%256,(int)(Math.random()*1000)%256);
            color2 = new Color((int)(Math.random()*1000)%256,(int)(Math.random()*1000)%256,(int)(Math.random()*1000)%256);
            levelChoose = new ArrayList<LevelButton>();
            buffer.drawImage((new ImageIcon("images\\Title.png")).getImage(),0,0,500,500,null);
            File dir = new File("levels");
            String[] levelNames = dir.list(filter);
            total = levelNames.length;
            upperLeftCorners = new int[total][2];
            rows = total/3;
            if (total%3!=0) rows++;
            int rowSeparation = (int)((500-50*rows)/(rows+1));
            for (int i = 0; i < rows; i++)
            {
                int columns = 3;
                int rowHeight = 50*i+rowSeparation*(i+1);
                if (i == (rows - 1))
                    columns = total - 3*i;
                for (int i2 = 0; i2 < columns; i2++)
                {
                    int add = 3*i;
                    if (rows == 1)
                        add = 0;
                    upperLeftCorners[add+i2][1] = rowHeight;
                    upperLeftCorners[add+i2][0] = 100*i2+(int)((500-100*columns)/(columns+1))*(i2+1);
                }
            }
            String[] levelsNoExtension = new String[levelNames.length];
            for (int i = 0; i < total; i++)
            {
                levelsNoExtension[i] = levelNames[i].substring(0,levelNames[i].length()-4);
                levelChoose.add(new LevelButton(i,upperLeftCorners[i][0],upperLeftCorners[i][1],levelsNoExtension[i],this));
            }
            setFocusable(true);
            requestFocusInWindow();
        }
        public void load(String s)
        {
            ReadFile.setReadInput("levels\\"+s,GameMain.panel());
            stuff();
        }
        public class LevelButton extends GamePanel{
            private JPanel levelPanel;
            private String name;
            public int myNum;
            public LevelButton(int i, int xLeftCorner, int yLeftCorner, String fileName, JPanel p)
            {
                super(100,50);
                myNum = i;
                name = ""+fileName;
                levelPanel = p;
                p.add(this);
                setBounds(xLeftCorner,yLeftCorner,100,50);
                buffer.setColor(color1);
                setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
            }
            public void paintComponent(Graphics g)
            {
                buffer.fillRect(0,0,150,50);
                buffer.setColor(Color.black);
                buffer.drawString(name,10,24);
                super.paintComponent(g);
            }
            public void mouseExited(MouseEvent e) {
                unFocus();
            }
            public void mouseEntered(MouseEvent e) {
                makeFocused();
            }
            public void makeFocused()
            {
                if (whichLevel!=-1)
                {
                    levelChoose.get(whichLevel).unFocus();
                    whichLevel = myNum;
                }
                buffer.setColor(color2);
                repaint();
                requestFocusInWindow();
            }
            public void unFocus()
            {
                buffer.setColor(color1);
                repaint();
                levelPanel.requestFocusInWindow();
            }
            public void mouseReleased(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {
                ((LevelPanel)levelPanel).load(name+".glv");
            }
            public void mouseClicked(MouseEvent e) {}
            public void mouseMoved(MouseEvent e) {}
            public void mouseDragged(MouseEvent e) {}
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_TAB||e.getKeyCode()==8)
                    ((LevelPanel)levelPanel).keyPressed(e);
                if (e.getKeyCode()==10)
                {
                    ((LevelPanel)levelPanel).load(name+".glv");
                }
            }
            public void keyTyped(KeyEvent e) {}
        }
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name)
            {
                int i = name.length();
                String s = name.substring(i-3);
                return s.equals("glv");
            }
        };
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
        }
        public void mouseExited(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void stuff()
        {
            GameMain.frame().setContentPane(GameMain.panel());
            GameMain.frame().setVisible(true);
            GameMain.frame().getContentPane().requestFocusInWindow();
        }
        public void mouseClicked(MouseEvent e){}
        public void mouseMoved(MouseEvent e){}
        public void mouseDragged(MouseEvent e){}
        public void keyReleased(KeyEvent e){}
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode()==KeyEvent.VK_TAB)
            {
                if (whichLevel!=-1)
                    levelChoose.get(whichLevel).unFocus();
                whichLevel++;
                whichLevel%=levelChoose.size();
                levelChoose.get(whichLevel).makeFocused();
            }
            if (e.getKeyCode()==8)
                GameMain.frame().setContentPane(GameMain.startPane());
        }
        public void keyTyped(KeyEvent e){}
    }
    public class InstructionsPanel extends GamePanel
    {
        public InstructionsPanel(int x1, int y1)
        {
            super(x1, y1);
        }
        public void mouseExited(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseClicked(MouseEvent e){}
        public void mouseMoved(MouseEvent e){}
        public void mouseDragged(MouseEvent e){}
        public void keyReleased(KeyEvent e){}
        public void keyPressed(KeyEvent e){}
        public void keyTyped(KeyEvent e){}
    }
}