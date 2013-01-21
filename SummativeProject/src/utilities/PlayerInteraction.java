/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import org.newdawn.slick.Input;


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

    public void HandleEvents(int key){
        switch(key){
            case Input.KEY_UP:
                level.player.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Up);
                break;

            case Input.KEY_DOWN:
                level.player.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Down);
                break;

            case Input.KEY_LEFT:
                level.player.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Left);
                break;

            case Input.KEY_RIGHT:
                level.player.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Right);
                break;
        }

        switch( utilities.CollisionEvent.CheckForCollision(level.GetPlayer(), level.GetTiledMap()) ){
            case NoCollision:
                if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Up)){
                    level.player.MoveUp();
                }
                else if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Down)) {
                    level.player.MoveDown();
                }
                else if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Left)) {
                    level.player.MoveLeft();
                }
                else if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Right)) {
                    level.player.MoveRight();
                }
                break;

            case Blocked:
                break;

            case Door:
                
                break;
        }
    }

    public NextLevelInformation GetNextLevelInfo(){
        return nextLevelInfo;
    }

    public boolean SwitchLevel(){
        return switchLevel;
    }
}
