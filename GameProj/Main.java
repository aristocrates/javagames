import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
/**
 * Contains the main method that runs the game as a whole, delegates game tasks
 * to different threads
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class Main
{
    private static JFrame gameFrame;
    private static GamePanel p;
    private static boolean paused;
    private static LinkedList<World> levels;
    private static World current;
    private static int on;
    private static Dimension screensize;
    private static Account acc;
    private static final int GAME_WIDTH = 805, GAME_HEIGHT = 624;
    private static Thread painter, imageUpdater, componentUpdater;

    /**
     * Starts up the game
     */
    public static void main(String[] args)
    {
        acc = new Account();
        resetLevels();
        screensize = Toolkit.getDefaultToolkit().getScreenSize();
        gameFrame = new JFrame("testframe");
        gameFrame.setSize(GAME_WIDTH,GAME_HEIGHT);
        gameFrame.setLocation((int)(screensize.getWidth()-GAME_WIDTH)/2,
            (int)(screensize.getHeight()-GAME_HEIGHT)/2);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Sound.playMusic();
        setToMenuPanel();
    }

    /**
     * Sets the game to menu mode
     */
    public static void setToMenuPanel()
    {
        gameFrame.setContentPane(new MenuPanel(800,600));
        gameFrame.setVisible(true);
    }

    /**
     * starts the levels
     */
    public static void startGame()
    {
        p = new GamePanel(800,600);
        gameFrame.setContentPane(p);
        gameFrame.setVisible(true);

        loadNext();
        startThreads();
    }

    /**
     * resets levels so they can be played through again
     */
    public static void resetLevels()
    {
        levels = new LinkedList<World>();
        levels.add(new IntroWorld(800,600,new ResourceManager(5000,500)));
        for (int i = 0; i < 3; i++)
        {
            /*if (i == 2)
                levels.add(new SurvivalWorld(800,600,
                new ResourceManager(5000,500),2));*/
            levels.add(new PlanetWorld(i+1,900,677,
                    new ResourceManager(5000,500)));
        }
    }

    /**
     * moves to next level and records a win
     */
    public static void winLevel()
    {
        loadNext();
        acc.addWin();
    }

    /**
     * resets the current level and records a loss
     */
    public static void loseLevel()
    {
        levels.addFirst(current);
        acc.addLoss();
        loadNext();
    }

    /**
     * @return the account object, mainly for the userinterface
     */
    public static Account getAccount()
    {
        return acc;
    }

    /**
     * starts the game's threads
     */
    public static void startThreads()
    {
        imageUpdater = new GameThread(){
            public void run()
            {
                while (true)
                {
                    if (paused)
                    {
                        try {
                            Thread.sleep(100);
                        }
                        catch (InterruptedException e) {}
                    }
                    else
                    {
                        if (p.getWorld() != null)
                            p.getWorld().updateBackground();
                        try{
                            Thread.sleep(50);
                        }
                        catch (InterruptedException e) {}
                    }
                }
            }
        }.getThread();
        componentUpdater = new GameThread(){
            public void run()
            {
                while (true)
                {
                    if (paused)
                    {
                        try {
                            Thread.sleep(100);
                        }
                        catch (InterruptedException e) {}
                    }
                    else
                    {
                        if (p.getWorld() != null)
                            p.getWorld().updateComponents();
                        try{
                            Thread.sleep(50);
                        }
                        catch (InterruptedException e) {}
                    }
                }
            }
        }.getThread();
        painter = new GameThread(){
            public void run()
            {
                while (true)
                {
                    p.repaint();
                    try{
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e) {}
                }
            }
        }.getThread();
        painter.start();
        imageUpdater.start();
        componentUpdater.start();
    }

    /**
     * Loads the next level
     */
    public static void loadNext()
    {
        try {
            current = levels.remove().newClone();
        }
        catch (NoSuchElementException e){
            resetLevels();
            setToMenuPanel();
        }
        p.loadWorld(current);
        Time.setToTimeZero();
        p.repaint();
    }

    /**
     * If the game is already paused, does nothing
     */
    public static void pauseGame()
    {
        if (paused)
            return;
        paused = true;
        Time.pause();
    }

    /**
     * @return a boolean representing whether or not the game is paused
     */
    public static boolean paused()
    {
        return paused;
    }

    /**
     * If the game is not paused, does nothing
     */
    public static void resumeGame()
    {
        if (!paused)
            return;
        paused = false;
        Time.resume();
    }
}