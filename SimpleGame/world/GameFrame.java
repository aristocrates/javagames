package world;

import javax.swing.*;
import otherStuff.*;
/**
 * Write a description of class Frame here.
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public class GameFrame extends JFrame implements Constants
{
    
    public GameFrame(String s)
    {
        super(s);
        setSize(665,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}