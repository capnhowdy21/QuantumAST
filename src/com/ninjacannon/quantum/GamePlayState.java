
package com.ninjacannon.quantum;

import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.SceneManager;
import com.ninjacannon.quantum.entity.component.render.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class GamePlayState extends BasicGameState
{
    private int stateID = -1;
    private float xOffset;
    private Player player;
    private Image background = null;
    private SceneManager scene = null;
    
    GamePlayState(int stateID){
        this.stateID = stateID;
    }
    
    @Override
    public int getID(){
        return stateID;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) 
            throws SlickException
    {
        background = ImageLibrary.background;
        player = new Player();
        EntityManager.manager.addEntity(player.getShip());
        scene = new SceneManager();
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException
    {
        background.draw(xOffset, 0);
        background.draw(xOffset + background.getWidth(),0);
        EntityManager.manager.render(gc, sbg, g);
        
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException
    {
        Input input = gc.getInput();
        
        if(input.isKeyDown(Input.KEY_ESCAPE)){
            gc.exit();
        }
        
        xOffset -= .2 * delta;
        if(xOffset <= 0 - background.getWidth()){
            xOffset = 0;
        }
        
        player.update(gc, sbg, delta);
        EntityManager.manager.update(gc, sbg, delta);
        scene.update(gc, sbg, delta);
        if(!player.alive){
            sbg.enterState(0);
        }
    }
    
    @Override
    public void leave(GameContainer gc, StateBasedGame sbg)
            throws SlickException
    {
        EntityManager.manager.cleanUp();
        player.reset();
    }
}
