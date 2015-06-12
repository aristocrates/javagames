package BasicStructure;

import java.awt.*;
/**
 * The background
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public interface Background
{
    public Image getImage();
    public void update();
    public int getWidth();
    public int getHeight();
}