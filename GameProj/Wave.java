import java.util.*;
/**
 * Write a description of class Wave here.
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class Wave
{
    private ArrayList<Enemy> e;
    public static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
    private static final String[] map = {"left","right","top","bottom"};
    private static final int[] dirAdj = {3,1,0,2};
    private TextBubble warning;
    private World w;
    
    /**
     * 
     */
    public Wave(int dir,World w,int numEnemies,double time)
    {
        Coordinate worldCenter = new Coordinate(w.getWidth()/2,w.getHeight()/2);
        this.w = w;
        dir = Math.abs(dir)%4;
        double dist = (dir<2)?w.getWidth():w.getHeight();
        Coordinate center = new Coordinate(worldCenter,dist/2,90*dirAdj[dir]);
        e = new ArrayList<Enemy>();
        for (int i = 0; i < numEnemies; i++)
        {
            int addX = (dir==UP||dir==DOWN)?0:(i-numEnemies/2)*4;
            int addY = (dir==LEFT||dir==RIGHT)?0:(i-numEnemies/2)*4;
            Coordinate c = new Coordinate(center.getX()+addX,
                                          center.getY()+addY);
            e.add(new Swarmer(c,w,c.angleToward(worldCenter),time));
        }
        warning = new TextBubble(numEnemies+" swarmers headed from the "
        +map[dir],210,new Coordinate(450,300), new StandardTimeObjective(time,10));
    }
    
    public void addToWorld()
    {
        for (Enemy en : e)
            w.addEnemy(en);
        w.addTextBubble(warning);
    }
}