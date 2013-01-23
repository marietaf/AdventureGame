/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import utilities.CharacterStats;

/**
 *
 * @author root
 */
public class Enemy extends NPC {
    public Enemy(Animation up, Animation down, Animation left, Animation right,
            float x, float y, float colxOffset, float colyOffset, float colWidth, float colHeight, CharacterStats stats) {
        super(up, down, left, right, x, y, colxOffset, colyOffset, colWidth, colHeight, stats);
    }

    public Enemy(String pathName, int [] duration, float x, float y, float colxOffset, float colyOffset, float colWidth, float colHeight, CharacterStats stats) throws SlickException {
        super(pathName, duration, x, y, colxOffset, colyOffset, colWidth, colHeight, stats);
    }

}
