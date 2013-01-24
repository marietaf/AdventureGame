/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import entities.*;

/**
 *
 * @author root
 */
public class NPCAI {
    Level level;

    public NPCAI(Level level){
        this.level = level;
        
    }

    public void RunAI(long delta){
        for (Enemy enemy: level.enemies){
            CommonCode.CharacterDirection horizontalDirection;
            CommonCode.CharacterDirection verticalDirection;
            if (level.player.GetY() < enemy.GetY()){
                 verticalDirection = CommonCode.CharacterDirection.Up;
            }
            else {
                 verticalDirection = CommonCode.CharacterDirection.Down;
            }
            enemy.ChangeRenderCharacterDirection(verticalDirection);
            
            if (level.player.GetX() < enemy.GetX()){
                horizontalDirection = CommonCode.CharacterDirection.Left;
            }
            else {
                horizontalDirection = CommonCode.CharacterDirection.Right;
            }
            enemy.ChangeRenderCharacterDirection(horizontalDirection);
        }
    }
}
