package BasicStructure;
/**
 * Write a description of class Coordinate here.
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class Coordinate
{
    private int x, y;
    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
    
    public int hashCode()
    {
        return x/70 * 1000 + y/50;
    }
    
    public boolean equals(Object o)
    {
        return o instanceof Coordinate && hashCode() == o.hashCode();
    }
    
    public double distanceTo(Coordinate other)
    {
        return Math.sqrt(Math.pow((this.x - other.x), 2) + 
                         Math.pow((this.y - other.y), 2));
    }
}