package BusinessLayer.Player;

import BusinessLayer.level;
import BusinessLayer.Enemy.Enemy;
import BusinessLayer.point;

import java.util.ArrayList;
import java.util.List;

public class Rogue extends  Player {
    // ----------------------------------- fields ----------------------------------------------------------------------
    Integer cost;
    Integer currentEnergy;
    // ----------------------------------- constructor ----------------------------------------------------------------------
    public Rogue(String name,Integer cost, point position, level level,Integer healthAmount,Integer defensePoints,Integer attackPoints){
        super(name,position,level,healthAmount,defensePoints,attackPoints);
        this.cost=cost;
        this.currentEnergy=100;
    }
    // ----------------------------------- methods ----------------------------------------------------------------------
    public void LevelUp(){
        if (exp>=playerLevel*50) {
            this.levelup();
            currentEnergy = 100;
            attackPoints = attackPoints + (3 * getlevel());
        }
    }
    public void tick(){
        this.currentEnergy=Math.min(currentEnergy+10,100);
    }
    public void setExp(Integer exp1){
        this.exp=exp1;
        this.LevelUp(); // check if the player level should be increase
    }
    public void castAbility(){
        if (this.currentEnergy<this.cost){
            this.messageHandler.Print(" cant cast ability yet");
        }
        else {
            this.currentEnergy=currentEnergy-cost;
            List<Enemy> enemies = this.LivingEnemyAtRange(2);
            if (enemies!=null) {
                for (Enemy enemy : enemies) {
                    this.fight(enemy, this.attackPoints);
                }
            }
            this.messageHandler.Print(this.describe()+" use Fan of Knives and now has current energy : "+currentEnergy);
        }
    }
    public String describe() {
        String ans="";
        ans = ans + "Player: "+ this.getName()+" health: "+this.health.HealthAmount+"/"+this.health.getHealthPool()+" attack points: "+this.attackPoints+" defense points: "+this.defensePoints+ " level: "+this.playerLevel+" experience: "+this.exp+" cost:"+cost+" current energy:"+currentEnergy;
        return ans;
    }

}
