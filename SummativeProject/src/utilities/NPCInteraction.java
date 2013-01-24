/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import entities.*;

/**
 *
 * @author root
 */
public class NPCInteraction {

    Level level;
    CommonCode.CharacterDirection direction;
    NPCAI npcAI;

    public NPCInteraction(Level level, NPCAI npcAI){
        this.level = level;
    }

    public void HandleEvents(long delta){
        boolean collision;
        for (Enemy enemy: level.enemies){
            CommonCode.CharacterDirection horizontalDirection;
            CommonCode.CharacterDirection verticalDirection;
            if (level.player.GetY() < enemy.GetY()){
                 enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Up);
                    collision = CheckNPCCollision(enemy);
                    if ( !collision ){
                        enemy.MoveUp();
                    }
                    enemy.update(delta);
            }
            else {
                 enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Down);
                    collision = CheckNPCCollision(enemy);
                    if ( !collision ){
                        enemy.MoveDown();
                    }
                    enemy.update(delta);
            }

            if (level.player.GetX() < enemy.GetX()){
                enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Left);
                    collision = CheckNPCCollision(enemy);
                    if ( !collision ){
                        enemy.MoveLeft();
                    }
                    enemy.update(delta);
            }
            else {
                enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Right);
                    collision = CheckNPCCollision(enemy);
                    if ( !collision ){
                        enemy.MoveRight();
                    }
                    enemy.update(delta);
            }
            /*
            switch(enemy.GetCharacterDirection()){
                case Up:
                    enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Up);
                    collision = CheckNPCCollision(enemy);
                    if ( !collision ){
                        enemy.MoveUp();
                    }
                    enemy.update(delta);
                    break;

                case Down:
                    enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Down);
                    collision = CheckNPCCollision(enemy);
                    if ( !collision ){
                        enemy.MoveDown();
                    }
                    enemy.update(delta);
                    break;

                case Left:
                    enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Left);
                    collision = CheckNPCCollision(enemy);
                    if ( !collision ){
                        enemy.MoveLeft();
                    }
                    enemy.update(delta);
                    break;

                case Right:
                    enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Right);
                    collision = CheckNPCCollision(enemy);
                    if ( !collision ){
                        enemy.MoveRight();
                    }
                    enemy.update(delta);
                    break;
            }
             * 
             */
        }

    }

    private boolean CheckNPCCollision(entities.Character character){
        boolean collision;
        switch( utilities.CollisionEvent.CheckForCollision( character, level.GetTiledMap() ) ){
            case NoCollision:
                collision = false;
                return collision;

            case Blocked:
                collision = true;
                return collision;

            case Door:
                collision = true;
                return collision;
        }
        collision = true;
        return collision;
    }

}
