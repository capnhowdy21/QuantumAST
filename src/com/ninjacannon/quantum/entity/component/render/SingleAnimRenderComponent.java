
package com.ninjacannon.quantum.entity.component.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class SingleAnimRenderComponent extends AnimRenderComponent 
{
    public SingleAnimRenderComponent(String id, SpriteSheet images, int w, int h, int length){
        super(id, images, w, h, length, false);
        anim.setLooping(false);
    }
            
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta){
        super.update(gc, sbg, delta);
        if(anim.isStopped()){
            owner.setAlive(false);
        }
    }
}
