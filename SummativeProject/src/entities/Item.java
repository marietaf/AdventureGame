/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import org.newdawn.slick.Renderable;

/**
 *
 * @author root
 */
public class Item extends Entity {
    
    protected Renderable currentRenderable;

    public Item(float x, float y, float colxOffset, float colyOffset, float colWidth, float colHeight) {
        super(x, y, colxOffset, colyOffset, colWidth, colHeight);
    }

    public void Render() {
        currentRenderable.draw(x, y);
    }
}
