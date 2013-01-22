/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author root
 */
public class Entity {

    protected float x, y;
    protected Rectangle collisionBox;

    public Entity(float x, float y, float colxOffset, float colyOffset, float colWidth, float colHeight) {
        this.x = x;
        this.y = y;
        collisionBox = new Rectangle(x + colxOffset, y + colyOffset, colWidth, colHeight);
    }

    public Rectangle GetCollisionBox() {
        return collisionBox;
    }

    public float GetX(){
        return x;
    }

    public float GetY(){
        return y;
    }

    public void SetX(float x){
        this.x = x;
    }
    public void SetY(float y){
        this.y = y;
    }
}
