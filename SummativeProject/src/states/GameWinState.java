/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
public class GameWinState extends BasicGameState {

    int stateID = 3;

    public GameWinState(int stateID){
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return 3;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.drawString("You won! Congrats!", 200, 240);
    }

}
