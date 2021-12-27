package BusinessLayer;

import BusinessLayer.Enemy.Enemy;
import BusinessLayer.Player.Player;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

public abstract class Tile implements visited,visitor {
    // ----------------------------------- fields ----------------------------------------------------------------------
    private char tileType;
    private point position;
    // ----------------------------------- constructors ----------------------------------------------------------------------
    public Tile(point position,char tileType){
        this.position=position;
        this.tileType=tileType;
    }
    // ----------------------------------- methods ----------------------------------------------------------------------
    public point getPosition(){ return  position;}
    public void setPosition(point Position){this.position=Position;}
    public char getTileType() {
        return tileType;
    }
    public void setTileType(char type){
        tileType=type;
    }
    public double range(Tile b){
        double ans=0;
        double x=this.getPosition().getX()-b.getPosition().getX();
        x=x*x;
        double y=this.getPosition().getY()-b.getPosition().getY();
        y=y*y;
        ans=x+y;
        ans=Math.sqrt(ans);
        return ans;
    }
    public String toString () {
        String ans="";
        return ans+this.tileType;
    }
}
