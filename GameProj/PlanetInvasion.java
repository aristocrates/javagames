import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
/**
 * Write a description of class PlanetInvasion here.
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class PlanetInvasion implements Background
{
    private Image planet, img;
    private Graphics editor;
    private int sunLocation;
    private static final int sunYCoord = 50, sunRadius = 25;
    
    public PlanetInvasion(int type)
    {
        planet = new ImageIcon(getClass().getResource("planet"+type+".gif")).getImage();
        sunLocation = -100;
        img = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
        editor = img.getGraphics();
    }
    
    public int getHeight()
    {
        return planet.getHeight(null);
    }
    
    public int getWidth()
    {
        return planet.getWidth(null);
    }
    
    public void update()
    {
        sunLocation += 1;
        if (sunLocation > getWidth() + 100)
            sunLocation = -100;
    }
    
    public Image getImage()
    {
        editor.drawImage(planet,0,0,getWidth(),getHeight(),null);
        editor.setColor(Color.yellow);
        editor.fillOval(sunLocation-sunRadius,sunYCoord-sunRadius,2*sunRadius,2*sunRadius);
        return img;
    }
}