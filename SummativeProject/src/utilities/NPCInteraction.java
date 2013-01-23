/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import entities.*;
import utilities.CommonCode.CharacterDirection;

/**
 *
 * @author root
 */
public class NPCInteraction {

    Level level;

    public NPCInteraction(Level level){
        this.level = level;
    }

    public void HandleEvents(CharacterDirection direction, long delta){
        boolean collision;
        for (Enemy enemy: level.enemies){
            switch(direction){
                case Up:
                    collision = CheckNPCCollision(direction, enemy);
                    if ( collision = false ){
                        enemy.MoveUp();
                    }
                    break;

                case Down:
                    collision = CheckNPCCollision(direction, enemy);
                    if ( collision = false ){
                        enemy.MoveDown();
                    }
                    break;

                case Left:
                    collision = CheckNPCCollision(direction, enemy);
                    if ( collision = false ){
                        enemy.MoveLeft();
                    }
                    break;

                case Right:
                    collision = CheckNPCCollision(direction, enemy);
                    if ( collision = false ){
                        enemy.MoveRight();
                    }
                    break;
            }
        }

    }

    private boolean CheckNPCCollision(CharacterDirection direction, entities.Character character){
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
