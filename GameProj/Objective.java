/**
 * Used to encapsulate conditions for 'failing' or 'passing' (the strict
 * meaning of these terms varies with the context it is used in)
 * Useful for the class to be checking these values in each render
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public interface Objective
{
    public boolean complete();
    public boolean fail();
}