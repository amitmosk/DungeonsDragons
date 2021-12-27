package Tests;

import BusinessLayer.*;
import BusinessLayer.Enemy.Enemy;
import BusinessLayer.Enemy.Monster;
import BusinessLayer.Enemy.Trap;
import BusinessLayer.Player.Player;
import BusinessLayer.Player.Warrior;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
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
    @BeforeEach
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

player1.setLevel(first);
    }

    @Test
    void livingEnemyAtRange1() {
        first.swap(player1,wall1);
        List<Enemy> enemeis = player1.LivingEnemyAtRange(1);
        System.out.println("the enemies in the range should be 0");
        Assert.assertNull(enemeis);
    }
    @Test
    void loseHealth1(){
        player1.loseHealth(150);
        System.out.println("the player should die and is health amount is :");
        Assert.assertTrue(player1.isDead());
        System.out.println(player1.getHealthAmount());
    }
    @Test
    void loseHealth2(){
        player1.loseHealth(50);
        Integer a = 50;
        System.out.println("the player should lose health and is health amount should be 50 and it is :");
        Assert.assertEquals(a,player1.getHealthAmount());
        System.out.println(player1.getHealthAmount());
    }
    @Test
    void loseHealth3(){
        player1.loseHealth(0);
        Integer a = 100;
        System.out.println("the player should lose 0 health and is health amount should be 100 and it is :");
        Assert.assertEquals(a,player1.getHealthAmount());
        System.out.println(player1.getHealthAmount());
    }
    @Test
    void livingEnemyAtRange2() {
        List<Enemy> enemeis = player1.LivingEnemyAtRange(3);
        System.out.println("the enemies in the range should be 2");
        Assert.assertEquals(2,enemeis.size());
        System.out.println(enemeis.size());

    }
}