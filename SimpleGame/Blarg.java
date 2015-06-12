import world.*;
import java.awt.event.*;
import java.awt.*;
import otherStuff.*;
/**
 * <p>There is only one Blarg in a game and making the game accomodate more than one Blarg
 * would contradict the principles of the game. For all of you who want to mess around
 * with my code, I don't care, it's all garbage anyway (as you can probably see by comments
 * like this one and the FREE_WILL class) and I pretty much ripped off of other games 
 * for all my ideas, but if you construct more than one Blarg at a time, I will officially 
 * shake my fist in anger.</p>
 * 
 * <p>I also made Blarg implement keylistener so I can just directly change his instance
 * fields</p>
 * 
 * <p>credit for idea to name him blarg:
 * <a href = "http://www.springhole.net/writing_roleplaying_randomators//90saliennames.htm">
 * http://www.springhole.net/writing_roleplaying_randomators//90saliennames.htm</a></p>
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public class Blarg extends GameComponent implements KeyListener
{
    private int screenX, screenY;
    private int experience;
    private Quest currentQuest;
    private Image blarg, spaceship;
    private UserSkills skills;
    
    public Blarg()
    {
        loadImages();
    }
    
    public void update(GameEvent e)
    {
    }
    
    private void loadImages()
    {
        
    }
    
    public boolean loopable()
    {
        return false;
    }
    
    public int getWidth()
    {
        return 0;
    }
    
    public void drawMe(Graphics g)
    {
    }
    
    public void setLocation(Point p)
    {
        screenX = (int)p.getX();
        screenY = (int)p.getY();
    }

    public void keyReleased(KeyEvent e){
    }
    
    public void keyPressed(KeyEvent e){
    
    }
    
    /**
     * At the time of writing this I don't really have any use for keyTyped, so it's just
     * sitting here doing nothing and taking up space in my source code
     */
    public void keyTyped(KeyEvent e){}
}