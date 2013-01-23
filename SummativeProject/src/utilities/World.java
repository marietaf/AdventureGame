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

    Level[] levels;
    Level currentLevel;
    float initialX, initialY;
    PlayerInteraction playerInteraction;
    NPCInteraction npcInteraction;
    String initialLevelID;
    //and NPC interaction!

    public World(String initialLevelID, float initialX, float initialY, Level[] levels){
        this.initialLevelID = initialLevelID;
        this.initialX = initialX;
        this.initialY = initialY;
        this.levels = levels;
    }

    public void ChangeCurrentLevel(){
        NextLevelInformation nextLevelInfo = playerInteraction.GetNextLevelInfo();

        for (Level level: levels){
            if(nextLevelInfo.GetLevelID().equals(level.GetLevelID())){
                currentLevel = level;
                break;
            }
        }

        currentLevel.player.SetNewCoordinates(playerInteraction.GetNextLevelInfo().GetStartX(),
                playerInteraction.GetNextLevelInfo().GetStartY());

        RenderCurrentLevel(/*nextLevelInfo.GetStartX(), nextLevelInfo.GetStartY()*/);
        playerInteraction = new PlayerInteraction(currentLevel);
        //npcInteraction = new NPCInteraction(currentLevel);
    }

    public void RenderCurrentLevel(){
        currentLevel.Render();
    }

    public Level GetCurrentLevel(){
        return currentLevel;
    }

    public void UpdateWorld(int key, long delta){
        playerInteraction.HandleEvents(key, delta);
        //npcInteraction.HandleEvents();
        if( playerInteraction.GetSwitchLevel() ){
            ChangeCurrentLevel();
        }
    }

    public void InitializeFirstLevel(){
        NextLevelInformation initialLevelInfo = new NextLevelInformation(initialLevelID, initialX, initialY);
        currentLevel = levels[0];
        RenderCurrentLevel(/*initialLevelInfo.GetStartX(), initialLevelInfo.GetStartY()*/);
        playerInteraction = new PlayerInteraction(currentLevel);
    }

}
