
/**
 * Write a description of class SurvivalWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SurvivalWorld extends SpaceWorld
{
    private int minutes;
    public SurvivalWorld(int width, int height, ResourceManager m, int minutes)
    {
        super(width,height,m);
        this.minutes = minutes;
        addTower(new EnergyCollector(new Coordinate(300,300),this));
        setObjective(new StandardTimeObjective(minutes*60,0){
                public boolean fail()
                {
                    return getAllTowers()!= null && getAllTowers().size() == 0;
                }
            });
        addTextBubble(new TextBubble("Objective: survive for "+minutes+" minutes",
                210,new Coordinate(300,100), new StandardTimeObjective(5,3)));
        for (int i = 0; i < minutes*5; i++)
            new Wave((int)(Math.random()*4),this,i*2+5,i/5.0*60).addToWorld();
    }

    public World newClone()
    {
        return new SurvivalWorld(getWidth(),getHeight(),resourceManager(),
            minutes);
    }
}
