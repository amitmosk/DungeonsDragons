package BusinessLayer;
import BusinessLayer.*;
import BusinessLayer.Enemy.Enemy;
import BusinessLayer.Enemy.Monster;
import BusinessLayer.Enemy.Trap;
import BusinessLayer.Player.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program
{

    // ----------------------------------- methods ---------------------------------------------------------------------

    public static level parseLevel(List<String> levelData,Player player,boolean flag) {
        List<Enemy> enemies = new ArrayList<>();
        Tile[][] board = new Tile[levelData.size()][levelData.get(0).length()];
        level currLevel = new level(board, enemies);
        int i = 0;//line
        for (String row : levelData)
        {
            for (int j = 0; j < row.length(); j++)//column
            {
                point position = new point(i, j);
                char ch = row.charAt(j);
                if (ch == '#') {
                    board[i][j] = new Wall(position);
                }
                if (ch == '.') {
                    board[i][j] = new EmptyTile(position);
                }
                //-------------------------------------------player
                if (ch == '@')
                {
                    if (flag)
                    {
                        board[i][j] = player;
                        player.setPosition(position);
                        player.setLevel(currLevel);
                    }
                    board[i][j]=player;
                }
                //--------------------------------------------Enemies
                if (ch == 's') {
                    Enemy lanisterSoldier = new Monster("Lannister Solider", 3, position, currLevel, 80, 3, 8, 25, 's');
                    board[i][j] = lanisterSoldier;
                    enemies.add(lanisterSoldier);
                }
                if (ch == 'k') {
                    Enemy lanisterKnight = new Monster("Lannister Knight", 4, position, currLevel, 200, 8, 14, 50, 'k');
                    board[i][j] = lanisterKnight;
                    enemies.add(lanisterKnight);
                }
                if (ch == 'q') {
                    Enemy queensGuard = new Monster("Queen’s Guard", 5, position, currLevel, 400, 15, 20, 100, 'q');
                    board[i][j] = queensGuard;
                    enemies.add(queensGuard);
                }
                if (ch == 'z') {
                    Enemy wright = new Monster("Wright", 3, position, currLevel, 600, 15, 30, 100, 'z');
                    board[i][j] = wright;
                    enemies.add(wright);
                }
                if (ch == 'b') {
                    Enemy baerWright = new Monster("Bear-Wright", 4, position, currLevel, 1000, 30, 75, 250, 'b');

                    board[i][j] = baerWright;
                    enemies.add(baerWright);
                }
                if (ch == 'g') {
                    Enemy giantWright = new Monster("Giant-Wright", 5, position, currLevel, 1500, 40, 100, 500, 'g');
                    board[i][j] = giantWright;
                    enemies.add(giantWright);
                }
                if (ch == 'w') {
                    Enemy whiteWalker = new Monster("White Walker", 6, position, currLevel, 2000, 50, 150, 1000, 'w');
                    board[i][j] = whiteWalker;
                    enemies.add(whiteWalker);
                }
                if (ch == 'M') {
                    Enemy theMountain = new Monster("The Mountain", 6, position, currLevel, 1000, 25, 60, 500, 'M');
                    board[i][j] = theMountain;
                    enemies.add(theMountain);
                }
                if (ch == 'C') {
                    Enemy queenCersei = new Monster("Queen Cersei", 1, position, currLevel, 100, 10, 10, 1000, 'C');
                    board[i][j] = queenCersei;
                    enemies.add(queenCersei);
                }
                if (ch == 'K') {
                    Enemy nightKing = new Monster("Night’s King", 8, position, currLevel, 5000, 150, 300, 5000, 'K');
                    board[i][j] = nightKing;
                    enemies.add(nightKing);
                }
                if (ch == 'B') {
                    Enemy Bonus = new Trap("Bonus Trap",1,5,position,currLevel,1,1,1,250,'B');
                    board[i][j] = Bonus;
                    enemies.add(Bonus);
                }
                if (ch == 'Q') {
                    Enemy queenTrap = new Trap("Queen’s Trap",3,7,position,currLevel,250,10,50,100,'Q');
                    board[i][j] = queenTrap;
                    enemies.add(queenTrap);
                }
                if (ch == 'D') {
                    Enemy deathTrap = new Trap("Death Trap",1,10,position,currLevel,500,20,100,250,'D');
                    board[i][j] = deathTrap;
                    enemies.add(deathTrap);
                }
            }
            i++;
        }
        currLevel.setBoardGame(board);
        currLevel.setEnemyList(enemies);
        return currLevel;
    }
    public static Player[] createPlayers(point position, level firstLevel) {
        Player [] characters = new Player[7];
        characters[0] = new Warrior("Jon Snow", 3, position, firstLevel, 300, 4, 30);
        characters[1] = new Warrior("The Hound", 5, position, firstLevel, 400, 6, 20);
        characters[2] = new Mage("Melisandre", 300, 30, 15, 5, 6, position, firstLevel, 100, 1, 5);
        characters[3] = new Mage("Thoros of Myr", 150, 20, 20, 5, 4, position, firstLevel, 250, 4, 25);
        characters[4] = new Rogue("Arya Stark", 20, position, firstLevel, 150, 2, 40);
        characters[5] = new Rogue("Bronn", 50, position, firstLevel, 250, 3, 50);
        characters[6] = new Hunter("Ygritte",6,position,firstLevel,220,2,30);
        return characters;
    }
    public static void main (String [] args)
    {
        EventHandler messageHandler=new EventHandler();
        Player player = messageHandler.chosePlayer(null,null);

        try
        {
            List<String> levelFiles = Files.list(Paths.get(args[0])).sorted().map(Path::toString).collect(Collectors.toList());
            List<level> levels = new ArrayList<>();
            boolean flag=true;
            for (String levelPath : levelFiles)
            {

                List<String> levelData = Files.readAllLines(Paths.get(levelPath));
                level l = parseLevel(levelData,player,flag);
                levels.add(l);
                flag=false;

            }
            controller control = new controller(levels,player);
            control.play();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }







}