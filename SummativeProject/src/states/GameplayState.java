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
    private Animation up, down, left, right;
    Player player;
    // LEVELS ~~~~~~~~~~~~
    World world;
    //ADD WHEN ADDING LEVELS
    TiledMap map1, map2;
    Item[] item1;
    Friendly[] friendly1;
    Enemy[] enemy1;
    Level[] levels;
    PlayerInteraction playerInteraction;

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
            player = new Player(up, down, left, right, 224, 384, 7, 10, 18, 22, playerStats);
            //Enemy
            
        // LEVELS ~~~~~~~~~~

        //CHANGE NUMBER OF LEVELS IN ARRAY
        levels = new Level[2];
        world = new World("1", 224, 384, levels);
            //LEVEL ONE
            map1 = new TiledMap("data/testmap2.tmx");
            item1 = new Item[0];
            friendly1 = new Friendly[0];
            enemy1 = new Enemy[0];
            levels[0] = new Level("1", map1, item1, enemy1, friendly1, player);
            //LEVEL TWO
            map2 = new TiledMap("data/testmap3.tmx");
            levels[1] = new Level("2", map2, item1, enemy1, friendly1, player);
            //LEVEL THREE

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
            world.UpdateWorld(Input.KEY_UP, delta);
        }
        else if(input.isKeyDown(Input.KEY_DOWN)){
            world.UpdateWorld(Input.KEY_DOWN, delta);
        }
        else if(input.isKeyDown(Input.KEY_LEFT)){
            world.UpdateWorld(Input.KEY_LEFT, delta);
        }
        else if(input.isKeyDown(Input.KEY_RIGHT)){
            world.UpdateWorld(Input.KEY_RIGHT, delta);
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
        

        //levels[0].Render();
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
    }
}
