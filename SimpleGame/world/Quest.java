package world;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import otherStuff.*;
/**
 * Write a description of class Quest here.
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public class Quest implements Constants
{
    private Background backg;
    private QuestPanel panel;
    
    /**
     * Constructs a quest with a specified background and GameComponents
     */
    public Quest(Background background, ArrayList<GameComponent> components,
                 int length, int rate)
    {
        backg = background;
    }
    
    public Image getImage()
    {
        BufferedImage draw = new BufferedImage(GAME_WIDTH,GAME_HEIGHT,BufferedImage.TYPE_INT_RGB);
        Graphics drawer = draw.getGraphics();
        drawer.drawImage(backg.getImage(),0,0,draw.getWidth(),draw.getHeight(),null);
        return draw;
    }
    
    public JPanel getPanel()
    {
        if (panel == null)
            panel = new QuestPanel(GAME_WIDTH,GAME_HEIGHT,this);
        return panel;
    }
}
