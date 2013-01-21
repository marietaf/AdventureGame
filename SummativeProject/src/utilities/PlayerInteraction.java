/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;


/**
 *
 * @author root
 */
public class PlayerInteraction {

    boolean switchLevel = false;
    NextLevelInformation nextLevelInfo;
    Level level;

    public PlayerInteraction(Level level){
        this.level = level;
    }

    public void HandleEvents(){
        if( utilities.CollisionEvent.CheckForCollision(level.GetPlayer(), level.GetTiledMap()) ){

        }
    }

    public NextLevelInformation GetNextLevelInfo(){
        return nextLevelInfo;
    }

    public boolean SwitchLevel(){
        return switchLevel;
    }
}
