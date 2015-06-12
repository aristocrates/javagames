package BasicStructure;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
/**
 * For space missions
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class StarrySky implements Background
{
    private int width, height;
    private Image background;
    private ArrayList<Star> stars;
    private Color starColor, backgroundColor;

    public StarrySky(int width, int height, Color backgroundColor, Color starColor)
    {
        this(width,height,starColor);
        background = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics temp = background.getGraphics();
        temp.setColor(this.backgroundColor = backgroundColor);
        temp.fillRect(0,0,background.getWidth(null),background.getHeight(null));
    }
    
    public StarrySky(int width, int height, Image bckg, Color starColor)
    {
        this(width,height,starColor);
        background = bckg;
    }
    
    private StarrySky(int width, int height, Color starColor)
    {
        this.width = width;
        this.height = height;
        this.starColor = starColor;
        this.backgroundColor = backgroundColor;
        
        stars = new ArrayList<Star>();
        randomlyGenerateStars();
    }
    
    public void randomlyGenerateStars()
    {
        int max = 700;
        int goUpTo = (int)((1.0-Math.pow(Math.random(),9))*700);
        if (goUpTo < max/2)
            goUpTo = max/2;
        for (int i = 0; i < goUpTo; i++)
        {
            double choose = Math.random();
            int depth = 2;
            double prob = 1.0;
            for (int i2 = depth; i2 > 0; i2--)
            {
                prob /= Math.pow(2,depth-i2+2);
                if (choose < prob)
                    depth = i2-1;
            }
            stars.add(new Star(depth));
        }
        Collections.sort(stars);
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }

    public Image getImage()
    {
        Image returnImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics editor = returnImage.getGraphics();
        editor.drawImage(background,0,0,width,height,null);
        for (Star star : stars)
            star.drawMe(editor);
        return returnImage;
    }

    public void update()
    {
        for (Star star : stars)
            star.update();
    }

    private class Star implements Comparable<Star>
    {
        private int depth;
        private double xcoord, ycoord;
        
        /**
         * Initializes the star with a random location
         * 
         * @param depth  int value between 0 and 2 (inclusive) where 0 is closest to the
         * screen
         */
        public Star(int depth)
        {
            this.depth = depth%3+4;
            xcoord = (int)(Math.random()*width);
            ycoord = (int)(Math.random()*height);
        }
        
        public int compareTo(Star other)
        {
            return other.depth - this.depth;
        }

        /**
         * Moves the star to the left by an amount determined by the depth variable
         */
        public void update()
        {
            xcoord -= 1.0/(depth-3);
            if (depth == 4)
                xcoord += .2;
            ycoord += .5/(depth-3);
            if (xcoord <= 0)
                xcoord = width;
            if (ycoord >= height)
                ycoord = 0;
        }

        /**
         * Color of the star is determined by StarrySky's starColor parameter.
         * Size is determined by depth.
         */
        public void drawMe(Graphics g)
        {
            int size = 25-3*(depth+1);
            Image draw = new BufferedImage(size,size,BufferedImage.TYPE_INT_ARGB);
            Graphics editor = draw.getGraphics();
            editor.setColor(starColor);
            editor.fillOval(0,0,size,size);
            g.drawImage(draw,(int)xcoord,(int)ycoord,size,size,null);
        }
    }
}