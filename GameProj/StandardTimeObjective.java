
/**
 * Most useful for 
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class StandardTimeObjective implements Objective
{
    private double start, finish;
    public StandardTimeObjective(double start, double duration)
    {
        this.start = start;
        this.finish = start+duration;
    }

    public boolean fail()
    {
        return Time.getTime()/1000 > finish;
    }
    
    public boolean complete()
    {
        return Time.getTime()/1000 > start;
    }
}
