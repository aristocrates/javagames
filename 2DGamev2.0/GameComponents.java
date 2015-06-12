import java.awt.*;
/**
 * Write a description of class GameComponents here.
 * 
 * @author Nick Meyer
 * @version 2.0
 */
public interface GameComponents
{
    public final static int EVENT_NO_ACTION = 0;
    public final static int EVENT_DIE = 1;
    public final static int EVENT_TELEPORT = 2;
    public final static int EVENT_COLLECT_STAR = 3;
    public class ObstacleEvent
    {
        private int eventType;
        public GameObstacle sender;
        public ObstacleEvent(int eventType, GameObstacle object)
        {
            this.eventType = eventType;
            sender = object;
        }
        public int eventType()
        {
            return eventType;
        }
    }
    public abstract class GameObstacle
    {
        int x, y;
        public GameObstacle(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        public abstract ObstacleEvent update(int time);
        public abstract void draw(Graphics g);
        public abstract void hide();
    }
    public class Star extends GameObstacle
    {
        public Star(int x, int y)
        {
            super(x,y);
        }
        public ObstacleEvent update(int time)
        {
            return new ObstacleEvent(EVENT_NO_ACTION,this);
        }
        public void draw(Graphics g)
        {
        }
        public void hide()
        {
        }
    }
    public class Wall extends GameObstacle
    {
        public Wall(int x, int y)
        {
            super(x,y);
        }
        public ObstacleEvent update(int time)
        {
            return new ObstacleEvent(EVENT_NO_ACTION,this);
        }
        public void draw(Graphics g)
        {
        }
        public void hide()
        {
        }
    }
    public class Bomb extends GameObstacle
    {
        public Bomb(int x, int y)
        {
            super(x,y);
        }
        public ObstacleEvent update(int time)
        {
            return new ObstacleEvent(EVENT_NO_ACTION,this);
        }
        public void draw(Graphics g)
        {
        }
        public void hide()
        {
        }
    }
    public class DeathStar extends GameObstacle
    {
        public DeathStar(int x, int y)
        {
            super(x,y);
        }
        public ObstacleEvent update(int time)
        {
            return new ObstacleEvent(EVENT_NO_ACTION,this);
        }
        public void draw(Graphics g)
        {
        }
        public void hide()
        {
        }
    }
    public class Teleporter extends GameObstacle
    {
        public Teleporter(int x, int y)
        {
            super(x,y);
        }
        public ObstacleEvent update(int time)
        {
            return new ObstacleEvent(EVENT_NO_ACTION,this);
        }
        public void draw(Graphics g)
        {
        }
        public void hide()
        {
        }
    }
}
