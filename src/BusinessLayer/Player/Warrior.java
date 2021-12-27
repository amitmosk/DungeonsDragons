package BusinessLayer.Player;

import BusinessLayer.Enemy.Enemy;
import BusinessLayer.level;
import BusinessLayer.point;

import java.util.ArrayList;
import java.util.List;

public class Warrior extends Player {
    // ----------------------------------- fields ----------------------------------------------------------------------
    int abilityCooldown; // number of rounds between using this ability
    int remainingCooldown; // amount of rounds until using again
    // ----------------------------------- constructor ----------------------------------------------------------------------
    public Warrior(String name,int abilityCooldown, point position, BusinessLayer.level level,Integer healthAmount,Integer defensePoints,Integer attackPoints){
        super(name,position,level,healthAmount,defensePoints,attackPoints);
        this.abilityCooldown=abilityCooldown;
        remainingCooldown=0;
    }
    // ----------------------------------- methods ----------------------------------------------------------------------
    public void tick(){
        if (remainingCooldown>0)
            this.remainingCooldown--;
    }
    public void setExp(Integer exp1){
        this.exp=exp1;
        this.LevelUp(); // check if the player level should be increase
    }
    public void castAbility(){
        if (this.remainingCooldown>0){
            this.messageHandler.Print(" cant cast ability yet");
        }
        else {
            remainingCooldown=abilityCooldown;
            this.health.setHealthAmount(Math.min(health.getHealthAmount()+(10*this.defensePoints),health.getHealthPool()));
            List<Enemy> lessThree = this.LivingEnemyAtRange(3);
            if (lessThree!=null) {
                int ran = random(0, lessThree.size() - 1);
                Enemy toHit = lessThree.get(ran);
                int damage = this.health.getHealthPool() / 10;
                toHit.loseHealth(damage);
                this.messageHandler.Print(this.describe()+" use Avenger’s Shield, and now have "+remainingCooldown+" remaining cool down,new health amount:"+this.getHealthAmount()+" and damage "+toHit.describe()+ "by "+damage);
            }
            else
                this.messageHandler.Print(this.describe()+" perform cast ability and now have "+remainingCooldown+" remaining cool down,new health amount:"+this.getHealthAmount());

        }
    }
    public String describe() {
        String ans="";
        ans = ans + "Player: "+ this.getName()+" health: "+this.health.HealthAmount+"/"+this.health.getHealthPool()+" attack points: "+this.attackPoints+" defense points: "+this.defensePoints+ " level: "+this.playerLevel+" experience: "+this.exp+" remain cool down:"+remainingCooldown+"/"+abilityCooldown;
        return ans;
    }
    public void LevelUp(){
        if (exp>=playerLevel*50) {
            this.levelup();
            remainingCooldown = 0;
            this.health.setHealthPool(health.getHealthPool() + (5 * getlevel()));
            this.attackPoints = attackPoints + (2 * getlevel());
            this.defensePoints = defensePoints + getlevel();
        }
    }



}
