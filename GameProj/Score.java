/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score
{
    private int score;
    
    /**
     * Make a new Score object with a score of 0
     */
    public Score()
    {
        score = 0;
    }
    
    /**
     * Adds to the score
     */
    public void addScore(int amnt)
    {
        score += amnt;
    }
    
    /**
     * @return the 
     */
    public int getScore()
    {
        return score;
    }
}