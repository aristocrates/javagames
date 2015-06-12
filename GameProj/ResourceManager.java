/**
 * Write a description of class ResourceManager here.
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class ResourceManager
{
    //store the resources available
    private int metal;
    private int people;

    public ResourceManager(int amntMetal, int numPeople)
    {
        metal = amntMetal;
        people = numPeople;
    }

    public ResourceManager(ResourceManager m)
    {
        this(m.getMetalPieces(),m.getNumPeople());
    }

    public int getMetalPieces()
    {
        return metal;
    }

    public int getNumPeople()
    {
        return people;
    }

    public boolean canBuild(Tower t)
    {
        ResourceManager tow = t.cost();
        return getMetalPieces() >= tow.getMetalPieces() && 
        getNumPeople() >= tow.getNumPeople();
    }

    public boolean construct(Tower t)
    {
        if (!canBuild(t))
            return false;
        subtract(t.cost());
        return true;
    }

    public void subtract(ResourceManager r)
    {
        metal -= r.getMetalPieces();
        people -= r.getNumPeople();
    }
}