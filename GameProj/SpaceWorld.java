import java.awt.*;
public class SpaceWorld extends World
{
    public SpaceWorld(int width, int height, ResourceManager m)
    {
        super(new StarrySky(1600,1200,Color.black,Color.white),width,height,m);
    }
}