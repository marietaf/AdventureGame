/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Animation;
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

    public void update( long deltaT ) {
        currentRenderable.update( deltaT );
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
