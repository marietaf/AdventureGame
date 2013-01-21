/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import org.newdawn.slick.Animation;
import utilities.CharacterStats;

/**
 *
 * @author root
 */
public class Player extends Character {
    public Player(Animation up, Animation down, Animation left, Animation right,
            float x, float y, float colxOffset, float colyOffset, float colWidth, float colHeight, CharacterStats stats) {
        super(up, down, left, right, x, y, colxOffset, colyOffset, colWidth, colHeight, stats);
    }
}
