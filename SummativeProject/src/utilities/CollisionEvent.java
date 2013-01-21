/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import org.newdawn.slick.tiled.TiledMap;
import entities.Character.*;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author root
 */
public class CollisionEvent {

    CommonCode.CharacterDirection currentDirection;
    Character character;
    NextLevelInformation nextLevelInfo;
    CommonCode.CollisionType collisionType;

    public static CommonCode.CollisionType CheckForCollision(entities.Character character, TiledMap map) {
        Rectangle collisionBox = character.GetCollisionBox();
        float speed = character.GetCharacterStats().GetSpeed();
        int i = 0;
        CommonCode.CollisionType collisionType = CommonCode.CollisionType.NoCollision;
        switch(character.GetCharacterDirection()){
            case Up:
                collisionType = CheckDirectionSpecificCollision(collisionBox.getWidth(),
                        collisionBox.getX() + i,
                        collisionBox.getY() - speed,
                        collisionBox.getX() + i,
                        collisionBox.getY() + collisionBox.getHeight() - speed,
                        map);
                break;

            case Down:
                collisionType = CheckDirectionSpecificCollision(collisionBox.getWidth(),
                        collisionBox.getX() + i,
                        collisionBox.getY() + collisionBox.getHeight() + speed,
                        collisionBox.getX() + i,
                        collisionBox.getY() + speed,
                        map);
                break;

            case Left:
                collisionType = CheckDirectionSpecificCollision(collisionBox.getHeight(),
                        collisionBox.getX() - speed,
                        collisionBox.getY() + i,
                        collisionBox.getX() + collisionBox.getWidth() - speed,
                        collisionBox.getY() + i,
                        map);
                break;

            case Right:
                collisionType = CheckDirectionSpecificCollision(collisionBox.getHeight(),
                        collisionBox.getX() + collisionBox.getWidth() + speed,
                        collisionBox.getY() + i,
                        collisionBox.getX() + speed,
                        collisionBox.getY() + i,
                        map);
                break;

            default:
                //do nothing
                break;
        }
        return collisionType;
    }

    public static CommonCode.CollisionType CheckDirectionSpecificCollision( float collisionDimension, float collisionX, float collisionY, float doorX, float doorY, TiledMap map){
        CommonCode.CollisionType collisionType = CommonCode.CollisionType.NoCollision;
        for (int i = 0; i < collisionDimension; i++){
            if ( isBlocked( collisionX, collisionY, map ) ){
                collisionType = CommonCode.CollisionType.Blocked;
            }
            else if ( !isBlocked( collisionX, collisionY, map ) ){
                collisionType = CommonCode.CollisionType.NoCollision;
            }
            if ( isDoor( doorX, doorY, map ) ){
                collisionType = CommonCode.CollisionType.Door;
            }
        }
        return collisionType;
    }

    private static String getProperty(String name, int tileNumX, int tileNumY, TiledMap map){
        return map.getTileProperty(map.getTileId(tileNumX, tileNumY, 0), name, "null");
    }

    private static boolean isBlocked(float x, float y, TiledMap map){
        return getProperty("blocked", (int) x / CommonCode.MAX_SIZE, (int) y / CommonCode.MAX_SIZE, map).equals("true");
    }

    private static boolean isDoor(float x, float y, TiledMap map){
        return getProperty("door", (int) x / CommonCode.MAX_SIZE, (int) y / CommonCode.MAX_SIZE, map).equals("true");
    }

    public NextLevelInformation GetNextLevelInfo(){
        return nextLevelInfo;
    }

}
