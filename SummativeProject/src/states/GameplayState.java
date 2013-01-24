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

/**
 *
 * @author root
 */
public class GameplayState extends BasicGameState {

    int stateID = 1;
    public String levelID;

    // CHARACTERS ~~~~~~~~
    Player player;
    Enemy greendragon,
          blueslime, blueslime2,
          blackghost,
          reddragon, reddragon2;
    // LEVELS ~~~~~~~~~~~~
    World world;
    TiledMap map1, map2, map3, map4, map5, map6, map7;
    Item[] item1;
    Friendly[] friendly1;
    Enemy[] enemyset1, enemyset2, enemyset3, enemyset4, enemyset5, enemyset6, enemyset7;
    Level[] levels;
    PlayerInteraction playerInteraction;
    NPCInteraction npcInteraction;
    NPCAI npcAI;

    public GameplayState(int stateID){
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return 1;
    }

    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        //INITIALIZE ALL ENTITIES and levels!
        //set defaults

        // CHARACTERS ~~~~~~~~
        int [] duration = {300, 300};
            //Player
            CharacterStats playerStats = new CharacterStats(1.5f, 0, 1);
            player = new Player("data/char2", duration, 100, 100, 7, 10, 18, 22, playerStats);
            //Enemy - greendragon (lvl2)
            CharacterStats greendragonStats = new CharacterStats(0.5f, 3, 1);
            greendragon = new Enemy("data/sprites/greendragon", duration, 224, 200, 7, 10, 18, 22, greendragonStats);
            //Enemy - blueslime
            CharacterStats blueslimeStats = new CharacterStats(0.5f, 3, 1);
            blueslime = new Enemy("data/sprites/blueslime", duration, 300, 300, 7, 10, 18, 22, blueslimeStats);

            CharacterStats blueslime2Stats = new CharacterStats(0.5f, 3, 1);
            blueslime2 = new Enemy("data/sprites/blueslime", duration, 125, 200, 7, 10, 18, 22, blueslime2Stats);

            //Enemy - blackghost
            CharacterStats blackghostStats = new CharacterStats(0.3f, 3, 1);
            blackghost = new Enemy("data/sprites/ghost", duration, 128, 224, 7, 10, 18, 22, blackghostStats);

            //Enemy - blackghost
            CharacterStats reddragonStats = new CharacterStats(0.5f, 3, 1);
            reddragon = new Enemy("data/sprites/reddragon", duration, 32, 192, 7, 10, 18, 22, reddragonStats);

            CharacterStats reddragon2Stats = new CharacterStats(0.5f, 3, 1);
            reddragon2 = new Enemy("data/sprites/reddragon", duration, 416, 192, 7, 10, 18, 22, reddragon2Stats);

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
            map2 = new TiledMap("data/map1v2.tmx");
            enemyset2 = new Enemy[1];
            enemyset2[0] = greendragon;
            levels[1] = new Level("2", map2, item1, enemyset2, friendly1, player);

            //LEVEL THREE
            map3 = new TiledMap("data/cave1.tmx");
            enemyset3 = new Enemy[1];
            enemyset3[0] = blueslime;
            levels[2] = new Level ("3", map3, item1, enemyset3, friendly1, player);

            //LEVEL FOUR
            map4 = new TiledMap("data/cave2.tmx");
            levels[3] = new Level("4", map4, item1, enemyset4, friendly1, player);
            enemyset4 = new Enemy[2];
            enemyset4[0] = blueslime2;
            enemyset4[1] = blueslime;

            //LEVEL FIVE
            map5 = new TiledMap("data/town1.tmx");
            enemyset5 = new Enemy[0];
            levels[4] = new Level("5", map5, item1, enemyset5, friendly1, player);


            //LEVEL SIX
            map6 = new TiledMap("data/Forest.tmx");
            enemyset6 = new Enemy[1];
            enemyset6[0] = blackghost;
            levels[5] = new Level("6", map6, item1, enemyset6, friendly1, player);


            //LEVEL SEVEN
            map7 =new TiledMap("data/Endgame.tmx");
            enemyset7 = new Enemy[2];
            enemyset7[0] = reddragon;
            enemyset7[1] = reddragon2;
            levels[6] = new Level("7", map7, item1, enemyset7, friendly1, player);

            

        world.InitializeFirstLevel();
        npcAI = new NPCAI(world.GetCurrentLevel());

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
