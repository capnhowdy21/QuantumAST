
package com.ninjacannon.quantum;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.SceneManager;
import com.ninjacannon.quantum.entity.component.*;
import com.ninjacannon.quantum.entity.component.movement.KeyboardMovementComponent;
import com.ninjacannon.quantum.entity.component.render.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class GamePlayState extends BasicGameState
{
    int stateID = -1;
    float xOffset;
    Image background = null;
    Entity player = null;
    SceneManager scene = null;
    
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
        player = new Entity("Player");
        player.AddComponent(new ImageRenderComponent("Render", ImageLibrary.ship));
        player.AddComponent(new KeyboardMovementComponent("Controls"));
        player.AddComponent(new GunComponent("Gun"));
        player.setPosition(new Vector2f(0,0));
        player.setHeight(ImageLibrary.ship.getHeight());
        player.setWidth(ImageLibrary.ship.getWidth());
        player.setScale(.5f);
        EntityManager.manager.addEntity(player);
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
        xOffset -= .2 * delta;
        if(xOffset <= 0 - background.getWidth()){
            xOffset = 0;
        }
        EntityManager.manager.update(gc, sbg, delta);
        scene.update(gc, sbg, delta);
    }
}
