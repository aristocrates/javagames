import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
/**
 * Write a description of class Callout here.
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class TextBubble
{
    private final String text;
    private static final FontMetrics fontMetrics;
    private static final Font displayFont;
    private int height, width;
    private Coordinate loc;
    private Objective c;
    
    public TextBubble(String text, int width, Coordinate loc, 
                      Objective c)
    {
        this.width = width;
        this.text = splitString(text);
        this.loc = loc;
        this.c = c;
    }
    
    /**
     * Draws the string so that it 
     */
    private static void drawLineBreakString(Graphics g,String text,int x,int y)
    {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
    
    /**
     * Returns the string "split" by \n characters into "lines" of width that
     * will fit on the width of the callout
     * Also sets the callout height
     */
    private String splitString(String orig)
    {
        String ans = "";
        String current = "";
        int lineHeight = fontMetrics.getHeight();
        for (String word : orig.split(" "))
        {
            if (fontMetrics.stringWidth(current+" "+word) > width)
            {
                ans += current + "\n";
                current = "";
                height += lineHeight;
            }
            current += word + " ";
        }
        if (!current.equals(""))
        {
            ans += current;
            height += lineHeight;
        }
        return ans;
    }

    /**
     * Draws the TextBubble onto the given Graphics object
     */
    public void drawMe(Graphics g)
    {
        if (!c.complete())
            return;
        if (c.fail())
            return;
        g.setFont(displayFont);
        g.setColor(new Color(255,255,255,150));
        g.fillRoundRect(loc.getX(),loc.getY(),width+5,height+5,10,10);
        g.setColor(Color.black);
        drawLineBreakString(g,text,loc.getX(),loc.getY());
    }
    
    /**
     * Currently, this will only ever be Arial size 15 as certain other fonts
     * tend to cause lag when loading the FontMetrics object
     * 
     * @return the font used by this TextBubble
     */
    public static Font getFont()
    {
        return displayFont;
    }

    static {
        displayFont = new Font("Arial",Font.PLAIN,17);
        Graphics generic = new BufferedImage(1,1,1).getGraphics();
        fontMetrics = generic.getFontMetrics(displayFont);
        generic.dispose();
    }
}