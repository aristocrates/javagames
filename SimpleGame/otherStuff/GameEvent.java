package otherStuff;


/**
 * <p>Simple class designed for communication among components of the game and the game 
 * progressor</p>
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public class GameEvent
{
    public static final GameEvent CARRY_ON;
    public static final GameEvent COLLISION;

    static {
        CARRY_ON = new GameEvent("carryOn","");
        COLLISION = new GameEvent("collide","");
    }

    private String type, command;
    
    private GameEvent(String type, String command)
    {
        this.type = type;
        this.command = command;
    }
    
    public GameEvent(GameEvent type, String command)
    {
        this.type = type.type;
        this.command = command;
    }
    
    public String toString()
    {
        return Math.random()+"";
    }
    
    public boolean equals(Object o)
    {
        if (!(o instanceof GameEvent))
            return false;
        return type.equals(((GameEvent)o).type);
    }
}