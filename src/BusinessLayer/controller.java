package BusinessLayer;

import BusinessLayer.*;
import BusinessLayer.Enemy.Enemy;
import BusinessLayer.Player.Player;

import java.util.List;
import java.util.Scanner;

public class controller {
    // ----------------------------------- fields ---------------------------------------------------------------------
    private level currLevel;
    private List<level> levels;
    private Player player;
    private int indexLevel;
    private EventHandler messageHandler;
    // ----------------------------------- constructor ---------------------------------------------------------------------
    public controller(List<level> levels,Player player){
        this.levels=levels;
        currLevel=levels.get(0);
        this.player=player;
        indexLevel=0;
        this.messageHandler=new EventHandler();
    }
    // ----------------------------------- methods ---------------------------------------------------------------------
    public void play(){
        boolean gameOver=false;
        while (currLevel.EnemiesAreDead()!=true){

            // printing
            // print all board
            messageHandler.Print(currLevel.describe());
            // print player stats
            messageHandler.Print(player.describe());

            int direction=messageHandler.inputReciever();
            currLevel.tick(direction,player);

            // player lose
            if (player.isDead()){
                currLevel.getTile(player.getPosition()).setTileType('X');
                messageHandler.Print("you lose - game over");
                messageHandler.Print(currLevel.describe());
                gameOver=true;
                break;
            }
        }
        if(!gameOver) {
            // go to next level or finish by winning
            indexLevel++;
            if (indexLevel == levels.size()) { // the player wins
                messageHandler.Print("you win - game over");

            }
            else
                {
                // updating player position for next level
                player.setPosition(null);
                currLevel = levels.get(indexLevel);
                currLevel.setPlayerInTheBoardGame(player);
                player.setLevel(currLevel);
                this.play();
            }
        }
    }

}



