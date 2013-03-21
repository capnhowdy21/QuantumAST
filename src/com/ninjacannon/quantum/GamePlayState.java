
package com.ninjacannon.quantum;

import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.SceneManager;
import com.ninjacannon.quantum.entity.component.render.ImageLibrary;
import com.ninjacannon.quantum.level.Background;
import org.newdawn.slick.Color;
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
    private Player player = null;
    private SceneManager scene = null;
    private Background background = null;
    private Image hud = null;
    private Image powered = null;
    private float hudOpacity;
    
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
        background = new Background();
        player = new Player();
        EntityManager.manager.addEntity(player.getShip());
        hud = ImageLibrary.getInstance().getImage("hud");
        powered = ImageLibrary.getInstance().getImage("powered");
        scene = new SceneManager(2, 1);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException
    {
        background.render(gc, sbg, g);
        EntityManager.manager.render(gc, sbg, g);
        hud.setAlpha(hudOpacity);
        hud.draw(0, 676);
        powered.setAlpha(hudOpacity);
        if(player.upgrade1()){
            powered.draw(15, 691);
        }
        if(player.upgrade2()){
            powered.draw(56, 691);
        }
        if(player.upgrade3()){
            powered.draw(97, 691);
        }
        g.setColor(Color.yellow);
        g.fillRect(50, 736, 200 * player.getEnergy(), 5);
        g.setColor(Color.cyan);
        g.fillRect(50, 750, 200 * player.getShields(), 5);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException
    {
        gc.setMouseGrabbed(true);
        Input input = gc.getInput();
        
        if(input.isKeyDown(Input.KEY_ESCAPE)){
            gc.exit();
        }
        
        if(input.isKeyPressed(Input.KEY_TAB)){
            player.energy.addEnergy(100);
        }
        
        background.update(gc, sbg, delta);
        player.update(gc, sbg, delta);
        EntityManager.manager.update(gc, sbg, delta);
        scene.update(gc, sbg, delta);
        
        if(player.getShip().getPosition().x < 250 && player.getShip().getPosition().y > 612){
            hudOpacity = .15f;
        } else {
            hudOpacity = .75f;
        }
        
        if(!player.alive){
            sbg.enterState(QuantumGame.MAINMENUSTATE);
        }
        
        if(scene.isComplete()){
            sbg.enterState(QuantumGame.MAINMENUSTATE);
        }
    }
    
    @Override
    public void leave(GameContainer gc, StateBasedGame sbg)
            throws SlickException
    {
        scene = null;
        EntityManager.manager.reset();
        player.reset();
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg)
            throws SlickException
    {
        this.init(gc, sbg);
    }
}
