package BusinessLayer.Player;
import BusinessLayer.*;
import BusinessLayer.Enemy.Enemy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Player extends Unit{
    // ----------------------------------- fields ----------------------------------------------------------------------
    protected Integer playerLevel;
    protected Integer exp;
    // ----------------------------------- constructor -----------------------------------------------------------------
    public Player(String name, point position,level level,Integer healthAmount,Integer defensePoints,Integer attackPoints){
        super(name, '@', position,level, healthAmount,defensePoints,attackPoints);
        playerLevel=1;
        exp=0;
    }
    // ----------------------------------- methods ---------------------------------------------------------------------
    public boolean visit(Player player){return false;}
    public boolean visit(Enemy enemy){
        // rolling defense and attack points
        Integer attackerRoll = random(0,this.getAttackPoints());
        this.messageHandler.Print(" "+this.describe()+" attack with " + attackerRoll + " attack points");
        Integer defenderRoll = random(0,enemy.getDefensePoints());
        this.messageHandler.Print(" "+enemy.describe()+"  defend with " + defenderRoll + " defense points");
        Integer sum = attackerRoll - defenderRoll; // the damage
        if (sum > 0) { // player wins
            enemy.loseHealth(sum);
            this.messageHandler.Print(enemy.describe()+" damaged with "+sum+" and now remain with "+enemy.getHealthAmount()+" health amount");
            if (enemy.isDead()) {
                this.setExp(this.getExp() + enemy.getExpValue()); // player earn the enemy's exp,this method are responsible to check about the level
                this.messageHandler.Print(this.describe()+" gained "+enemy.getExpValue()+" experience");
                this.messageHandler.Print(enemy.describe()+" is dead");
                level.swap(this, enemy); // move the player into the enemy's position
                level.enemyDead(enemy); // clear the enemy from the board
                this.messageHandler.Print(this.describe());
            }
        }
        return isDead;
    } // player attack enemy
    public boolean visit(Wall wall){
        messageHandler.Print(" cant move into a wall");
        return false;} // player cant step into a wall
    public boolean visit(EmptyTile empty){
        level.swap(this,empty);
        return true;
    } // player step into an empty tile
    public boolean accept(visitor v){return v.visit(this);}
    public abstract void tick();
    public Integer getlevel(){return this.playerLevel;} // player level - not game level
    public Integer getExp(){return exp;}
    public abstract void setExp(Integer exp1);
    public void levelup() {
            exp = exp - (50 * playerLevel);
            playerLevel++;
            this.health.setHealthPool(this.health.getHealthPool() + (10 * playerLevel));
            this.health.setHealthAmount(this.health.getHealthPool()); //have to check what is current health
            attackPoints = attackPoints + (4 * playerLevel);
            defensePoints = defensePoints + playerLevel;
            this.messageHandler.Print(this.describe()+" is level up to "+this.playerLevel+" level");
    } // for all the players
    public abstract void castAbility();
    public void move(int direction){
        if (direction==1) { // move up
            point place = this.up();
            Tile tile = level.getTile(place);
            if (tile.accept(this)){
                this.messageHandler.Print(this.describe()+" moves up");
            }
        }
        if (direction==2) { // move down
            point place = this.down();
            Tile tile = level.getTile(place);
            if (tile.accept(this)){
                this.messageHandler.Print(this.describe()+" moves down");
            }
        }
        if (direction==3) { // move right
            point place = this.right();
            Tile tile = level.getTile(place);
            if (tile.accept(this)){
                this.messageHandler.Print(this.describe()+" moves right");
            }
        }
        if (direction==4) { // move left
            point place = this.left();
            Tile tile = level.getTile(place);
            if (tile.accept(this)){
                this.messageHandler.Print(this.describe()+" moves left");
            }
        }
        if (direction==5) { // cast ability
            this.castAbility();
        }

        this.tick();

    }
    public abstract String describe();
    public void SetIsDead() {
        this.isDead=true;
    }
    public boolean fight(Enemy enemy,Integer attackerRoll){ // player attack enemy - for the cast ability only, when the attack points determine
        this.messageHandler.Print(" "+this.describe()+" attack with " + attackerRoll + " attack points");
        Integer defenderRoll = random(0,enemy.getDefensePoints());
        this.messageHandler.Print(" "+enemy.describe()+"  defend with " + defenderRoll + " defense points");
        Integer sum = attackerRoll - defenderRoll;
        if (sum > 0) { // player wins
            enemy.loseHealth(sum);
            this.messageHandler.Print(enemy.describe()+" damaged with "+sum+" and now remain with "+enemy.getHealthAmount()+" health amount");

            if (enemy.isDead()) {
                this.setExp(this.getExp() + enemy.getExpValue()); // player earn the enemy's exp,this method are responsible to check about the level
                this.messageHandler.Print(this.describe()+" gained "+enemy.getExpValue()+" experience");
                this.messageHandler.Print(enemy.describe()+" is dead");
                level.swap(this, enemy); // move the player into the enemy's position
                level.enemyDead(enemy); // clear the enemy from the board
                this.messageHandler.Print(this.describe());

            }
        }
        return isDead;
    }
    public List<Enemy> LivingEnemyAtRange(Integer range){
        List<Enemy> enemyAtRange = new LinkedList<>(); // return null if there is no enemies in range
        List<Enemy> enemies = this.level.getEnemies();
        int counter=0;
        for (Enemy enemy : enemies)
        {
            if (this.range(enemy)<range) {
                enemyAtRange.add(enemy);
                counter++;
            }
        }
        if (counter==0)
            return null;
        else
            return enemyAtRange;

    }
    // --------------------------------------- static method -----------------------------------------------------------
    public static Integer random(Integer lower,Integer higher){
        double r =  (Math.random()*(higher-lower+1))+lower;
        return (int)r;


    }



}