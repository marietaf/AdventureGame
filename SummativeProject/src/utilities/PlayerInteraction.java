/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;


/**
 *
 * @author root
 */
public class PlayerInteraction {

    boolean switchLevel = false;
    boolean gameOver = false;
    boolean gameWin = false;
    NextLevelInformation nextLevelInfo;
    Level level;
    int playerHitCountdown;

    public PlayerInteraction(Level level){
        this.level = level;
    }

    public void HandleEvents(int key, long delta){

        UpdatePlayerHitCountdown();

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
                Rectangle collisionBox = level.player.GetCollisionBox();
                float playerSpeed = level.player.GetCharacterStats().GetSpeed();
                float doorX = -1, doorY = -1, startX, startY;
                String levelID;
                if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Up)){
                    doorX = playerX + (collisionBox.getWidth()/2);
                    doorY = playerY + (collisionBox.getHeight()/2) - playerSpeed;
                }
                else if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Down)){
                    doorX = playerX + (collisionBox.getWidth()/2);
                    doorY = playerY + (collisionBox.getHeight()/2) + playerSpeed;
                }
                else if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Left)){
                    doorX = playerX + (collisionBox.getWidth()/2) - playerSpeed;
                    doorY = playerY + (collisionBox.getHeight()/2);
                }
                else if(level.player.GetCharacterDirection().equals(CommonCode.CharacterDirection.Right)){
                    doorX = playerX + (collisionBox.getWidth()/2) + playerSpeed;
                    doorY = playerY + (collisionBox.getHeight()/2);
                }
                levelID = utilities.CollisionEvent.getProperty("levelID", (int)(doorX / CommonCode.MAX_SIZE), (int)(doorY / CommonCode.MAX_SIZE), level.GetTiledMap());
                startX = Float.parseFloat( utilities.CollisionEvent.getProperty("startX", (int)(doorX / CommonCode.MAX_SIZE), (int)(doorY / CommonCode.MAX_SIZE), level.GetTiledMap() ) );
                startY = Float.parseFloat( utilities.CollisionEvent.getProperty("startY", (int)(doorX / CommonCode.MAX_SIZE), (int)(doorY / CommonCode.MAX_SIZE), level.GetTiledMap() ) );
                switchLevel = true;
                nextLevelInfo = new NextLevelInformation(levelID, startX, startY);
                break;
        }

        CheckPlayerIntersections();
        
        if ( level.GetLevelID().equals("7") && 
             level.player.GetX() >= 192 && level.player.GetX() <= 288 &&
             level.player.GetY() >= 347 && level.player.GetY() <= 416 ){
            gameWin = true;
        }
    }

    public void HandleEvents(){
        UpdatePlayerHitCountdown();

        CheckPlayerIntersections();

        if ( level.GetLevelID().equals("7") &&
             level.player.GetX() >= 192 && level.player.GetX() <= 288 &&
             level.player.GetY() >= 347 && level.player.GetY() <= 416 ){
            gameWin = true;
        }
    }

    public void CheckPlayerIntersections(){
        //Check for intersections
        switch( utilities.IntersectionEvent.CheckForIntersection(level) ){
            case NoIntersection:
                break;

            case Enemy:
                if (playerHitCountdown <= 0){
                    level.player.TakeDamage(1);
                    int health = level.player.GetCharacterStats().GetHealth();
                    if ( health == 0 ){
                        gameOver = true;
                    }
                    playerHitCountdown = 60;
                }
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

    public boolean GetGameOver(){
        return gameOver;
    }

    public boolean GetGameWin(){
        return gameWin;
    }

    public void SetPlayerHitCountdown(int countdownNum){
        playerHitCountdown = countdownNum;
    }

    public void UpdatePlayerHitCountdown(){
        playerHitCountdown--;
    }
}
