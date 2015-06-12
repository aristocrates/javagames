package world;

import java.awt.*;
import java.awt.image.*;
import otherStuff.*;
/**
 * Write a description of class Callout here.
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public class Callout extends GameComponent
{
    private final String text;
    private static final FontMetrics fontMetrics;
    private static Font displayFont;
    private GameComponent source;
    private int height, width;
    
    /**
     * Constructs a somewhat "1.33:1" aspect ratio callout
     */
    public Callout(GameComponent g, String text, boolean pointed)
    {
        this(g,text,(int)(4*Math.sqrt(getTextArea(text)/12.0)));
    }
    
    public Callout(GameComponent g, String text, int width)
    {
        this.width = width;
        source = g;
        this.text = splitString(text);
    }
    
    public void setNewSource(GameComponent g)
    {
        source = g;
    }
    
    public GameComponent clone()
    {
        return new Callout(source,text,width);
    }
    
    private static void drawLinebreakString(Graphics g,String text,int x,int y)
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
            if (fontMetrics.stringWidth(current+" ") > width)
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
    
    public Shape getDimensions()
    {
        return null;
    }

    public void drawMe(Graphics g)
    {
        
    }
    
    public void update(GameEvent e)
    {
        //if (e.equals(GameEvent.disappear()))
    }
    
    private static int getTextArea(String text)
    {
        return fontMetrics.getHeight()*fontMetrics.stringWidth(text);
    }
    
    public boolean loopable()
    {
        return source.loopable();
    }

    static {
        displayFont = new Font("Courier",Font.PLAIN,12);
        Graphics genericGraphics = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB).getGraphics();
        genericGraphics.setFont(displayFont);
        fontMetrics = genericGraphics.getFontMetrics();
    }
}