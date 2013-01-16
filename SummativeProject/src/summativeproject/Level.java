/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package summativeproject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author root
 */
public class Level {

    int levelNum;
    TiledMap mapfile;

    Level(int levelNumber, TiledMap map){
        levelNum = levelNumber;
        mapfile = map;
    }
}
