package BusinessLayer.Player;

import BusinessLayer.level;
import BusinessLayer.Enemy.Enemy;
import BusinessLayer.point;

import java.util.ArrayList;
import java.util.List;

public class Mage extends Player {
    // ----------------------------------- fields ----------------------------------------------------------------------
    Integer manaPool;
    Integer currentMana;
    Integer manaCost;
    Integer spellPower;
    Integer hitsCount;
    Integer abilityRange;
    // ----------------------------------- constructor ----------------------------------------------------------------------
    public Mage(String name,Integer manaPool, Integer manaCost, Integer spellPower, Integer hitsCount, Integer abilityRange, point position, level level,Integer healthAmount,Integer defensePoints,Integer attackPoints){
        super(name,position,level,healthAmount,defensePoints,attackPoints);
        this.manaPool=manaPool;
        this.manaCost=manaCost;
        this.spellPower=spellPower;
        this.hitsCount=hitsCount;
        this.abilityRange=abilityRange;
        this.currentMana=manaPool/4;
    }
    // ----------------------------------- methods ----------------------------------------------------------------------
    public void LevelUp(){
        if (exp>=playerLevel*50) {
            this.levelup();
            manaPool = manaPool + (25 * this.getlevel());
            currentMana = Math.min(currentMana + manaPool / 4, manaPool);
            spellPower = spellPower + (10 * getlevel());
        }
    }
    public void setExp(Integer exp1){
        this.exp=exp1;
        LevelUp(); // check if the player level should be increase
    }
    public void tick(){
        this.currentMana=Math.min(manaPool,currentMana+this.playerLevel);
    }
    public void castAbility(){
        if (this.currentMana<this.manaCost){
            this.messageHandler.Print(" cant cast ability yet");
        }
        else {
            this.currentMana=this.currentMana-this.manaCost;
            int hits=0;
            List<Enemy> enemiesAtRange = LivingEnemyAtRange(abilityRange);
            while ((hits<this.hitsCount) & (enemiesAtRange!=null)){
                int ran = random(0, enemiesAtRange.size()-1);
                Enemy toHit = enemiesAtRange.get(ran);
                int damage=this.spellPower;
                this.fight(toHit,damage);
                hits++;
                if (toHit.isDead())
                    enemiesAtRange.remove(toHit);
            }
            this.messageHandler.Print(this.describe()+" use Blizzard and now have Mana : "+currentMana);
        }
    }
    public String describe() {
        String ans="";
        ans = ans + "Player: "+ this.getName()+" health: "+this.health.HealthAmount+"/"+this.health.getHealthPool()+" attack points: "+this.attackPoints+" defense points: "+this.defensePoints+ " level: "+this.playerLevel+" experience: "+this.exp+" Mana: "+currentMana+"/"+manaPool+" mana cost:" + manaCost+ " spell power:"+spellPower+" hits count:"+hitsCount+ " ability range: "+abilityRange;
        return ans;
    }


}
