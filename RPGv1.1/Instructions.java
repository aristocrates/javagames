
/**
 * Provides the instructions for the driver.
 * 
 * @author Nick Meyer
 * @version 1.0 11/11/2010
 */
public class Instructions
{
    /**This gives the coordinates of the monsters, where the first [] is the y coordinate and
     * the second [] is the x coordinate. The numbers represent how many monsters there are
     * on each corner.
     */
    private int[][] monsterArray = {{0,1,0,1,1,1,0,0,2,0}, //First
                                    {1,0,1,0,1,0,1,1,1,1}, //Second
                                    {1,1,3,4,10,1,2,3,1,1},//...
                                    {0,0,0,2,5,1,0,2,0,1},
                                    {1,0,0,1,0,2,1,1,0,1},
                                    {1,1,1,1,1,1,1,1,1,1},
                                    {2,3,1,3,4,1,5,1,7,1},
                                    {0,2,0,4,1,0,5,1,0,1},
                                    {1,0,1,0,0,1,0,1,0,0},
                                    {0,0,1,0,1,0,0,1,0,25}}; //Tenth
    private boolean[][] townArray = {{false,false,false,false,false,false,false,false,true,false},
                                     {false,false,false,true,false,false,false,false,false,false},
                                     {false,false,false,false,false,false,false,false,false,false},
                                     {false,false,false,false,false,false,true,false,false,false},
                                     {true,false,false,false,false,false,false,false,false,false},
                                     {false,false,false,true,false,false,false,false,false,true},
                                     {},
                                     {},
                                     {},
                                     {}};
    double maxYValue = 9;
    double maxXValue = 9;
    /**FOR REFERENCE:
    * The order for the array is {level,experience,gold,endurance,mana,intelligence,strength,dexterity,yCoordinate,xCoordinate}
    */
    private double[] characterSkillArray = {1,0,2500,15,15,0,0,0,0,0};
    private double experienceNeeded = 10;
    /**FOR REFERENCE:
    * The order for the array is {endurance,stamina,gained experience,base gold reward}
    */
    private double[] monsterSkillArray = {10,10,10,1000};
    private double monsterLevel = 1;
    private String[] weaponArray = new String[8];
    private int weaponLevel = 0;
    private double[] weaponBaseDamageArray = new double[8];
    private double[] weaponUpgradeCostArray = {-1000,2500,5000,10000,20000,50000,100000,250000,999999999};
    private String[] armorArray = new String[8];
    private int armorLevel = 0;
    private double[] armorBaseBlockArray = new double[8];
    private double[] armorUpgradeCostArray = {-1000,2500,5000,10000,20000,50000,100000,250000,999999999};
    private String input;
    private double RAM;
    private int RAM2;
    private String weaponName;
    private String armorName;
    private String Class;
    private String specialAttack;
    private String charName;
/****************************************************************************************************************************************************************************************************
*****************************************************************************************************************************************************************************************************
*****************************************************************************************************************************************************************************************************
*****************************************************************************************************************************************************************************************************
*****************************************************************************************************************************************************************************************************
*****************************************************************************************************************************************************************************************************
*****************************************************************************************************************************************************************************************************
*****************************************************************************************************************************************************************************************************
*****************************************************************************************************************************************************************************************************
*****************************************************************************************************************************************************************************************************
*****************************************************************************************************************************************************************************************************
*****************************************************************************************************************************************************************************************************
*/
    public void playGame()
    {
        System.out.println("Would you like to play a game?");
        playGameType();
    }
    /************************************************************************************************************************************************************************************************
     */
    public void playGameType()
    {
        input = TextIO.getlnString();
        if (yes())
        {
            System.out.println("I thought you would want to.");
            restOfGame();
        }
        else
        {
            if (input.equalsIgnoreCase("no"))
            {
                System.out.println("Hmm. Fine. Be that way Get out of here.");
            }
            else
            {
                System.out.println("I can't understand what you said. Please retype");
                playGameType();
            }
        }
    }
    /************************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     */
    public void restOfGame()
    {
        System.out.println("The object of this game is to defeat all the monsters");
        System.out.print("(press enter to continue)");
        input = TextIO.getlnString();
        System.out.println("First, you must choose your class.");
        System.out.println("Do you want to be a mage, rogue, or warrior?");
        mageRogueOrWarrior();
        setSkills();
        input = TextIO.getlnString();
        System.out.println("Hmm. I never asked for your name. How rude of me.");
        name();
        input = TextIO.getlnString();
        buyThings();
        System.out.println("Now, before I let you go off and explore on your own,");
        System.out.print("you need to know some basics");
        input = TextIO.getlnString();
        System.out.println("You are currently on a coordinate grid identical to the first quadrant");
        System.out.println("(in case you aren't a math nerd, that means where x and y are positive)");
        
        while (!isGameOver())
        {
            if (nextToAMonster())
            {
                battle();
            }
            if (isAtHomeSquare())
            {
                System.out.println("Do you want to buy any upgrades?");
                input = TextIO.getlnString();
                if (yes())
                {
                    buyThings();
                }
            }
            System.out.println("Do you want to move?");
            input = TextIO.getlnString();
            if (yes())
            {
                move();
                moveEnd();
            }
            else
            {
                System.out.print("OK, you decide to stay on this coordinate.");
                input = TextIO.getlnString();
            }
        }
        for (int x = 1; x <= 50; x++)
        {
            System.out.println("");
        }
        System.out.println("CONGRATULATIONS!!!!!! You have beaten the game!");
    }
    /************************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     */
    public void move()
    {
        System.out.println("Type 'up' to move North, 'down' to move South, 'left' to move West, and 'right' to move east.");
        System.out.println("You are currently at (" + characterSkillArray[9] + "," + characterSkillArray[8] + ")");
        System.out.println("NOTE: PLEASE SPELL EVERYTHING CORRECTLY. CAPITALIZATION DOES NOT MATTER.");
        input = TextIO.getlnString();
        if (input.equalsIgnoreCase("up"))
        {
            characterSkillArray[8]++;
            if (characterSkillArray[8] > maxYValue)
            {
                characterSkillArray[8]--;
                System.out.print("You can't do that! You'd walk off the coordinate grid!");
                input = TextIO.getlnString();
                move();
            }
        }
        else
        {
            if (input.equalsIgnoreCase("down"))
            {
                characterSkillArray[8]--;
                if (characterSkillArray[8] < 0)
                {
                    characterSkillArray[8]++;
                    System.out.print("You can't do that! You'd walk off the coordinate grid!");
                    input = TextIO.getlnString();
                    move();
                }
            }
            else
            {
                if (input.equalsIgnoreCase("left"))
                {
                    characterSkillArray[9]--;
                    if (characterSkillArray[9] < 0)
                    {
                        characterSkillArray[9]++;
                        System.out.print("You can't do that! You'd walk off the coordinate grid!");
                        input = TextIO.getlnString();
                        move();
                    }
                }
                else
                {
                    if (input.equalsIgnoreCase("right"))
                    {
                        characterSkillArray[9]++;
                        if (characterSkillArray[9] > maxXValue)
                        {
                            characterSkillArray[9]--;
                            System.out.print("You can't do that! You'd walk off the coordinate grid!");
                            input = TextIO.getlnString();
                            move();
                        }
                    }
                    else
                    {
                        System.out.print("I THOUGHT I TOLD YOU TO SPELL CORRECTLY!!!");
                        input = TextIO.getlnString();
                        move();
                    }
                }
            }
        }
    }
    public void moveEnd()
    {
        System.out.print("You have successfully moved");
        input = TextIO.getlnString();
        System.out.println("You are now at (" + characterSkillArray[9] + "," + characterSkillArray[8] + ")");
    }
    public void name()
    {
        System.out.print("(pick an name for character): ");
        charName = TextIO.getlnString();
        System.out.println("So, your name is " + charName + "? " + "Please confirm your choice");
        input = TextIO.getlnString();
        if (yes())
        {
            System.out.println("Hello " + charName + ".");
            System.out.print("(press enter)");
        }
        else
        {
            name();
        }
    }
    public void mageRogueOrWarrior()
    {
        input = TextIO.getlnString();
        if (input.equalsIgnoreCase("Mage"))
        {
            Class = "Mage";
            specialAttack = "cast spell";
            String[] mageArray = { "Toy Wand", "Wooden Wand", "Oak Wand", "Wooden Staff", "Oak Staff", "Bejewled Mystic Staff", "Head Magician's Staff","All Powerful Staff of the Ancients"};
            System.arraycopy(mageArray, 0, weaponArray, 0, 7);
            double[] mageDamageArray = {3,5,9,15,23,35,46,58};
            System.arraycopy(mageDamageArray, 0, weaponBaseDamageArray, 0, 7);
            String[] mageArmorArray = { "Harry Potter Toy Invisibility Cloak", "Cloak", "Basic Robes", "Magical Robes", "Magical Tunic", "Flowing Garbs", "'I Reached the Second Highest Cloak Upgrade and All I Got Was This Stupid T - Shirt!' T Shirt","Energy Vest of Ultimate Power"};
            double[] mageBlockArray = {1,3,7,14,28,56,65,80};
            System.arraycopy(mageBlockArray, 0, armorBaseBlockArray, 0, 7);
            System.arraycopy(mageArmorArray, 0, armorArray, 0, 7);
        }
        else
        {
            if (input.equalsIgnoreCase("Warrior"))
            {
                Class = "Warrior";
                specialAttack = "powerfully strike";
                String[] warriorArray = { "Plastic Sword", "Wooden Practice Sword", "Apprentice's Blade", "Steel Blade", "Knight's Blade", "Gladiator's Sword", "Glistening Rapier","Demon Punisher"};
                System.arraycopy(warriorArray, 0, weaponArray, 0, 7);
                double[] warriorDamageArray = {5,10,20,40,65,90,110,150};
                System.arraycopy(warriorDamageArray, 0, weaponBaseDamageArray, 0, 7);
                String[] warriorArmorArray = { "Cardboard Box Armor", "Plastic Knight Costume", "Apprentice Chain Plate", "Iron Plate", "Jouster's Defender", "Crusader's Plate", "Shining Knight's Armor","Shining Gladiator's Armor"};
                System.arraycopy(warriorArmorArray, 0, armorArray, 0, 7);
                double[] warriorBlockArray = {1,2,3,4,5,7,10,20};
                System.arraycopy(warriorBlockArray, 0, armorBaseBlockArray, 0, 7);
            }
            else
            {
                if (input.equalsIgnoreCase("Rogue"))
                {
                    Class = "Rogue";
                    specialAttack = "throw daggers";
                    String[] rogueArray = { "Toy Razor", "Butter Knife", "Pocket Shiv", "Thug's Knife", "Machete", "Golden Dagger", "Back Stabber","Thieve's Pride"};
                    System.arraycopy(rogueArray, 0, weaponArray, 0, 7);
                    double[] rogueDamageArray = {1,2,3,5,9,15,30,50};
                    System.arraycopy(rogueDamageArray, 0, weaponBaseDamageArray, 0, 7);
                    String[] rogueArmorArray = { "Leather Scraps", "Expensive Leather Scraps", "Leather Armor", "Hoodlum Outfit", "Serial Killer's Hockey Mask", "Great  Theives Robes", "Silent Ninja Assassin Garb","Cloak of Complete Invisibility"};
                    System.arraycopy(rogueArmorArray, 0, armorArray, 0, 7);
                    double[] rogueBlockArray = {5,10,20,40,65,90,110,150};
                    System.arraycopy(rogueBlockArray, 0, armorBaseBlockArray, 0, 7);
                }
                else
                {
                    System.out.println("I can't understand what you're saying. Please retype:");
                    mageRogueOrWarrior();
                }
            }
        }
        weaponName = weaponArray[0];
        armorName = armorArray[0];
    }
    /************************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     */
    public void setSkills()
    {
        double pointsToSpend = 10;
        skills(pointsToSpend,5,"intelligence");
        pointsToSpend = RAM;
        skills(pointsToSpend,6,"strength");
        pointsToSpend = RAM;
        skills(pointsToSpend,7,"dexterity");
        pointsToSpend = RAM;
        System.out.println("Confirm your choices:");
        System.out.println("You have an intelligence of " + characterSkillArray[5]);
        System.out.println("You have a strength of " + characterSkillArray[6]);
        System.out.println("You have a dexterity of " + characterSkillArray[7]);
        input = TextIO.getlnString();
        if (yes())
        {
            System.out.print("You have successfully chosen your stats. Click enter to continue.");
        }
        else
        {
            setSkills();
        }
    }
    /************************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     */
    public void skills(double pointsToSpend, int whichStat, String whichStat2)
    {
        System.out.println("Now, choose your skills. You have " + pointsToSpend + " skill points to spend.");
        System.out.println("How many points would you like to spend on " + whichStat2 + "?");
        double pointsSpent = TextIO.getlnInt();
        if (pointsSpent > pointsToSpend)
        {
            System.out.println("You can't do that! You don't have enough points!");
            skills(pointsToSpend,whichStat,whichStat2);
        }
        else
        {
            System.out.println("Confirm: Do you want to spend " + pointsSpent + " points on " + whichStat2 + "?");
            System.out.println("You would have " + (pointsToSpend - pointsSpent) + " points left!");
            input = TextIO.getlnString();
            if (yes())
            {
                RAM = pointsToSpend - pointsSpent;
                characterSkillArray[whichStat] = characterSkillArray[whichStat]+pointsSpent;
            }
            else
            {
                skills(pointsToSpend,whichStat,whichStat2);
            }
        }
    }
    /************************************************************************************************************************************************************************************************
     */
    public void buyThings()
    {
        buyThings1();
        buyThings2();
        buyThings3();
    }
    public void buyThings1()
    {
        System.out.print("Well " + charName + ", now it's time for you to buy some stuff");
        input = TextIO.getlnString();
        System.out.print("You have " + characterSkillArray[2] + " gold left to spend");
        input = TextIO.getlnString();
        System.out.println("Here is a list of all the items and their prices:");
        System.out.println("Weapon upgrade: " + weaponUpgradeCostArray[weaponLevel + 1]);
        System.out.println("Armor upgrade: " + armorUpgradeCostArray[armorLevel + 1]);
        System.out.println("Would you like to upgrade your weapon from " + weaponName);
        System.out.print(" to " + weaponArray[weaponLevel + 1] + "? Cost: " + weaponUpgradeCostArray[weaponLevel + 1] + " gold ");
        input = TextIO.getlnString();
        if (yes())
        {
            if (weaponUpgradeCostArray[weaponLevel + 1] <= characterSkillArray[2])
            {
                characterSkillArray[2] = characterSkillArray[2] - weaponUpgradeCostArray[weaponLevel + 1];
                System.out.println("You now have " + characterSkillArray[2] + " gold.");
                weaponLevel++;
                weaponName = weaponArray[weaponLevel];
                System.out.println("You know weild the " + weaponName);
            }
            else
            {
                System.out.println("You can't buy that! You don't have enough money!!!");
                buyThings1();
            }
        }
    }
    public void buyThings2()
    {
        System.out.println("Would you like to upgrade your armor from " + armorName);
        System.out.print(" to " + armorArray[armorLevel + 1] + "? Cost: " + armorUpgradeCostArray[armorLevel + 1] + " gold ");
        input = TextIO.getlnString();
        if (yes())
        {
            if (armorUpgradeCostArray[armorLevel + 1] <= characterSkillArray[2])
            {
                characterSkillArray[2] = characterSkillArray[2] - armorUpgradeCostArray[armorLevel + 1];
                System.out.println("You now have " + characterSkillArray[2] + " gold.");
                armorLevel++;
                armorName = armorArray[weaponLevel];
                System.out.println("You now have equipped the " + armorName);
            }
            else
            {
                System.out.println("You can't buy that! You don't have enough money!!!");
                buyThings2();
            }
        }
    }
    public void buyThings3()
    {
        System.out.print("Would you like to exit the shop now? ");
        input = TextIO.getlnString();
        if (yes())
        {
            System.out.print("You are now exiting the shop.");
            input = TextIO.getlnString();
        }
        else
        {
            buyThings3();
        }
    }
    /************************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     */
    public void battle()
    {
        double originalHealth = characterSkillArray[3];
        double originalMana = characterSkillArray[4];
        double originalMonsterHealth = monsterSkillArray[0];
        double originalMonsterMana = monsterSkillArray[1];
        boolean yourTurnFirst = Math.random() >= 0.5;
        System.out.println("A monster has attacked you. Prepare to fight.");
        System.out.print("(press enter to continue)");
        input = TextIO.getlnString();
        if (yourTurnFirst)
        {
            charAttack();
        }
        while (monsterSkillArray[0] >= 0 && characterSkillArray[3] >= 0)
        {
            monsterAttack();
            if (characterSkillArray[3] > 0)
            {
                charAttack();
            }
        }
        if (monsterSkillArray[0] <= 0)
        {
            System.out.print("Congratualations! You emerge victorious!");
            monsterArray[(int)(characterSkillArray[8])][(int)(characterSkillArray[9])] = monsterArray[(int)(characterSkillArray[8])][(int)(characterSkillArray[9])] - 1;
            input = TextIO.getlnString();
            characterSkillArray[3] = originalHealth;
            characterSkillArray[4] = originalMana;
            monsterSkillArray[0] = originalMonsterHealth;
            monsterSkillArray[1] = originalMonsterMana;
            characterSkillArray[1] = characterSkillArray[1] + monsterSkillArray[2];
            double randomGold = Math.round(Math.abs((Math.random() * 1000) - (Math.random() * 1000)));
            characterSkillArray[2] = characterSkillArray[2] + monsterSkillArray[3] + randomGold;
            System.out.print("Press enter to view battle summary:");
            input = TextIO.getlnString();
            System.out.println("You are restored to full health and full energy.");
            System.out.println("You now have " + characterSkillArray[1] + " out of " + experienceNeeded + " experience.");
            System.out.println("You now have " + characterSkillArray[2] + " gold.");
            if (characterSkillArray[1] >= experienceNeeded)
            {
                levelUp();
            }
        }
        else
        {
            System.out.print("You are defeated, but fear not for this is your lucky day");
            input = TextIO.getlnString();
            System.out.println("You wake up back at the starting position, with half your gold gone,");
            characterSkillArray[2] = Math.round(characterSkillArray[2] * 0.5);
            characterSkillArray[8] = 0;
            characterSkillArray[9] = 0;
            System.out.println("but - hey - at least you're still alive!");
            characterSkillArray[3] = originalHealth;
            input = TextIO.getlnString();
        }
    }
    /************************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     */
    public void charAttack()
    {
        System.out.println("Would you like to 'attack', use '" + specialAttack + "', or rest?");
        double predeterminedAttackDamage = weaponBaseDamageArray[weaponLevel] + Math.round(0.1*characterSkillArray[0]*(Math.round(100*Math.random())));
        input = TextIO.getlnString();
        if (input.equalsIgnoreCase("attack"))
        {
            if (characterSkillArray[4] - 1 < 0)
            {
                System.out.print("You can't do that! You don't have enough energy!");
                input = TextIO.getlnString();
                charAttack();
            }
            else
            {
                System.out.println("You attack with your " + weaponName + ".");
                monsterSkillArray[0] = monsterSkillArray[0] - predeterminedAttackDamage;
                characterSkillArray[4] = characterSkillArray[4] - 1;
                System.out.println("You did " + predeterminedAttackDamage + " damage!");
                System.out.println("Your health is " + characterSkillArray[3] + " and your mana is " + characterSkillArray[4] + ".");
            }
        }
        else
        {
            if (input.equalsIgnoreCase(specialAttack))
            {
                if (characterSkillArray[4] - 3 < 0)
                {
                    System.out.print("You can't do that! You don't have enough energy!");
                    input = TextIO.getlnString();
                    charAttack();
                }
                System.out.println("You " + specialAttack + ".");
                monsterSkillArray[0] = monsterSkillArray[0] - 1.5*predeterminedAttackDamage;
                characterSkillArray[4] = characterSkillArray[4] - 3;
                System.out.println("You did " + 1.5*predeterminedAttackDamage + " damage!");
                System.out.println("Your health is " + characterSkillArray[3] + "and your mana is " + characterSkillArray[4] + ".");
            }
            else
            {
                if (input.equalsIgnoreCase("rest"))
                {
                    System.out.println("You just rest.");
                    characterSkillArray[4] = characterSkillArray[4] + 1;
                    System.out.println("Your health is " + characterSkillArray[3] + "and your mana is " + characterSkillArray[4] + ".");
                }
                else
                {
                    System.out.print("You spelled something wrong. Shame on you.");
                    charAttack();
                }
            }
        }
    }
     /************************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     */
    public void monsterAttack()
    {
        boolean doNotRest = Math.random() >= 0.2;
        double predeterminedAttackDamage = monsterLevel*2 + Math.round(0.1*characterSkillArray[0]*(Math.round(100*Math.random())));
        characterSkillArray[3] = characterSkillArray[3] - predeterminedAttackDamage + armorBaseBlockArray[armorLevel];
        if (monsterSkillArray[1] > 0)
        {
            if (doNotRest)
            {
                System.out.print("Press enter to see how much damage the monster did to you");
                input = TextIO.getlnString();
                System.out.print("The monster did " + predeterminedAttackDamage + " damage to you.");
                input = TextIO.getlnString();
                System.out.println("You have " + characterSkillArray[3] + " health and " + characterSkillArray[4] + " mana left");
            }
            else
            {
                System.out.print("Monster rested");
                input = TextIO.getlnString();
                monsterSkillArray[1] = monsterSkillArray[1] + 3;
                monsterSkillArray[0] = monsterSkillArray[0] + 1;
                characterSkillArray[3] = characterSkillArray[3] + predeterminedAttackDamage - armorBaseBlockArray[armorLevel];
            }
        }
        else
        {
            System.out.print("Monster rested");
            input = TextIO.getlnString();
            monsterSkillArray[1] = monsterSkillArray[1] + 3;
            monsterSkillArray[0] = monsterSkillArray[0] + 1;
            characterSkillArray[3] = characterSkillArray[3] + predeterminedAttackDamage - armorBaseBlockArray[armorLevel];
            
        }
    }
     /************************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     */
    public void levelUp()
    {
        System.out.println("Congratulations! You have levelled up!");
        input = TextIO.getlnString();
        characterSkillArray[0]++;
        characterSkillArray[3] = 15 + Math.round(0.5 * characterSkillArray[0] * (characterSkillArray[0] + 1));
        characterSkillArray[4] = 15 + Math.round(0.25 * characterSkillArray[0] * (characterSkillArray[0] + 1));
        experienceNeeded = (Math.pow(characterSkillArray[0],2)) * experienceNeeded;
        double a = monsterSkillArray[0];
        double b = monsterSkillArray[1];
        double c = monsterSkillArray[2];
        double d = monsterSkillArray[3];
        double[] monsterArray = {a + 3, b + 3, c * 2, d * 2};
        System.arraycopy(monsterArray,0,monsterSkillArray,0,3);
        monsterLevel = characterSkillArray[0];
        System.out.println("You are now level " + characterSkillArray[0]);
        System.out.println("... and so are the monsters! :P");
        RAM = 5;
        skills(RAM,5,"intelligence");
        skills(RAM,6,"strength");
        skills(RAM,7,"dexterity");
    }
    /************************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     */
    public boolean isGameOver()
    {
        int x = 0;
        int y = 0;
        int test = 0;
        while (y < 10)
        {
            while (x < 10)
            {
                if (monsterArray[x][y] != 0)
                {
                    test++;
                }
                x++;
            }
            x = 0;
            y++;
        }
        return test == 0;
    }
    /************************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     */
    public boolean isAtHomeSquare()
    {
        return characterSkillArray[8] == 0 && characterSkillArray[9] == 0;
    }
    /************************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     * **********************************************************************************************************************************************************************************************
     */
    public boolean nextToAMonster()
    {
        return monsterArray[(int)(characterSkillArray[8])][(int)(characterSkillArray[9])] > 0;
    }
    public boolean yes()
    {
        return input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y");
    }
}
