/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import org.newdawn.slick.geom.Rectangle;
import entities.*;

/**
 *
 * @author root
 */
public class IntersectionEvent {

    public static CommonCode.IntersectionType CheckForIntersection(Level level) {
        Rectangle entityCollisionBox;
        CommonCode.IntersectionType intersectionType;
        Rectangle characterCollisionBox = level.player.GetCollisionBox();
        intersectionType = CommonCode.IntersectionType.NoIntersection;
        
        for ( Enemy enemy: level.enemies ){
            entityCollisionBox = enemy.GetCollisionBox();
            if( characterCollisionBox.intersects(entityCollisionBox) ){
                intersectionType = CommonCode.IntersectionType.Enemy;
            }
        }
        for ( Friendly friendly: level.friendlies ){
            entityCollisionBox = friendly.GetCollisionBox();
            if( characterCollisionBox.intersects(entityCollisionBox) ){
                intersectionType = CommonCode.IntersectionType.Friendly;
            }
        }
        for ( Item item: level.items ){
            entityCollisionBox = item.GetCollisionBox();
            if( characterCollisionBox.intersects(entityCollisionBox) ){
                intersectionType = CommonCode.IntersectionType.Item;
            }
        }
        return intersectionType;
    }
}
