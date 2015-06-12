package world;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
/**
 * Write a description of class Panel here.
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public class QuestPanel extends JPanel
{
    private Quest quest;
    private int width, height;
    public QuestPanel(int width, int height, Quest quest)
    {
        this.quest = quest;
        this.width = width;
        this.height = height;
    }
    
    public void paintComponent(Graphics g)
    {
        g.drawImage(quest.getImage(),0,0,getWidth(),getHeight(),null);
    }
}