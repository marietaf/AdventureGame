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
    static int i;

    public static CommonCode.CollisionType CheckForCollision(entities.Character character, TiledMap map) {
        Rectangle collisionBox = character.GetCollisionBox();
        float speed = character.GetCharacterStats().GetSpeed();
        CommonCode.CollisionType collisionType = CommonCode.CollisionType.NoCollision;
        switch(character.GetCharacterDirection()){
            case Up:
                for( int i = 0; i < collisionBox.getWidth(); i++ ){
                collisionType = CheckDirectionSpecificCollision(collisionBox.getX() + i,
                        collisionBox.getY() - speed,
                        collisionBox.getX() + i,
                        collisionBox.getY() + collisionBox.getHeight() - speed,
                        map);
                }
                break;

            case Down:
                for( int i = 0; i < collisionBox.getWidth(); i++ ){
                collisionType = CheckDirectionSpecificCollision(collisionBox.getX() + i,
                        collisionBox.getY() + collisionBox.getHeight() + speed,
                        collisionBox.getX() + i,
                        collisionBox.getY() + speed,
                        map);
                }
                break;

            case Left:
                for( int i = 0; i < collisionBox.getHeight(); i++ ){
                collisionType = CheckDirectionSpecificCollision(collisionBox.getX() - speed,
                        collisionBox.getY() + i,
                        collisionBox.getX() + collisionBox.getWidth() - speed,
                        collisionBox.getY() + i,
                        map);
                }
                break;

            case Right:
                for( int i = 0; i < collisionBox.getHeight(); i++ ){
                collisionType = CheckDirectionSpecificCollision(collisionBox.getX() + collisionBox.getWidth() + speed,
                        collisionBox.getY() + i,
                        collisionBox.getX() + speed,
                        collisionBox.getY() + i,
                        map);
                }
                break;

            default:
                //do nothing
                break;
        }
        return collisionType;
    }

    public static CommonCode.CollisionType CheckDirectionSpecificCollision( float collisionX, float collisionY, float doorX, float doorY, TiledMap map){
        CommonCode.CollisionType collisionType = CommonCode.CollisionType.NoCollision;
            if ( isBlocked( collisionX, collisionY, map ) ){
                collisionType = CommonCode.CollisionType.Blocked;
            }
            else if ( !isBlocked( collisionX, collisionY, map ) ){
                collisionType = CommonCode.CollisionType.NoCollision;
            }
            if ( isDoor( doorX, doorY, map ) ){
                collisionType = CommonCode.CollisionType.Door;
            }
        return collisionType;
    }

    public static String getProperty(String name, int tileNumX, int tileNumY, TiledMap map){
        return map.getTileProperty(map.getTileId(tileNumX, tileNumY, 0), name, "noproperty");
    }

    private static boolean isBlocked(float x, float y, TiledMap map){
        return getProperty("blocked", (int) x / CommonCode.MAX_SIZE, (int) y / CommonCode.MAX_SIZE, map).equals("true");
    }

    private static boolean isDoor(float x, float y, TiledMap map){
        return getProperty("door", (int) x / CommonCode.MAX_SIZE, (int) y / CommonCode.MAX_SIZE, map).equals("true");
    }

}
