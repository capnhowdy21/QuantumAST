
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

    public static final int MAINMENUSTATE = 0;
    public static final int GAMEPLAYSTATE = 1;
    
    
    public QuantumGame()
    {
        super("Quantum AST");
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new GamePlayState(GAMEPLAYSTATE));
        this.enterState(MAINMENUSTATE);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException
    {
      AppGameContainer app = new AppGameContainer(new QuantumGame());
      app.setMaximumLogicUpdateInterval(20);
      app.setMinimumLogicUpdateInterval(20);
      app.setDisplayMode(1280, 720, true);
      app.setResizable(false);
      app.setShowFPS(true);
      app.start();
    }
    
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException
    {
        this.getState(MAINMENUSTATE).init(gameContainer, this);
        this.getState(GAMEPLAYSTATE).init(gameContainer, this);
    }
}
