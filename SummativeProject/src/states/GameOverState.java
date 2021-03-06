/*This program was created by Marieta Farova, Kelvin Xu and Aron Yoo
 * from December 17th 2012, to January 24th 2013
 * to create an rpg game in java
 */

package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author root
 */
public class GameOverState extends BasicGameState {

    int stateID = 2;

    public GameOverState(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return 2;
    }
    public long deltaTime;
    public long endTime;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //endTime = System.currentTimeMillis();
        //deltaTime = endTime - GameplayState.startTime;
        //if (deltaTime > 10000){
        //    sbg.enterState(0);
        //}
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.drawString("GAME OVER", 200, 240);
    }
}
