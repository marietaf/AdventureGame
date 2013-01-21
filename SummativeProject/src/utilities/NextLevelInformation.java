/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

/**
 *
 * @author root
 */
public class NextLevelInformation {
    String levelID;
    float startX, startY;

    public NextLevelInformation(String levelID, float startX, float startY){
        this.levelID = levelID;
        this.startX = startX;
        this.startY = startY;
    }

    public String GetLevelID(){
        return levelID;
    }

    public float GetStartX(){
        return startX;
    }

    public float GetStartY(){
        return startY;
    }
}
