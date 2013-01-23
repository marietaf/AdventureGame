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
    CommonCode.CharacterDirection direction;
    Level level;

    public NPCAI(Level level){
        this.level = level;
        
    }

    public void RunAI(long delta){
        for (Enemy enemy: level.enemies){
            if (level.player.GetY() < enemy.GetY()){
                this.direction = CommonCode.CharacterDirection.Up;
                enemy.ChangeRenderCharacterDirection(direction);
                break;
            }
            if (level.player.GetY() > enemy.GetY()){
                this.direction = CommonCode.CharacterDirection.Down;
                enemy.ChangeRenderCharacterDirection(direction);
                break;
            }
            if (level.player.GetX() < enemy.GetX()){
                this.direction = CommonCode.CharacterDirection.Left;
                enemy.ChangeRenderCharacterDirection(direction);
                break;
            }
            if (level.player.GetX() > enemy.GetX()){
                this.direction = CommonCode.CharacterDirection.Right;
                enemy.ChangeRenderCharacterDirection(direction);
                break;
            }
        }
    }

    public CommonCode.CharacterDirection GetNPCDirection(){
        return direction;
    }
}
