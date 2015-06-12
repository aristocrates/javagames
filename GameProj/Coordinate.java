/**
 * Encapsulates coordinate points in a two dimensional cartesian plane
 * 
 * @author Matthew Pak, Sydney Desai, Nick Meyer
 * @version 1.0
 */
public class Coordinate
{
    private int xValue;
    private int yValue;

    /**
     * Constructs a coordinate with the given x and y arguments
     */
    public Coordinate(int x, int y)
    {
        xValue = x;
        yValue = y;
    }
    
    /**
     * Constructs a coordinate the given distance away from the given coordinate
     * at angle theta
     */
    public Coordinate(Coordinate c, double dist, int theta)
    {
        this((int)(c.getX()+dist*Math.cos(angleConverter(theta,true)*180/Math.PI)),(int)(c.getY()+dist*Math.sin(angleConverter(theta,true)*180/Math.PI)));
    }
    
    /**
     * @return a new Coordinate object identical to this
     */
    public Coordinate clone()
    {
        return new Coordinate(xValue,yValue);
    }

    /**
     * Modifies the coordinate to have the newly specified x and y values
     */
    public void setPoint(int x, int y)
    {
        xValue = x;
        yValue = y;
    }

    /**
     * @return the x value
     */
    public int getX()
    {
        return xValue;
    }

    /**
     * @return the y value
     */
    public int getY()
    {
        return yValue;
    }

    /**
     * returns the angle (in degrees) formed by the y axis and the ray
     * connecting this coordinate and the other (the same way as gridworld)
     */
    public int angleToward(Coordinate other)
    {
        int ans = (int)(Math.atan2(other.getY()-getY(),other.getX()-getX())*180/Math.PI);
        return angleConverter(ans,false);
    }

    /**
     * if toReal, converts game notation to real notation, otherwise real notation
     * to game notation
     */
    public static int angleConverter(int angle, boolean toReal)
    {
        int ans = angle;
        if (toReal)
        {
            if (ans > 90)
                ans -= 450;
            ans += 90;
            if (ans < 0)
                ans += 360;
        }
        else
        {
            ans = 90-ans;
            if (ans < 0)
                ans += 360;
            ans = 180 - ans;
            if (ans < 0)
                ans += 360;
        }
        return ans;
    }

    /**
     * @return the distance between this and the specified coordinate
     */
    public double distanceTo(Coordinate c)
    {
        int x1 = c.getX();
        int y1 = c.getY();
        return Math.pow(Math.pow(xValue-x1,2)+Math.pow(yValue-y1,2),0.5);
    }

    /**
     * @return true if the 
     */
    public boolean equals(Object o)
    {
        if(!(o instanceof Coordinate))
            return false;
        Coordinate c = (Coordinate)o;
        if(c.getX() == xValue && c.getY() == yValue)
        {
            return true;
        }
        return false;
    }

    /**
     * 
     */
    public int hashCode()
    {
        return xValue*10000 + yValue;
    }

    /**
     * @return String representation of this object
     */
    public String toString()
    {
        return "("+xValue+","+yValue+")";
    }
}
