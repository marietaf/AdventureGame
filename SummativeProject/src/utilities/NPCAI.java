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

    public NPCAI(Level level, long delta){
        this.level = level;
        for (Enemy enemy: level.enemies){
            if (level.player.GetY() < enemy.GetY()){
                enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Up);
                this.direction = CommonCode.CharacterDirection.Up;
                enemy.update(delta);
            }
            if (level.player.GetY() > enemy.GetY()){
                enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Down);
                this.direction = CommonCode.CharacterDirection.Down;
                enemy.update(delta);
            }
            if (level.player.GetX() < enemy.GetX()){
                enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Left);
                this.direction = CommonCode.CharacterDirection.Left;
                enemy.update(delta);
            }
            if (level.player.GetX() > enemy.GetX()){
                enemy.ChangeRenderCharacterDirection(CommonCode.CharacterDirection.Right);
                this.direction = CommonCode.CharacterDirection.Right;
                enemy.update(delta);
            }
        }
    }

    public CommonCode.CharacterDirection GetNPCDirection(){
        return direction;
    }
}
