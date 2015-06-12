/**
 * Write a description of interface MathFormulas here.
 * 
 * @author Nick Meyer
 * @version 2.0
 */
public final class MathFormulas
{
    public final static double distanceFormula(double x1, double y1, double x2, double y2)
    {
        return Math.pow((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1),0.5);
    }
    public final static boolean pointWithin(double x, double y, double leftX, double topY, double width, double height)
    {
        return x>leftX&&x<(leftX+width)&&y>topY&&y<(topY+height);
    }
}
