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
    PlayerInteraction playerInteraction;
    //and NPC interaction!

    public World(float initialX, float initialY, Level[] Levels){
        
    }

    public void ChangeCurrentLevel(){
        DetermineNextLevel();
        isInitialLevel = false;
        
        
        
        //after change current level...
        playerInteraction = new PlayerInteraction(currentLevel);
    }

    private void DetermineNextLevel(){
        float playerX, playerY;
        if(isInitialLevel){
            playerInteraction = new PlayerInteraction(currentLevel);
            playerX = initialX;
            playerY = initialY;
        }
        else{
            NextLevelInformation nextLevelInfo = playerInteraction.GetNextLevelInfo();
            String levelID = nextLevelInfo.GetLevelID();
            playerX = nextLevelInfo.GetStartX();
            playerY = nextLevelInfo.GetStartY();
        }

        RenderCurrentLevel(playerX, playerY);
    }

    private void RenderCurrentLevel(float playerX, float playerY){
        currentLevel.Render(playerX, playerY);
    }

    public Level GetCurrentLevel(){
        return currentLevel;
    }

    public void CheckWorldEvents(){
        
    }

}
