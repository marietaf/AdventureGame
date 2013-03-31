/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.CharacterStats;

/**
 *
 * @author root
 */
public class Player extends Character {

    Image healthImagePath;

    public Player(Animation up, Animation down, Animation left, Animation right,
            float x, float y, float colxOffset, float colyOffset, float colWidth, float colHeight, CharacterStats stats, String healthImagePath) throws SlickException {
        super(up, down, left, right, x, y, colxOffset, colyOffset, colWidth, colHeight, stats);
        this.healthImagePath = new Image(healthImagePath);
    }

    public Player(String pathName, int [] duration, float x, float y, float colxOffset, float colyOffset, float colWidth, float colHeight, CharacterStats stats, String healthImagePath) throws SlickException {
        super(pathName, duration, x, y, colxOffset, colyOffset, colWidth, colHeight, stats);
        this.healthImagePath = new Image(healthImagePath);
    }

    public Image GetHealthImage(){
        return healthImagePath;
    }

}
