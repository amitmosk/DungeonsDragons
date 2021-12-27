package BusinessLayer.Enemy;


import BusinessLayer.*;
import BusinessLayer.Player.Player;

import static BusinessLayer.Player.Player.random;

public abstract class Enemy extends Unit {
    // ----------------------------------- fields ----------------------------------------------------------------------
    protected Integer enemyExp; // the experience tha player gains if he kill this enemy
    // ----------------------------------- constructor ----------------------------------------------------------------------
    public Enemy(String name,point position,level level,Integer healthAmount,Integer defensePoints,Integer attackPoints, Integer exp,char type){
        super(name,type,position,level,healthAmount,defensePoints,attackPoints);
        this.enemyExp=exp;
    }
    // ----------------------------------- methods ----------------------------------------------------------------------
    public boolean visit(Player player){
        // rolling defense and attack points
        Integer attackerRoll = random(0,this.getAttackPoints());
        this.messageHandler.Print(this.describe()+" attack with " + attackerRoll + " attack points");
        Integer defenderRoll = random(0,player.getDefensePoints());
        this.messageHandler.Print(player.describe()+" defend with " + defenderRoll + " defend points");
        Integer sum = attackerRoll - defenderRoll; // the damage
        if (attackerRoll - defenderRoll > 0) {
            player.loseHealth(sum);
            this.messageHandler.Print(player.describe()+" damaged with "+sum+" and now remain with "+player.getHealthAmount()+" health amount");
            if (player.getHealthAmount() <= 0) {
                player.setTileType('X');
                player.SetIsDead();
                // game over
            }
        }
        return true;
    } // enemy attack player
    public boolean visit(Enemy enemy){return false;} // enemy cant step into an enemy tile
    public boolean visit(Wall wall){return false;}// enemy cant step into a wall
    public boolean visit(EmptyTile empty){
        level.swap(this,empty);
        return true;
    } // enemy step into an empty tile
    public boolean accept(visitor v){return v.visit(this);}
    public Integer getExpValue()
    {
        return this.enemyExp;
    }
    public abstract void tick(Player player);
    public abstract String describe();








}