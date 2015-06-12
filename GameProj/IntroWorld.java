/**
 * Write a description of class IntroWorld here.
 * 
 * @author Nick Meyer
 * @version 1.0
 */
public class IntroWorld extends SpaceWorld implements Objective
{
    private Tower mustDefend;
    private Enemy mustDefeat;
    
    public IntroWorld(int width, int height, ResourceManager m)
    {
        super(width,height,m);
        setObjective(this);
        addTower(mustDefend = new EnergyCollector(new Coordinate(500,500), this));
        addTower(new Linker(new Coordinate(450,450),this));
        addTower(new AttackTower(new Coordinate(400,400),this));
        addTower(new Linker(new Coordinate(550,550),this));
        addTower(new AttackTower(new Coordinate(600,600),this));
        addEnemy(mustDefeat = new EnemyTower(new Coordinate(700,900),2000,20,this,15));
        addTextBubble(new TextBubble("Welcome",75,new Coordinate(100,100),
                new StandardTimeObjective(1,3)));
        addTextBubble(new TextBubble("Training program will commence shortly",
        210,new Coordinate(300,100), new StandardTimeObjective(5,3)));
        addTextBubble(new TextBubble("To zoom in or out, use the scroll on the mouse, the + and - keyboard buttons, or the PgUp and PgDown keyboard buttons",
        200,new Coordinate(450,300), new StandardTimeObjective(8,10)));
        addTextBubble(new TextBubble("To pan to different parts of the map, press the mouse on part of the screen and drag",
        200,new Coordinate(200,300), new StandardTimeObjective(8,10)));
        addTextBubble(new TextBubble("To pause the game, click the button to the left of this message. Click it again to unpause",
        200,new Coordinate(100,10), new StandardTimeObjective(19,6)));
        addTextBubble(new TextBubble("The orange circle and green triangles are examples of enemies. Your goal will be to destroy them before they destroy your towers",
        200,new Coordinate(450,300), new StandardTimeObjective(26,10)));
        addTextBubble(new TextBubble("Scroll the mouse over to the tab on the right to select a tower",
        200,new Coordinate(450,300), new StandardTimeObjective(37,4)));
        addTextBubble(new TextBubble("Energy collectors collect energy",
        200,new Coordinate(450,60), new StandardTimeObjective(42,8)));
        addTextBubble(new TextBubble("Attack Towers attack enemies",
        200,new Coordinate(450,150), new StandardTimeObjective(42,8)));
        addTextBubble(new TextBubble("Linkers are nice to link towers together",
        200,new Coordinate(450,250), new StandardTimeObjective(42,8)));
        addTextBubble(new TextBubble("Deselects the current tower type",
        200,new Coordinate(450,400), new StandardTimeObjective(42,8)));
        addTextBubble(new TextBubble("Now click on the screen where you want to place the tower",
        200,new Coordinate(450,300), new StandardTimeObjective(51,4)));
        addTextBubble(new TextBubble("You're on your own now, good luck! Your objective: destroy all (the only in this case) EnemeyTowers",
        200,new Coordinate(450,300), new StandardTimeObjective(55,10)));
    }
    
    /**
     * For reseting levels
     */
    public World newClone()
    {
        return new IntroWorld(getWidth(),getHeight(),resourceManager());
    }

    /**
     * @return true when player wins
     */
    public boolean complete()
    {
        return mustDefeat.removed();
    }

    /**
     * @return true when player loses
     */
    public boolean fail()
    {
        return mustDefend != null && mustDefend.removed();
    }
}
