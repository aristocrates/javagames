package world;
import java.awt.*;
/**
 * It is recommended that seperate threads are used to update the Background and
 * paint it's image.
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public interface Background
{
    public Image getImage();
    public void update();
}