package BusinessLayer;

import BusinessLayer.Enemy.Enemy;
import BusinessLayer.Player.Player;
import BusinessLayer.Tile;
import BusinessLayer.point;

public class Wall extends Tile {
    // ----------------------------------- constructor ----------------------------------------------------------------------
    public Wall(point position){
        super(position,'#');
    }
    // ----------------------------------- methods ----------------------------------------------------------------------
    // wall cant visit anyone
    public boolean visit(Player player){return false;}
    public boolean visit(Enemy enemy){return false;}
    public boolean visit(Wall wall){return false;}
    public boolean visit(EmptyTile empty){return false;}
    public boolean accept(visitor v){return v.visit(this);}
}
