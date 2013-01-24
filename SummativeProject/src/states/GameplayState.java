/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package states;

import entities.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import utilities.CharacterStats;
import utilities.Level;
import utilities.NPCAI;
import utilities.NPCInteraction;
import utilities.PlayerInteraction;
import utilities.World;

/*
 * TO DO:
 * - startx and starty
 */

/**
 *
 * @author root
 */
public class GameplayState extends BasicGameState {

    int stateID = 1;
    public String levelID;

    // CHARACTERS ~~~~~~~~
    Player player;
    Enemy greendragon, blueslime;
    // LEVELS ~~~~~~~~~~~~
    World world;
    //ADD WHEN ADDING LEVELS
    TiledMap map1, map2, map3, map4, map5, map6, map7;
    Item[] item1;
    Friendly[] friendly1;
    Enemy[] enemyset1, enemyset2, enemyset3, enemyset4, enemyset5, enemyset6, enemyset7;
    Level[] levels;
    PlayerInteraction playerInteraction;
    NPCInteraction npcInteraction;
    NPCAI npcAI;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public GameplayState(int stateID){
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return 1;
    }
    
    /*
    public static long startTime;

    private void gameOver(StateBasedGame sbg){
        //isiinitiallevel = true

        levelID = "1";
        map = levelMap[1];
        sprite = down;
        enemy1 = enemy1down;
        x = 224;
        y = 96;
        enemy1x = 224;
        enemy1y = 160;

        startTime = System.currentTimeMillis();
        sbg.enterState(2);
    }
    */

    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        //INITIALIZE ALL ENTITIES and levels!
        //set defaults

        // CHARACTERS ~~~~~~~~
        int [] duration = {300, 300};
            //Player
            CharacterStats playerStats = new CharacterStats(1.5f, 0, 1);
            player = new Player("data/char2", duration, 224, 384, 7, 10, 18, 22, playerStats);
            //Enemy - greendragon (lvl2)
            CharacterStats greendragonStats = new CharacterStats(0.5f, 3, 1);
            greendragon = new Enemy("data/sprites/greendragon", duration, 224, 200, 7, 10, 18, 22, greendragonStats);
            //Enemy - greendragon
            CharacterStats blueslimeStats = new CharacterStats(0.5f, 3, 1);
            blueslime = new Enemy("data/sprites/blueslime", duration, 224, 200, 7, 10, 18, 22, blueslimeStats);
            
        // LEVELS ~~~~~~~~~~

        //CHANGE NUMBER OF LEVELS IN ARRAY
        levels = new Level[7];
        world = new World("1", 50, 100, levels);

            //LEVEL ONE
            map1 = new TiledMap("data/room1.tmx");
            item1 = new Item[0];
            friendly1 = new Friendly[0];
            enemyset1 = new Enemy[0];
            levels[0] = new Level("1", map1, item1, enemyset1, friendly1, player);

            //LEVEL TWO
<<<<<<< HEAD
            map2 = new TiledMap("data/map1v2.tmx");
            enemyset2 = new Enemy[0];
=======
            map2 = new TiledMap("data/Map1.tmx");
            enemyset2 = new Enemy[1];
            enemyset2[0] = greendragon;
>>>>>>> Added Enemies to Level 1 and 2 & Started WinState
            levels[1] = new Level("2", map2, item1, enemyset2, friendly1, player);

            //LEVEL THREE
            map3 = new TiledMap("data/cave1.tmx");
            enemyset3 = new Enemy[1];
            enemyset3[0] = blueslime;
            levels[2] = new Level ("3", map3, item1, enemyset3, friendly1, player);

            //LEVEL FOUR
<<<<<<< HEAD
            map4 = new TiledMap("data/cave2.tmx");
            levels[3] = new Level("4", map4, item1, enemyset2, friendly1, player);
=======
            map4 = new TiledMap("data/cave2v2.tmx");
            enemyset4 = new Enemy[0];
            levels[3] = new Level("4", map4, item1, enemyset4, friendly1, player);
>>>>>>> Added Enemies to Level 1 and 2 & Started WinState

            //LEVEL FIVE
            map5 = new TiledMap("data/town1.tmx");
            enemyset5 = new Enemy[0];
            levels[4] = new Level("5", map5, item1, enemyset5, friendly1, player);


            //LEVEL SIX
            map6 = new TiledMap("data/Forest.tmx");
            enemyset6 = new Enemy[0];
            levels[5] = new Level("6", map6, item1, enemyset6, friendly1, player);


            //LEVEL SEVEN
            map7 =new TiledMap("data/Endgame.tmx");
            enemyset7 = new Enemy[0];
            levels[6] = new Level("7", map7, item1, enemyset7, friendly1, player);

            

        world.InitializeFirstLevel();
        npcAI = new NPCAI(world.GetCurrentLevel());
            
        
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        //initilialize maps (map = current map)


        //initialize images for the game
        //images for sprite
        

        //the time between updates on sprites
        

        //false means it will only update when the user presses the key
        

        //tells what the original postition of the sprite is

    }

    //@Override = Annotation that says it is overriding/changing method of a parent class
    //updates the game when it gets input from user
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        Input input = container.getInput();
        //KEY BINDINGS to PlayerInteraction.HandleEvents()
        // check if needs to change levels SwitchLevel();
        //if true, then GetCurrentLevel();

        npcAI.RunAI(delta);
        world.UpdateWorld(delta, npcAI);

        if(input.isKeyDown(Input.KEY_UP)){
            world.UpdatePlayer(Input.KEY_UP, delta, sbg);
        }
        else if(input.isKeyDown(Input.KEY_DOWN)){
            world.UpdatePlayer(Input.KEY_DOWN, delta, sbg);
        }
        else if(input.isKeyDown(Input.KEY_LEFT)){
            world.UpdatePlayer(Input.KEY_LEFT, delta, sbg);
        }
        else if(input.isKeyDown(Input.KEY_RIGHT)){
            world.UpdatePlayer(Input.KEY_RIGHT, delta, sbg);
        }

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        

        //((x or y) - delta * speed) is a way of making up for the difference in
        //  update times so that when there is a larger gap (larger delta) then
        //  the speed will be greater to compensate for this difference.
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        world.RenderCurrentLevel();
        for(Enemy enemy: world.GetEnemyArray()){
            enemy.GetCharacterRenderableDirection().draw(enemy.GetX(), enemy.GetY());
        }
        player.GetCharacterRenderableDirection().draw(player.GetX(), player.GetY());
    }
}
