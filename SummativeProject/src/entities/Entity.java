/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.geom.Rectangle;
import utilities.PlayerInteraction;

/**
 *
 * @author root
 */
public class Entity {

    protected float x, y;
    protected float colxOffset, colyOffset;
    protected Rectangle collisionBox;

    public Entity(float x, float y, float colxOffset, float colyOffset, float colWidth, float colHeight) {
        this.x = x;
        this.y = y;
        this.colxOffset = colxOffset;
        this.colyOffset = colyOffset;
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

    public float GetCollisionXOffset(){
        return colxOffset;
    }

    public float GetCollisionYOffset(){
        return colyOffset;
    }

    public void SetNewCoordinates(float x, float y){
        this.x = x;
        this.y = y;
        this.collisionBox.setX(x + colxOffset);
        this.collisionBox.setY(y + colyOffset);
    }
}
