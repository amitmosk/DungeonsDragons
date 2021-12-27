package BusinessLayer;

import BusinessLayer.Enemy.Enemy;
import BusinessLayer.Player.Player;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

import java.util.List;

public class level {
    // ----------------------------------- fields ----------------------------------------------------------------------
    private Tile[][] boardGame;
    private List<Enemy> enemyList;
    private EventHandler messageHandler;
    // ----------------------------------- constructor ----------------------------------------------------------------------
    public level(Tile[][] board,List<Enemy> enemies){
        this.boardGame=board;
        this.enemyList=enemies;
        this.messageHandler=new EventHandler();
    }
    // ----------------------------------- methods ----------------------------------------------------------------------
    public void swap(Tile a,Tile b){ // a move forward to b
          point aPosition = a.getPosition();
          point bPosition=b.getPosition();
          a.setPosition(b.getPosition());
          b.setPosition(aPosition);
          boardGame[a.getPosition().getX()][a.getPosition().getY()]=a;
          boardGame[b.getPosition().getX()][b.getPosition().getY()]=b;

    }
    public boolean EnemiesAreDead() {
        for (Enemy enemy : enemyList)
        {
            if (!enemy.isDead())
                return false;
        }
        return true;
    }
    public void enemyDead(Tile a) { // when a enemy dead
        point pos = a.getPosition();
        EmptyTile toEnter = new EmptyTile(pos);
        boardGame[pos.getX()][pos.getY()]=toEnter;
        enemyList.remove(a);
    }
    public Tile getTile (point position)
    {
        return boardGame[position.getX()][position.getY()];
    }
    public boolean isValidPoint(point toMove) {
        int x = toMove.getX();
        int y=toMove.getY();
        if (x<0 | x>this.boardGame.length | y<0 | y>this.boardGame[0].length)
        {
            messageHandler.Print("cant move outside the board");
            return false;
        }
        return true;
    }
    public void tick(int direction,Player player) {
        player.move(direction); // player move or cast ability
        // enemies move
        for (Enemy enemy : enemyList)
        {
            enemy.tick(player);
        }
    }
    public List<Enemy> getEnemies() {
        return enemyList;
    }
    public point getPlayerPosition() {
        for (int i=0;i<boardGame.length;i++){
            for (int j=0;j<boardGame[i].length;j++){
                if (boardGame[i][j].getTileType()=='@'){
                    int x=i;
                    int y=j;
                    point toReturn = new point(x,y);
                    return toReturn;
                }
            }
        }
        return null;
    }
    public String describe() {
        String answer="";
        for (int i=0;i<boardGame.length;i++){
            for (int j=0;j<boardGame[i].length;j++){
                answer=answer+" "+boardGame[i][j].getTileType();
            }
            answer=answer+"\n";
        }
        return answer;
    }
    public void setPlayerInTheBoardGame(Player player) {
        point position = this.getPlayerPosition();
        boardGame[position.getX()][position.getY()]=player;
        player.setPosition(position);

    }
    public void setBoardGame(Tile[][] boardGame) {
        this.boardGame = boardGame;
    }
    public void setEnemyList(List<Enemy> enemyList) {
        this.enemyList = enemyList;
    }
}
