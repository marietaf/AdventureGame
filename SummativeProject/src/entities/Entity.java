/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Renderable;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author root
 */
public class Entity {

    protected float x, y;
    protected Renderable currentRenderable;
    protected Rectangle collisionBox;

    public Entity(float x, float y, float colxOffset, float colyOffset, float colWidth, float colHeight) {
        this.x = x;
        this.y = y;
        collisionBox = new Rectangle(x + colxOffset, y + colyOffset, colWidth, colHeight);
    }

    public Rectangle GetCollisionBox() {
        return collisionBox;
    }

    public void Render() {
        currentRenderable.draw(x, y);
    }
}
