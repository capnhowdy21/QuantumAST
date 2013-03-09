
package com.ninjacannon.quantum.entity.component.render;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class AnimRenderComponent extends RenderComponent
{
    protected Animation anim;
    
    public AnimRenderComponent(String id, String images, int length, boolean pingpong)
    {
        super(id);
        anim = new Animation(ImageLibrary.getInstance().getSheet(images), length);
        anim.setAutoUpdate(false);
        anim.setLooping(true);
        anim.setPingPong(pingpong);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {
        Vector2f pos = owner.getPosition();
        float scale = owner.getScale();
        anim.getCurrentFrame().draw(pos.x, pos.y, scale);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        anim.update(delta);
    }
    
    @Override
    public void reset(){
        anim.setCurrentFrame(0);
    }
}
