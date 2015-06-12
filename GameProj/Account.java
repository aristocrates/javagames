import java.util.*;
/**
 * Stores number of wins, number of losses, num
 * 
 * @author Sydney Desai 
 * @version 1.0
 */
public class Account
{
    private int numWins;
    private int numLosses;
    private Score gameScore;
    private ArrayList<World> unlockedWorlds;
    
    /**
     * Makes a new Account object with no wins or losses and an empty score
     * Also initializes unlockedWorlds as an empty list
     */
    public Account()
    {
        numWins = 0;
        numLosses = 0;
        gameScore = new Score();
        unlockedWorlds = new ArrayList<World>();
    }
    
    /**
     * Increases the number of wins by 1
     */
    public void addWin()
    {
        numWins++;
    }
    
    /**
     * Increases the number of losses by 1
     */
    public void addLoss()
    {
        numLosses++;
    }
    
    /**
     * @return the number of wins
     */
    public int getNumWins()
    {
        return numWins;
    }
    
    /**
     * @return the number of losses
     */
    public int getNumLosses()
    {
        return numLosses;
    }
    
    /**
     * @return this account's GameScore object
     */
    public Score getGameScore()
    {
        return gameScore;
    }
    
    /**
     * Iterates through unlockedWorlds and checks (using .equals) if w is
     * in unlockedWorlds
     * 
     * @return true if the world is in the ArrayList unlockedWorlds
     */
    public boolean canPlay(World w)
    {
        for(int x=0; x<unlockedWorlds.size(); x++)
        {
            if(unlockedWorlds.get(x).equals(w))
               return true;
            
        }
        return false;
    }
    
    /**
     * Adds the specified world to unlockedWorlds
     */
    public void unlockWorld(World w)
    {
        unlockedWorlds.add(w);
    }
    
    /**
     * String representation
     */
    public String toString()
    {
        return "wins:"+numWins+" losses:"+numLosses+" score:"+gameScore.getScore();
    }
}