/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

/**
 *
 * @author root
 */
public class World {

    Level[] Levels;
    Level currentLevel;
    boolean isInitialLevel = true;
    float initialX, initialY;

    public World(float initialX, float initialY, Level[] Levels){
        
    }

    private void RenderCurrentLevel(float playerX, float playerY){
        currentLevel.Render(playerX, playerY);
    }

    private void DetermineNextLevel(){
        float playerX, playerY;
        if(isInitialLevel){
            playerX = initialX;
            playerY = initialY;
        }
        else{

        }

        //CHECK DOOR ID

        
        RenderCurrentLevel(playerX, playerY);
    }

    public void ChangeCurrentLevel(){
        DetermineNextLevel();
        isInitialLevel = false;
    }

    public Level GetCurrentLevel(){
        return currentLevel;
    }

}
