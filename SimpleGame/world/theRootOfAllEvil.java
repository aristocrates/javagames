package world;
import otherStuff.*;
import world.*;
/**
 * Superclass for all evil things in the game. All subclasses of theRootOfAllEvil must be
 * directly harmful to Blarg. For example, if 
 * 
 * @author Aristocrates, barbecue chef / j̶a̶r̶g̶o̶n̶ ̶s̶p̶o̶u̶t̶i̶n̶g̶ ̶m̶a̶n̶i̶a̶c̶ 
 * part time philosopher
 * @version sin(π/2)
 */
public abstract class theRootOfAllEvil extends GameComponent
{
    /**
     * @return the amount of damage to Blarg's life done
     */
    public abstract int damageDone();
    
    public boolean loopable()
    {
        return true;
    }
}