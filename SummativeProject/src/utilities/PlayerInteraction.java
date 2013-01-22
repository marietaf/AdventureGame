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

    public void HandleEvents(int key, long delta){

        //Change player direction depending on key being pressed
        switch(key){
            case Input.KEY_UP:
                level.player.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Up);
                level.player.update(delta);
                break;

            case Input.KEY_DOWN:
                level.player.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Down);
                level.player.update(delta);
                break;

            case Input.KEY_LEFT:
                level.player.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Left);
                level.player.update(delta);
                break;

            case Input.KEY_RIGHT:
                level.player.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Right);
                level.player.update(delta);
                break;
        }

        //Check for collisions
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
                float playerX = level.player.GetCollisionBox().getX();
                float playerY = level.player.GetCollisionBox().getY();
                float doorX = -1, doorY = -1, startX, startY;
                String levelID;
                if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Up)){
                    doorX = playerX + 16;
                    doorY = playerY - 1;
                }
                else if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Down)){
                    doorX = playerX + 16;
                    doorY = playerY + 33;
                }
                else if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Left)){
                    doorX = playerX - 1;
                    doorY = playerY + 16;
                }
                else if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Right)){
                    doorX = playerX + 33;
                    doorY = playerY + 16;
                }
                levelID = utilities.CollisionEvent.getProperty("levelID", (int)(doorX / CommonCode.MAX_SIZE), (int)(doorY / CommonCode.MAX_SIZE), level.GetTiledMap());
                startX = Float.parseFloat( utilities.CollisionEvent.getProperty("startX", (int)(doorX / CommonCode.MAX_SIZE), (int)(doorY / CommonCode.MAX_SIZE), level.GetTiledMap() ) );
                startY = Float.parseFloat( utilities.CollisionEvent.getProperty("startY", (int)(doorX / CommonCode.MAX_SIZE), (int)(doorY / CommonCode.MAX_SIZE), level.GetTiledMap() ) );
                switchLevel = true;
                nextLevelInfo = new NextLevelInformation(levelID, startX, startY);
                break;
        }

        //Check for intersections
        switch( utilities.IntersectionEvent.CheckForIntersection(level) ){
            case NoIntersection:
                break;
                
            case Enemy:
                level.player.GetCharacterStats().SetHealth( level.player.GetCharacterStats().GetHealth() - 1 );
                break;

            case Friendly:
                break;

            case Item:
                break;
        }
    }

    public NextLevelInformation GetNextLevelInfo(){
        return nextLevelInfo;
    }

    public boolean GetSwitchLevel(){
        return switchLevel;
    }
}
