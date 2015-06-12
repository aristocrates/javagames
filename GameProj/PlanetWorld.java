import java.util.*;
public class PlanetWorld extends World
{
    private ArrayList<EnemyTower> tows;
    private Tower mustDefend;
    private int type;
    private static final int PLANET_RADIUS = 248;
    private static final Coordinate CENTER_TOWER = new Coordinate(658,418);

    /**
     * Makes a new PlanetWorld
     */
    public PlanetWorld(int type, int width, int height, ResourceManager m)
    {
        super(new PlanetInvasion(type),width,height,m);
        this.type = type;
        tows = new ArrayList<EnemyTower>();
        addEnemy(towerAt(CENTER_TOWER.clone()));
        for (int i = 0; i < 3*type; i++)
            addEnemy(towerAt(new Coordinate(CENTER_TOWER,PLANET_RADIUS,i*360/type)));
        addTower(mustDefend=new EnergyCollector(new Coordinate(100,500), this));
        setObjective(new Objective(){
                public boolean complete()
                {
                    return tows.size() == 0;
                }

                public boolean fail()
                {
                    return mustDefend != null && mustDefend.removed();
                }
            });
        addTextBubble(new TextBubble("Objective: destroy all EnemyTowers",
        200,new Coordinate(450,300), new StandardTimeObjective(0,8)));
    }
    
    /**
     * Step-wise refinement to reduce codelength for creating PlanetWorld-
     * specific EnemyTowers
     */
    private EnemyTower towerAt(Coordinate c)
    {
        return new EnemyTower(c,2000*type,25,this);
    }

    /**
     * Overrides World's
     */
    public World newClone()
    {
        return new PlanetWorld(type,getWidth(),getHeight(),resourceManager());
    }

    /**
     * Compares equality
     */
    public boolean equals(Object other)
    {
        if (!(other instanceof PlanetWorld))
            return false;
        PlanetWorld candidate = (PlanetWorld)other;
        return type == candidate.type;
    }

    /**
     * Overrides to store EnemyTowers in tows
     */
    public void addEnemy(Enemy e)
    {
        super.addEnemy(e);
        if (e instanceof EnemyTower)
            tows.add((EnemyTower)e);
    }

    /**
     * Overrides to remove EnemyTowers from tows
     */
    public void removeEnemy(Enemy e)
    {
        super.removeEnemy(e);
        if (e instanceof EnemyTower)
            tows.remove(e);
    }
}