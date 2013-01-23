/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.CharacterStats;
import utilities.CommonCode;

/**
 *
 * @author root
 */
public class Character extends Entity {

    Animation animationUp, animationDown, animationLeft, animationRight;
    protected Animation currentRenderable;
    CommonCode.CharacterDirection currentDirection;
    CharacterStats characterStats;

    public Character(Animation up, Animation down, Animation left, Animation right,
            float x, float y, float colxOffset, float colyOffset, float colWidth, float colHeight, CharacterStats stats) {
        
        super(x, y, colxOffset, colyOffset, colWidth, colHeight);
        animationUp = up;
        animationDown = down;
        animationLeft = left;
        animationRight = right;
        
        characterStats = stats;
        currentRenderable = animationDown;
        currentDirection = CommonCode.CharacterDirection.Down;
    }

    public Character( String animPathName, int [] duration, float x, float y, float colxOffset, float colyOffset, float colWidth, float colHeight, CharacterStats stats ) throws SlickException {
        super(x, y, colxOffset, colyOffset, colWidth, colHeight);
        animationUp = new Animation(new Image[]{new Image(animPathName + "_bk1.gif"), new Image(animPathName + "_bk2.gif")}, duration, false);
        animationDown = new Animation(new Image[]{new Image(animPathName + "_fr1.gif"), new Image(animPathName + "_fr2.gif")}, duration, false);
        animationLeft = new Animation(new Image[]{new Image(animPathName + "_lf1.gif"), new Image(animPathName + "_lf2.gif")}, duration, false);
        animationRight = new Animation(new Image[]{new Image(animPathName + "_rt1.gif"), new Image(animPathName + "_rt2.gif")}, duration, false);
        
        currentRenderable = animationDown;
        currentDirection = CommonCode.CharacterDirection.Down;
        characterStats = stats;
    }

    public void update( long delta ) {
        currentRenderable.update( delta );
    }



    public void ChangeRenderCharacterDirection(CommonCode.CharacterDirection direction) {
        switch (direction) {
            case Up:
                currentRenderable = animationUp;
                currentDirection = CommonCode.CharacterDirection.Up;
                break;

            case Down:
                currentRenderable = animationDown;
                currentDirection = CommonCode.CharacterDirection.Down;
                break;

            case Left:
                currentRenderable = animationLeft;
                currentDirection = CommonCode.CharacterDirection.Left;
                break;

            case Right:
                currentRenderable = animationRight;
                currentDirection = CommonCode.CharacterDirection.Right;
                break;
        }
    }

    public CommonCode.CharacterDirection GetCharacterDirection() {
        return currentDirection;
    }

    public Animation GetCharacterRenderableDirection(){
        return currentRenderable;
    }
    
    public CharacterStats GetCharacterStats(){
        return characterStats.Clone();
    }

    public void MoveUp(){
        y -= characterStats.GetSpeed();
        collisionBox.setY(collisionBox.getY() - characterStats.GetSpeed());
        //currentRenderable.update(delta);
    }

    public void MoveDown(){
        y += characterStats.GetSpeed();
        collisionBox.setY(collisionBox.getY() + characterStats.GetSpeed());
    }

    public void MoveLeft(){
        x -= characterStats.GetSpeed();
        collisionBox.setX(collisionBox.getX() - characterStats.GetSpeed());
    }

    public void MoveRight(){
        x += characterStats.GetSpeed();
        collisionBox.setX(collisionBox.getX() + characterStats.GetSpeed());
    }

    public void Render() {
        currentRenderable.draw(x, y);
    }

}
