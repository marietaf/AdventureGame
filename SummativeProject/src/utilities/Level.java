/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import entities.*;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author root
 */
public class Level {

    String levelID;
    TiledMap map;
    Item[] items;
    Enemy[] enemies;
    Friendly[] friendlies;
    Player player;

    public Level(String levelID, TiledMap map, Item[] items, Enemy[] enemies, Friendly[] friendlies, Player player){
        this.levelID = levelID;
        this.map = map;
        this.items = items;
        this.enemies = enemies;
        this.friendlies = friendlies;
        this.player = player;
    }

    public void Render(){
        map.render(0, 0);
        for(Item i: items){
            i.Render();
        }
        for(Enemy i: enemies){
            i.Render();
        }
        for(Friendly i: friendlies){
            i.Render();
        }
        //player.Render();
    }

    public TiledMap GetTiledMap(){
        return map;
    }

    public Player GetPlayer(){
        return player;
    }

    public String GetLevelID(){
        return levelID;
    }
}
