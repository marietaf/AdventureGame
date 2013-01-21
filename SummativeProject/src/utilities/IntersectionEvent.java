/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author root
 */
public class IntersectionEvent {

    Rectangle characterCollisionBox, entityCollisionBox;
    boolean intersection;

    public boolean CheckForIntersection(Rectangle characterCollisionBox, Rectangle entityCollisionBox) {

        if( characterCollisionBox.intersects(entityCollisionBox) ){
            return true;
        }
        else{
            return false;
        }
    }
}
