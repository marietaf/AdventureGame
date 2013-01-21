/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package states;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Image;

/**
 *
 * @author root
 */
public class GameplayState extends BasicGameState {

    int stateID = 1;

    public String levelID;

    //initializes the grass map
    private TiledMap map;
    TiledMap[] levelMap = new TiledMap[9];

    //initializes the animation for the sprite
    private Animation sprite, up, down, left, right;
    private Animation enemy1, enemy1up, enemy1down, enemy1left, enemy1right;

    //position of the sprite - initial at (x,y)
    private float x = 224f, y = 96f;
    private float enemy1x = 224f, enemy1y = 160f;

    //Tile size is 32x32
    private static final int SIZE = 32;

    public static long startTime;

    public GameplayState(int stateID){
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return 1;
    }

    private void gameOver(StateBasedGame sbg){
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

    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        //INITIALIZE ALL ENTITIES and levels!
        //set defaults

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //starts on level 1
        levelID = "1";

        //initilialize maps (map = current map)
        map = new TiledMap("data/testmap3.tmx");
        levelMap[0] = new TiledMap("data/testmap.tmx");
        levelMap[1] = new TiledMap("data/testmap2.tmx");
        levelMap[2] = new TiledMap("data/testmap3.tmx");

        //initialize images for the game
        //images for sprite
        Image [] movementUp = {new Image("data/char2_bk1.gif"), new Image("data/char2_bk2.gif")};
        Image [] movementDown = {new Image("data/char2_fr1.gif"), new Image("data/char2_fr2.gif")};
        Image [] movementLeft = {new Image("data/char2_lf1.gif"), new Image("data/char2_lf2.gif")};
        Image [] movementRight = {new Image("data/char2_rt1.gif"), new Image("data/char2_rt2.gif")};
        Image [] enemy1UP = {new Image("data/char_bk1.gif"), new Image("data/char_bk2.gif")};
        Image [] enemy1DOWN = {new Image("data/char_fr1.gif"), new Image("data/char_fr2.gif")};
        Image [] enemy1LEFT = {new Image("data/char_lf1.gif"), new Image("data/char_lf2.gif")};
        Image [] enemy1RIGHT = {new Image("data/char_rt1.gif"), new Image("data/char_rt2.gif")};

        //the time between updates on sprites
        int [] duration = {300, 300};

        //false means it will only update when the user presses the key
        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);
        enemy1up = new Animation(enemy1UP, duration, true);
        enemy1down = new Animation(enemy1DOWN, duration, true);
        enemy1left = new Animation(enemy1LEFT, duration, true);
        enemy1right = new Animation(enemy1RIGHT, duration, true);

        //tells what the original postition of the sprite is
        sprite = down;
        enemy1 = enemy1down;
    }

    public String getProperty(String name, int x, int y){
        return map.getTileProperty(map.getTileId(x, y, 0), name, "false");
    }

    //if the tile property of "blocked" at coords x and y are true, return true
    public boolean isBlocked(float x, float y){
        return getProperty("blocked", (int) x / SIZE, (int) y / SIZE).equals("true");
    }

    //if the tile property of "door" at coords x and y are true, return true
    public boolean isDoor(float x, float y){
        return getProperty("door", (int) x / SIZE, (int) y / SIZE).equals("true");
    }

    public void levelChange(int xPos, int yPos) throws SlickException{
        //get the levelID from the .tmx file (which level it will be going to)
        levelID = map.getTileProperty(map.getTileId((xPos / SIZE), (yPos / SIZE), 0), "levelID", "0");
        //switch map depending on levelID and x and y starting coordinates
        if(levelID.equals("1")){
            map = levelMap[1];
            x = 224;
            y = 384;
        }
        else if(levelID.equals("2")){
           map = levelMap[2];
           x = 224;
           y = 416;
        }
    }

    //@Override = Annotation that says it is overriding/changing method of a parent class
    //updates the game when it gets input from user
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        //KEY BINDINGS to PlayerInteraction.HandleEvents()
        // check if needs to change levels SwitchLevel();
        //if true, then GetCurrentLevel();

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Input input = container.getInput();

        //SPRITE MOVEMENT ~~~~~~~~~~~~~~~~~~~~~~~~~
        //xSIZE is sprite width
        //excesshalfsxSIZE is the sides of the sprite image (since it has to equal 32px)
        int xSIZE = 18;
        int excesshalfsxSIZE = 7;
        //ySIZE is sprite height
        //excessySIZE is the 10 other pixels above the sprite's original size (since it has to equal 32px)
        int ySIZE = 22;
        int excessySIZE = 10;
        boolean collision = false;
        //the lower the delta the slower the sprite will update
        float speed = 0.1f;
        //((x or y) - delta * speed) is a way of making up for the difference in
        //  update times so that when there is a larger gap (larger delta) then
        //  the speed will be greater to compensate for this difference.
        if(input.isKeyDown(Input.KEY_UP)){
            sprite = up;
            //check space in front of sprite for different tile properties
            for(int j = 0; j < xSIZE; j++){
                //check x+i because it checks from top left of sprite, which may miss walls to the right of it
                if(isBlocked(x + excesshalfsxSIZE + j, y + excessySIZE - delta * speed)){
                    collision = true;
                    j = xSIZE;
                }
                if(isDoor(x + excesshalfsxSIZE + j, y + excessySIZE + ySIZE + delta * speed)){
                    levelChange((int)(x + excesshalfsxSIZE + j), (int)(y + excessySIZE - delta * speed));
                }
            }
            if(!collision){
                sprite.update(delta);
                y -= delta * speed;
            }
        }
        else if(input.isKeyDown(Input.KEY_DOWN)){
            sprite = down;
            if(!isBlocked(x + excesshalfsxSIZE, y + excessySIZE + ySIZE + delta * speed)){
                collision = false;
            }
            for(int j = 0; j < xSIZE; j++){
                if(isBlocked(x + excesshalfsxSIZE + j, y + excessySIZE + ySIZE + delta * speed)){
                    collision = true;
                    j = SIZE;
                }
                if(isDoor(x + excesshalfsxSIZE + j, y + excessySIZE - delta * speed)){
                    levelChange((int)(x + excesshalfsxSIZE), (int)(y + excessySIZE + ySIZE + delta * speed));
                }
            }
            if(!collision){
                sprite.update(delta);
                y += delta * speed;
            }
        }
        else if(input.isKeyDown(Input.KEY_LEFT)){
            sprite = left;
            if(!isBlocked(x + excesshalfsxSIZE - delta * speed, y + excessySIZE)){
                collision = false;
            }
            for(int j = 0; j < ySIZE; j++){
                if(isBlocked(x + excesshalfsxSIZE - delta * speed, y + excessySIZE + j)){
                    collision = true;
                    j = ySIZE;
                }
                if(isDoor(x + (excesshalfsxSIZE + xSIZE) + delta * speed, y + excessySIZE + j)){
                    levelChange((int)(x + excesshalfsxSIZE - delta * speed), (int)(y + excessySIZE));
                }
            }
            if(!collision){
                sprite.update(delta);
                x -= delta * speed;
            }
        }
        else if(input.isKeyDown(Input.KEY_RIGHT)){
            sprite = right;
            if(!isBlocked(x + (excesshalfsxSIZE + xSIZE) + delta * speed, y + excessySIZE)){
                collision = false;
            }
            for (int j = 0; j < ySIZE; j++){
                if(isBlocked(x + (excesshalfsxSIZE + xSIZE) + delta * speed, y + excessySIZE + j)){
                    collision = true;
                    j = ySIZE;
                }
                if(isDoor(x + excesshalfsxSIZE - delta * speed, y + excessySIZE + j)){
                    levelChange((int)(x + (excesshalfsxSIZE + xSIZE) + delta * speed), (int)(y + excessySIZE));
                }
            }
            if(!collision){
                sprite.update(delta);
                x += delta * speed;
            }
        }

        //enemy MOVEMENT ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        float enemySpeed = 0.5f;

        if(enemy1x < x){
            enemy1 = enemy1right;
            enemy1x += enemySpeed;
        }
        if(enemy1x > x){
            enemy1 = enemy1left;
            enemy1x -= enemySpeed;
        }
        if(enemy1y < y){
            enemy1 = enemy1down;
            enemy1y += enemySpeed;
        }
        if(enemy1y > y){
            enemy1 = enemy1up;
            enemy1y -= enemySpeed;
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        //world.render();

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        map.render(0, 0);
        sprite.draw((int)x, (int)y);
        enemy1.draw((int)enemy1x, (int)enemy1y);
    }
}
