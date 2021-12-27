package BusinessLayer;

import BusinessLayer.Enemy.Enemy;
import BusinessLayer.Player.Player;

public interface visitor {
    public boolean visit(Player player);
    public boolean visit(Enemy enemy);
    public boolean visit(Wall wall);
    public boolean visit(EmptyTile empty);
}
