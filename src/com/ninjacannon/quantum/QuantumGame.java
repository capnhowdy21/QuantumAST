
package com.ninjacannon.quantum;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Dan Cannon
 */
public class QuantumGame extends StateBasedGame
{
    public static final int GAMEWIDTH = 1024;
    public static final int GAMEHEIGHT = 768;
    
    public static final int MAINMENUSTATE = 0;
    public static final int GAMEPLAYSTATE = 1;
    
    
    public QuantumGame()
    {
        super("Quantum AST");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException
    { 
      AppGameContainer app = new AppGameContainer(new QuantumGame());
      app.setMaximumLogicUpdateInterval(20);
      app.setMinimumLogicUpdateInterval(20);
      app.setDisplayMode(GAMEWIDTH, GAMEHEIGHT, true);
      app.setResizable(false);
      app.setShowFPS(true);
      app.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new GamePlayState(GAMEPLAYSTATE));
    }
}
