/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package states;

import entities.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Image;
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
    private Animation up, down, left, right, enemy1up, enemy1down, enemy1left, enemy1right;
    Player player;
    Enemy enemy1;
    // LEVELS ~~~~~~~~~~~~
    World world;
    //ADD WHEN ADDING LEVELS
    TiledMap map1, map2, map3, map4, map5, map6, map7;
    Item[] item1;
    Friendly[] friendly1;
    Enemy[] enemyset1, enemyset2;
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
            Image [] movementUp = {new Image("data/char2_bk1.gif"), new Image("data/char2_bk2.gif")};
            Image [] movementDown = {new Image("data/char2_fr1.gif"), new Image("data/char2_fr2.gif")};
            Image [] movementLeft = {new Image("data/char2_lf1.gif"), new Image("data/char2_lf2.gif")};
            Image [] movementRight = {new Image("data/char2_rt1.gif"), new Image("data/char2_rt2.gif")};
            up = new Animation (movementUp, duration, false);
            down = new Animation(movementDown, duration, false);
            left = new Animation(movementLeft, duration, false);
            right = new Animation(movementRight, duration, false);
            CharacterStats playerStats = new CharacterStats(1.5f, 3, 1);
            player = new Player("data/char2", duration, 224, 384, 7, 10, 18, 22, playerStats);
            //Enemy
            CharacterStats enemy1Stats = new CharacterStats(1.5f, 3, 1);
            //enemy1 = new Enemy(enemy1up, enemy1down, enemy1left, enemy1right, 224, 200, 7, 10, 18, 22, enemy1Stats);
            enemy1 = new Enemy("data/char2", duration, 224, 200, 7, 10, 18, 22, enemy1Stats);
            
        // LEVELS ~~~~~~~~~~

        //CHANGE NUMBER OF LEVELS IN ARRAY
        levels = new Level[6];
        world = new World("1", 256, 256, levels);
            //LEVEL ONE
            map1 = new TiledMap("data/room1.tmx");
            item1 = new Item[0];
            friendly1 = new Friendly[0];
            enemyset1 = new Enemy[1];
            enemyset1[0] = enemy1;
            levels[0] = new Level("1", map1, item1, enemyset1, friendly1, player);
            //LEVEL TWO
            map2 = new TiledMap("data/Map1.tmx");
            enemyset2 = new Enemy[0];
            levels[1] = new Level("2", map2, item1, enemyset2, friendly1, player);
            //LEVEL THREE
            map3 = new TiledMap("");

            //LEVEL FOUR
            map4 = new TiledMap("data/cave2.tmx");
            levels[3] = new Level("4", map4, item1, enemyset2, friendly1, player);

            //LEVEL FIVE
            map5 = new TiledMap("data/town1.tmx");
            levels[4] = new Level("5", map5, item1, enemyset2, friendly1, player);


            //LEVEL SIX




            //LEVEL SEVEN


            

        world.InitializeFirstLevel();
            
        
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

        
        if(input.isKeyDown(Input.KEY_UP)){
            world.UpdateWorld(Input.KEY_UP, delta, npcAI);
        }
        else if(input.isKeyDown(Input.KEY_DOWN)){
            world.UpdateWorld(Input.KEY_DOWN, delta, npcAI);
        }
        else if(input.isKeyDown(Input.KEY_LEFT)){
            world.UpdateWorld(Input.KEY_LEFT, delta, npcAI);
        }
        else if(input.isKeyDown(Input.KEY_RIGHT)){
            world.UpdateWorld(Input.KEY_RIGHT, delta, npcAI);
        }

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        

        //((x or y) - delta * speed) is a way of making up for the difference in
        //  update times so that when there is a larger gap (larger delta) then
        //  the speed will be greater to compensate for this difference.
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        world.RenderCurrentLevel();
        player.GetCharacterRenderableDirection().draw(player.GetX(), player.GetY());
        enemy1.GetCharacterRenderableDirection().draw(enemy1.GetX(), enemy1.GetY());
        

        //levels[0].Render();
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
    }
}
