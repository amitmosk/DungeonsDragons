package BusinessLayer.Enemy;
import BusinessLayer.level;
import BusinessLayer.Player.Player;
import BusinessLayer.Tile;
import BusinessLayer.point;

import static BusinessLayer.Player.Player.random;

public class Monster extends Enemy {
    // ----------------------------------- fields ----------------------------------------------------------------------
    private Integer visionRange;
    // ----------------------------------- constructor ----------------------------------------------------------------------
    public Monster(String name,Integer visionRange, point position, level level,Integer healthAmount,Integer defensePoints,Integer attackPoints,Integer exp,char type){
        super(name,position,level,healthAmount,defensePoints,attackPoints,exp,type);
        this.visionRange=visionRange;
    }
    // ----------------------------------- methods ----------------------------------------------------------------------
    public void tick(Player player) {
        int dx=0;
        int dy=0;
        point place;
        point playerPosition=player.getPosition();
        if (this.range(player) < this.visionRange)
        {
            dy = this.getPosition().getX() - playerPosition.getX();
            dx =  this.getPosition().getY() - playerPosition.getY();
            if (Math.abs(dx) > Math.abs(dy))
            {
                if (dx > 0)
                { // moving left
                     place = this.left(); // have to change the position one left
                }
                else
                { // moving right
                     place = this.right(); // have to change the position one left
                }
            }
            else
            {
                if (dy>0)
                { // moving up
                     place = this.up(); // have to change the position one left
                }
                else
                { // moving down
                     place = this.down(); // have to change the position one left
                }
            }
        }
        else
        {
            place = randomMove();
        }
        if (place!=null) {
            Tile tile = level.getTile(place);
            tile.accept(this);

        }

    }
    public point randomMove() {
        double ran = random(1,4);
        if (ran==1)
        {
            return up();
        }
        else if (ran==2)
        {
            return down();
        }
        else if (ran==3)
        {
            return right();
        }
        else if (ran==4)
        {
            return left();
        }
        throw new IllegalArgumentException("the random does not work well");
    } // have to change random
    public String describe() {
        String ans="";
        ans = ans + " The Enemy "+ this.getName()+" Health: "+this.getHealthAmount()+ " Enemy Experience: "+this.enemyExp + " Attack Points: "+this.attackPoints+" Defense Points: "+this.defensePoints+" Vision Range: "+visionRange;
        return ans;
    }
}
