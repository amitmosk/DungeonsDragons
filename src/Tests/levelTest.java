package Tests;
import BusinessLayer.*;
import BusinessLayer.Enemy.Enemy;
import BusinessLayer.Enemy.Monster;
import BusinessLayer.Enemy.Trap;
import BusinessLayer.Player.Player;
import BusinessLayer.Player.Warrior;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class levelTest {

    Tile[][] board;
    List<Enemy> enemeis;
    level first;
    Wall wall1;
    Wall wall2;
    Wall wall3;
    EmptyTile empty1;
    EmptyTile empty2;
    EmptyTile empty3;
    Monster monster1;
    Trap trap1;
    Player player1;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

         wall1 = new Wall(new point(0,0));
         wall2= new Wall(new point(0,1));
         wall3 = new Wall(new point(0,2));
         empty1=new EmptyTile(new point(1,0));
         empty2 = new EmptyTile(new point(1,1));
         empty3 = new EmptyTile(new point(1,2));
         monster1 = new Monster("Tom",10,new point(2,0),first,100,5,50,100,'k');
         trap1 = new Trap("SA",20,3,new point(2,1),first,50,30,50,1,'S');
         player1=new Warrior("amit",50,new point(2,2),first,100,50,50);
        enemeis = new LinkedList<Enemy>();
        enemeis.add(trap1);
        enemeis.add(monster1);
        board = new Tile[3][3];
        board[0][0]=wall1;
        board[0][1]=wall2;
        board[0][2]=wall3;
        board[1][0]= empty1;
        board[1][1]= empty2;
        board[1][2]= empty3;
        board[2][0]= monster1;
        board[2][1]=trap1;
        board[2][2]= player1;
        first = new level(board,enemeis);

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test

    void swap1() {
        first.swap(board[0][0],board[1][1]);
        System.out.println("after swap, the tile in the 0,0 position should be empty and it is : ");
        Assert.assertEquals('.',board[0][0].getTileType());
        System.out.println(board[0][0].getTileType());
    }


    @org.junit.jupiter.api.Test
    void swap2() {
        try {
            first.swap(board[0][0], board[5][5]);
        }
        catch (Exception e){
            System.out.println("exception expected, out of bounds");

        }
    }
    @org.junit.jupiter.api.Test
    void swap3() {

            first.swap(board[0][0], board[2][2]);
        System.out.println("after swap, the tile in the 0,0 position should be player and it is : ");
        Assert.assertEquals('@',board[0][0].getTileType());
        System.out.println(board[0][0].getTileType());

        }
    @org.junit.jupiter.api.Test

    void enemisAreDead1() {
        boolean flag = first.EnemiesAreDead();
        System.out.println("the enemies are alive, so the answer should be false and it is : ");
        Assert.assertFalse(flag);
        System.out.println(flag);

    }
    @org.junit.jupiter.api.Test

    void enemisAreDead2() {
        trap1.setIsDead();
        monster1.setIsDead();
        boolean flag = first.EnemiesAreDead();
        System.out.println("the enemies are dead, so the answer should be true and it is : ");
        Assert.assertTrue(flag);
        System.out.println(flag);

    }



}