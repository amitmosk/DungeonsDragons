package BusinessLayer;

import BusinessLayer.Enemy.Enemy;
import BusinessLayer.Player.Player;
import BusinessLayer.Tile;
import BusinessLayer.point;

public class EmptyTile extends Tile {
    // ----------------------------------- constructor ----------------------------------------------------------------------
    public EmptyTile(point position){
        super(position,'.');
    }
    // ----------------------------------- methods ----------------------------------------------------------------------
    // empty tile cant visit anyone
    public boolean visit(Player player){return false;}
    public boolean visit(Enemy enemy){return false;}
    public boolean visit(Wall wall){return false;}
    public boolean visit(EmptyTile empty){return false;}
    public boolean accept(visitor v){return v.visit(this);}

}
