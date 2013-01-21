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
    boolean collision;

    public static boolean CheckForCollision(entities.Character character, TiledMap map) {
        Rectangle collisionBox = character.GetCollisionBox();
        float speed = character.GetCharacterStats().GetSpeed();
        CommonCode.CollisionType collisionType;
        switch(character.GetCharacterDirection()){
            case Up:
                collisionType = CheckDirectionSpecificCollision(collisionBox.getWidth(),
                        collisionBox.getX(),
                        collisionBox.getY() - speed,
                        collisionBox.getX(),
                        collisionBox.getY() + collisionBox.getHeight() - speed,
                        map);
                break;

            case Down:
                collisionType = CheckDirectionSpecificCollision(collisionBox.getWidth(),
                        collisionBox.getX(),
                        collisionBox.getY() + collisionBox.getHeight() + speed,
                        collisionBox.getX(),
                        collisionBox.getY() + speed,
                        map);
                break;

            case Left:
                collisionType = CheckDirectionSpecificCollision(collisionBox.getHeight(),
                        collisionBox.getX(),
                        collisionBox.getY() - speed,
                        collisionBox.getX(),
                        collisionBox.getY() + collisionBox.getHeight() - speed,
                        map);
                break;

            case Right:
                collisionType = CheckDirectionSpecificCollision(collisionBox.getHeight(),
                        collisionBox.getX(),
                        collisionBox.getY() - speed,
                        collisionBox.getX(),
                        collisionBox.getY() + collisionBox.getHeight() - speed,
                        map);
                break;

            default:
                //do nothing
                break;
        }
        return collision;
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
